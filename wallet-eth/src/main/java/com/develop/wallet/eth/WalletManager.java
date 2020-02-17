package com.develop.wallet.eth;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.develop.mnemonic.KeyPairUtils;
import com.develop.mnemonic.MnemonicUtils;
import com.develop.wallet.eth.listener.WalletListener;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.AdminFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthUninstallFilter;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.novacrypto.bip39.SeedCalculator;
import io.github.novacrypto.bip39.wordlists.English;
import io.github.novacrypto.hashing.Sha256;


public class WalletManager {

    public static boolean DEBUG = true;
    /**
     * 代币合约地址
     */
    public static String tokenAddres = "";

    /**
     * 区块链服务器地址
     */
    public static String URL = "https://ropsten.infura.io/v3/bb770b6135ec434e9259072aee28efe0";


    public static BigInteger GAS_PRICE = BigInteger.valueOf(0x3b9aca00);
    public static BigInteger GAS_LIMIT = BigInteger.valueOf(0x493e0);

    private static Web3j web3j;
    private static Admin admin;
    private static ExecutorService mExecutorService;
    private static ImportWalletListener mImportWalletListener;
    private static CheckPasswordListener mCheckPasswordListener;
    private static Wallet mWallet;

    public static Web3j getWeb3j() {
        if (web3j == null) {
            web3j = Web3jFactory.build(new HttpService(URL));
        }
        return web3j;
    }


    public static Admin getAdmin() {
        if (admin == null) {
            admin = AdminFactory.build(new HttpService(URL));
        }
        return admin;
    }

    public static ExecutorService getExecutorService() {
        if (mExecutorService == null) {
            mExecutorService = Executors.newCachedThreadPool();
        }
        return mExecutorService;
    }

    /**
     * 配置地址
     *
     * @param

     */
    public static void config( String url) {

        URL = url;

    }

    private  static Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    mImportWalletListener.importSuccess((Wallet) msg.obj);
                    break;
                case 2:
                    mImportWalletListener.importFailure((Exception) msg.obj);
                    break;
                case 3:
                    mCheckPasswordListener.onSuccess((Wallet) msg.obj);
                    break;
                case 4:
                    mCheckPasswordListener.onFailure((Exception) msg.obj);

                    break;
            }
            return false;
        }
    });





    /**
     * 配置地址
     *
     * @param url
     * @param token
     * @param debug
     */
    public static void config(String url, String token, boolean debug) {
        URL = url;
        tokenAddres = token;
        DEBUG = debug;
    }



    /**1
     * 生成钱包地址
     */
    public static Wallet generateWalletAddress(String walletName) {
        try {
            String mnemonic = MnemonicUtils.generateMnemonic();
            Log.e("mnemonic>>>>>>>",mnemonic);
            ECKeyPair ecKeyPair = WalletUtils.generateBip32ECKeyPair(mnemonic);
            String address = EthUtils.getAddress(ecKeyPair);
            String privateKey = EthUtils.getPrivateKey(ecKeyPair);
            String publicKey = EthUtils.getPublicKey(ecKeyPair);
            log(String.format("generateWalletAddress: mnemonic = %s, address = %s, privateKey = %s", mnemonic, address, privateKey));
            Wallet wallet = new Wallet(mnemonic, address, privateKey, publicKey);
            wallet.setWalletName(walletName);
            return wallet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据助记词获取地址
     *
     * @param mnemonic
     * @return
     */
    public static String generateAddress(String mnemonic) {
        ECKeyPair ecKeyPair = WalletUtils.generateBip32ECKeyPair(mnemonic);
        String address = EthUtils.getAddress(ecKeyPair);
        log(String.format("generateAddress: mnemonic = %s, address = %s", mnemonic, address));
        return address;
    }

    /**
     * 通过助记词获取私钥
     *
     * @param mnemonic
     */
    public static String generatePrivateKey(String mnemonic) {
        ECKeyPair ecKeyPair = WalletUtils.generateBip32ECKeyPair(mnemonic);
        String privateKey = EthUtils.getPrivateKey(ecKeyPair);
        log(String.format("generatePrivateKey: mnemonic = %s, privateKey = %s", mnemonic, privateKey));

        return privateKey;
    }

    /**
     * 生成钱包keystore
     *
     * @param password
     * @param mnemonic
     * @return
     */
    public static Wallet generateWalletKeystore(String password, String mnemonic,File destination) {
        try {
            Wallet wallet =  WalletUtils.generateBip32WalletFile(password,destination,mnemonic);
//            Wallet wallet = WalletUtils.generateBip32Wallet(password, mnemonic);
            if (wallet != null) {
                log(String.format("generateWalletKeystore: mnemonic = %s, password = %s, privateKey = %s, keystore = %s", mnemonic, password, wallet.getPrivateKey(), wallet.getKeystore()));
            }
            return wallet;
        } catch (CipherException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




    /**
     * 导出私钥
     *
     * @param password   创建钱包时的密码
     * @param walletFile
     * @return
     */
    public String exportPrivateKey(String password, WalletFile walletFile) {

        try {
            ECKeyPair ecKeyPair = org.web3j.crypto.Wallet.decrypt(password, walletFile); //可能出现OOM
//            ECKeyPair ecKeyPair = LWallet.decrypt(password, walletFile);
            String privateKey = Numeric.toHexStringNoPrefix(ecKeyPair.getPrivateKey());
            return privateKey;
        } catch (CipherException e) {
            e.printStackTrace();
            return "error";
        }
    }





    /**
     * 导出Keystore
     *
     * @param password   创建钱包时的密码
     * @param walletFile
     * @return
     */
//    public String exportKeystore(String password, WalletFile walletFile) {
//        if (decrypt(password, walletFile)) {
//            return new Gson().toJson(walletFile);
//        } else {
//            return "decrypt failed";
//        }
//    }

    /**
     * 解密
     * 如果方法没有抛出CipherException异常则表示解密成功，也就是说可以把Wallet相关信息展示给用户看
     *
     * @param password   创建钱包时的密码
     * @param walletFile
     * @return
     */
    public static  void decrypt(String password, WalletFile walletFile,DecryptListener decryptListener) {
        try {
            ECKeyPair ecKeyPair = org.web3j.crypto.Wallet.decrypt(password, walletFile); //可能出现OOM
//            ECKeyPair ecKeyPair = LWallet.decrypt(password, walletFile);
            String address = EthUtils.getAddress(ecKeyPair);
            String privateKey = EthUtils.getPrivateKey(ecKeyPair);
            String publicKey = EthUtils.getPublicKey(ecKeyPair);
            mWallet.setAddress(address);
            mWallet.setPrivateKey(privateKey);
            mWallet.setPublicKey(publicKey);
            decryptListener.onSuccess(true,mWallet);
        } catch (CipherException e) {
            e.printStackTrace();
            decryptListener.onSuccess(false,null);

        }
    }


    public interface CheckPasswordListener{
        void onSuccess(Wallet wallet);
        void onFailure(Exception e);
    }


    /**
     * 解密
     * 如果方法没有抛出CipherException异常则表示解密成功，代表密码正确
     */
    public static  void decrypt(final String password, final Wallet wallet, CheckPasswordListener checkPasswordListener) {
        mCheckPasswordListener = checkPasswordListener;
        mWallet = wallet;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectMapper objectMapper = new ObjectMapper();
                WalletFile walletFile = null;
                try {

//                   解决用keystore导入苹果创建账号时候无法验证bug
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    walletFile = objectMapper.readValue(wallet.getKeystore(), WalletFile.class);

                     decrypt(password, walletFile, new DecryptListener() {
                        @Override
                        public void onSuccess(Boolean success, Wallet wallet) {
                            if (success){
                                Message message = new Message();
                                message.what = 3;
                                message.obj = wallet;
                                handler.sendMessage(message);
                            }else {
                                Message message = new Message();
                                message.what = 4;
                                handler.sendMessage(message);
                            }

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                    Message message = new Message();
                    message.what = 4;
                    message.obj = e;
                    handler.sendMessage(message);
                }
            }
        });
        thread.start();


    }



    public  interface  DecryptListener{
        void onSuccess(Boolean success,Wallet wallet);
    }


    public static void changePassword(final Context context, String oldPassword, final String newPassword, final Wallet wallet, final ImportWalletListener importWalletListener) {
        mImportWalletListener = importWalletListener;
        mCheckPasswordListener =  new CheckPasswordListener() {
            @Override
            public void onSuccess(Wallet wallet1) {
                importWalletByPrivateKey(wallet1.getPrivateKey(), wallet1.getWalletName(), newPassword, new ImportWalletListener() {
                    @Override
                    public void importSuccess(Wallet wallet) {
//                        Message message = new Message();
//                        message.what=1;
//                        message.obj = wallet;
//                        handler.sendMessage(message);
                        importWalletListener.importSuccess(wallet);
                    }

                    @Override
                    public void importFailure(Exception e) {
//                        Message message = new Message();
//                        message.what=2;
//                        message.obj = e;
//                        handler.sendMessage(message);
                        importWalletListener.importFailure(e);

                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                importWalletListener.importFailure(e);

            }
        };
        decrypt(oldPassword, wallet, mCheckPasswordListener);
//        ObjectMapper objectMapper = new ObjectMapper();
//        WalletFile walletFile = null;
//        try {
//            walletFile = objectMapper.readValue(wallet.getKeystore(), WalletFile.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (decrypt(oldPassword,walletFile)){
//            importWalletByPrivateKey(wallet.getPrivateKey(), wallet.getWalletName(), newPassword, new ImportWalletListener() {
//                @Override
//                public void importSuccess(Wallet wallet) {
//                    Message message = new Message();
//                    message.what=1;
//                    message.obj = wallet;
//                    handler.sendMessage(message);
//                }
//
//                @Override
//                public void importFailure(Exception e) {
//                    Message message = new Message();
//                    message.what=2;
//                    message.obj = e;
//                    handler.sendMessage(message);
//                }
//            });
//        }else {
//            Toast.makeText(context,context.getResources().getString(R.string.pass_err),Toast.LENGTH_SHORT).show();
//        }

    }





    public static void importWalletByKeystore(final String password, final String keystore, final String name, final ImportWalletListener importWalletListener){
        mImportWalletListener = importWalletListener;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                WalletFile walletFile = null;
                try {
                    walletFile = objectMapper.readValue(keystore, WalletFile.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    ECKeyPair keyPair = org.web3j.crypto.Wallet.decrypt(password, walletFile);
                    String address = EthUtils.getAddress(keyPair);
                    String privateKey = EthUtils.getPrivateKey(keyPair);
                    String publicKey = EthUtils.getPublicKey(keyPair);
//            WalletFile walletFile = WalletUtils.createWalletFile(password, keyPair, false);
                    Wallet wallet = new Wallet(null, address, privateKey, publicKey,keystore);
                    wallet.setWalletName(name);
                    Message message = new Message();
                    message.what=1;
                    message.obj = wallet;
                    handler.sendMessage(message);
                    Log.e("------------", address);
                } catch (CipherException e) {
                    e.printStackTrace();
                    Message message = new Message();
                    message.what=2;
                    message.obj = e;
                    handler.sendMessage(message);

                }catch (Exception e){
                    Message message = new Message();
                    message.what=2;
                    message.obj = e;
                    handler.sendMessage(message);
                }

            }
        });
        thread.start();


    }




    public static void importWalletByMemoryWord(final String passwd, final String menmory, final String name, final ImportWalletListener importWalletListener){
        mImportWalletListener = importWalletListener;

        List mnemonicList = Arrays.asList(menmory.split(" "));
        if (mnemonicList.size()!=12){
            importWalletListener.importFailure(new Exception("助记词必须是12个"));
            return;
        }
//        byte[] seed = new SeedCalculator()
//                .withWordsFromWordList(English.INSTANCE)
//                .calculateSeed(mnemonicList, passwd);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                byte[] seed =  MnemonicUtils.generateSeed(menmory,passwd);
                ECKeyPair ecKeyPair = WalletUtils.generateBip32ECKeyPair(menmory);
                String address = EthUtils.getAddress(ecKeyPair);
                String privateKey = EthUtils.getPrivateKey(ecKeyPair);
                String publicKey = EthUtils.getPublicKey(ecKeyPair);

//                ECKeyPair ecKeyPair = ECKeyPair.create(Sha256.sha256(seed));
//                String privateKey = ecKeyPair.getPrivateKey().toString(16);
//                String publicKey = ecKeyPair.getPublicKey().toString(16);
//                String address = "0x" + Keys.getAddress(publicKey);
                String json = null;
                WalletFile walletFile = null;
                try {
                    walletFile = WalletUtils.createWalletFile(passwd, ecKeyPair, false);
                    json = WalletUtils.objectMapper.writeValueAsString(walletFile);
                    Wallet wallet = new Wallet(menmory, address, privateKey, publicKey,json);
                    wallet.setWalletName(name);
                    Message message = new Message();
                    message.what=1;
                    message.obj = wallet;
                    handler.sendMessage(message);
                } catch (CipherException e) {
                    e.printStackTrace();
                    Message message = new Message();
                    message.what=2;
                    message.obj = e;
                    handler.sendMessage(message);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    Message message = new Message();
                    message.what=2;
                    message.obj = e;
                    handler.sendMessage(message);
                }

            }
        });
        thread.start();

        //创建钱包地址与密钥
//        String fileName = null;
//        try {
//            fileName = WalletUtils.generateWalletFile(passwd, ecKeyPair, new File(Environment.getExternalStorageDirectory().getPath() + "/MyWallet"), false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }


    public interface ImportWalletListener{
        void importSuccess(Wallet wallet);
        void importFailure(Exception e);
    }


    public static void importWalletByPrivateKey(final String privateKey, final String name, final String passwd, final ImportWalletListener importWalletListener){


       mImportWalletListener = importWalletListener;
        if (privateKey.startsWith("0x")&&privateKey.length()!=66){
            mImportWalletListener.importFailure(new Exception("私钥格式错误"));
            return;
        }
        if (!privateKey.startsWith("0x")&&privateKey.length()!=64){
            mImportWalletListener.importFailure(new Exception("私钥格式错误"));
            return;
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Credentials credentials = Credentials.create(privateKey);
                ECKeyPair ecKeyPair = credentials.getEcKeyPair();
//        ECKeyPair.create(new BigInteger(privateKey,16));
                String msg = "address:\n" + credentials.getAddress()
                        + "\nprivateKey:\n" + Numeric.encodeQuantity(ecKeyPair.getPrivateKey())
                        + "\nPublicKey:\n" + Numeric.encodeQuantity(ecKeyPair.getPublicKey());
                Log.e("ByMemoryWord:",msg);

                String json = null;
                WalletFile walletFile = null;
                try {
                    walletFile = WalletUtils.createWalletFile(passwd, ecKeyPair, false);
                    json = WalletUtils.objectMapper.writeValueAsString(walletFile);
                    Wallet wallet = new Wallet(null, credentials.getAddress(), Numeric.encodeQuantity(ecKeyPair.getPrivateKey()),  Numeric.encodeQuantity(ecKeyPair.getPublicKey()),json);
                    wallet.setWalletName(name);
                    Message message = new Message();
                    message.what=1;
                    message.obj = wallet;
                    handler.sendMessage(message);
                } catch (CipherException e) {
                    e.printStackTrace();
                    Message message = new Message();
                    message.what=2;
                    message.obj = e;
                    handler.sendMessage(message);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    Message message = new Message();
                    message.what=2;
                    message.obj = e;
                    handler.sendMessage(message);
                }
            }
        });
        thread.start();


    }






    /**
     * 通过keystore 获取私钥
     *
     * @param password
     * @param keystore
     */
    public static void generatePrivateKey(String password, String keystore) {
        try {
            ECKeyPair ecKeyPair = WalletUtils.loadKeystore(password, keystore);
            String address = EthUtils.getAddress(ecKeyPair);
            String privateKey = EthUtils.getPrivateKey(ecKeyPair);
            log(String.format("generatePrivateKey: privateKey = %s, address = %s, password = %s, keystore = %s", privateKey, address, password, keystore));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据私钥转账
     *
     * @param fromAddress
     * @param privateKey
     * @param toAddress
     * @param amount
     * @return
     */
    public static String sendTransactionByPrivateKey(String fromAddress, String privateKey, String toAddress, String amount) {
        Log.e("privatekey",privateKey);
        Log.e("fromAddress>>>>",fromAddress);
        Log.e("toAddress>>>>",toAddress);
        ECKeyPair ecKeyPair = ECKeyPair.create(EthUtils.toKeyBigInteger("e851d34bfb21bbc5ff0c33c8b5fad841faa2b9bc63f4cdd42651092394e4f6a9"));
        return sendTransaction(fromAddress, ecKeyPair, toAddress, EthUtils.toBigInteger(amount));
    }


    public static String sendTransactionByPrivateKey(String fromAddress, String privateKey, String toAddress, String amount,float gasPrice,String gasLitmit,int decimal) {

        Log.e("privatekey",privateKey);
        Log.e("fromAddress>>>>",fromAddress);
        Log.e("toAddress>>>>",toAddress);

        if (privateKey.startsWith("0x")) privateKey = privateKey.substring(2,privateKey.length());
        Log.e("privateKey>>>>",privateKey);
        ECKeyPair ecKeyPair = ECKeyPair.create(EthUtils.toKeyBigInteger(privateKey));
//        return sendTransaction(fromAddress, ecKeyPair, toAddress, EthUtils.toBigInteger(amount),gasPrice,gasLitmit);

        return sendTransaction(fromAddress, ecKeyPair, toAddress, new BigDecimal(amount).multiply(BigDecimal.TEN.pow(decimal)).toBigInteger(),gasPrice,gasLitmit);


    }




    public static String sendTransactionByPrivateKey(String fromAddress, BigInteger privateKey, String toAddress, String amount) {
        ECKeyPair ecKeyPair = ECKeyPair.create(privateKey);
        return sendTransaction(fromAddress, ecKeyPair, toAddress, EthUtils.toBigInteger(amount));
    }

    /**
     * 根据助记词转账
     *
     * @param fromAddress
     * @param mnemonic
     * @param toAddress
     * @param amount
     * @return
     */
    public static String sendTransactionByMnemonic(String fromAddress, String mnemonic, String toAddress, String amount) {
        ECKeyPair ecKeyPair = WalletUtils.generateBip32ECKeyPair(mnemonic);
        return sendTransaction(fromAddress, ecKeyPair, toAddress, EthUtils.toBigInteger(amount));
    }

    /**
     * 根据助记词异步调用转账
     *
     * @param fromAddress
     * @param mnemonic
     * @param toAddress
     * @param amount
     * @param listener
     */
    public static void sendTransactionByMnemonicAsync(final String fromAddress,
                                                      final String mnemonic,
                                                      final String toAddress,
                                                      final String amount,
                                                      final WalletListener listener) {
        getExecutorService().submit(new Runnable() {
            @Override
            public void run() {
                ECKeyPair ecKeyPair = WalletUtils.generateBip32ECKeyPair(mnemonic);
                String hash = sendTransaction(fromAddress, ecKeyPair, toAddress, EthUtils.toBigInteger(amount));
                if (listener != null) {
                    listener.onSendTransaction(hash);
                }
            }
        });
    }

    /**
     * 发送转账交易
     */
    private static String sendTransaction(String fromAddress, ECKeyPair ecKeyPair, String toAddress, BigInteger amount) {
        // noce
        BigInteger nonce = getNonce(fromAddress);

        String hash = sendTransaction(fromAddress, ecKeyPair, toAddress, amount, nonce, 0);
        return hash;
    }


    /**
     * 发送转账交易
     */
    private static String sendTransaction(String fromAddress, ECKeyPair ecKeyPair, String toAddress, BigInteger amount,float gasPrice,String gasLitmit) {
        // noce
        BigInteger nonce = getNonce(fromAddress);
        String hash = sendTransaction(fromAddress, ecKeyPair, toAddress, amount, nonce, 0,gasPrice,gasLitmit);
        return hash;
    }




    private static int maxResetCount =20;

    private static String sendTransaction(String fromAddress, ECKeyPair ecKeyPair, String toAddress, BigInteger amount, BigInteger nonce, int resetCount) {
        try {
//            PersonalUnlockAccount personalUnlockAccount = getAdmin().personalUnlockAccount(fromAddress, "").send();
//            if (personalUnlockAccount.accountUnlocked()) {
//            }
            List inputParameters = Arrays.asList(new Address(toAddress), new Uint256(amount));
            List outputParameters = Arrays.asList(new TypeReference<Address>() {
            }, new TypeReference<Bool>() {
            });

            //创建交易  注意金额 保留小数点后8位 要转化为整数 比如0.00000001 转化为1
            Function function = new Function("transfer", inputParameters, outputParameters);
            String data = FunctionEncoder.encode(function);
//
////            智能合约事物
            BigInteger GAS_PRICE = Convert.toWei(BigDecimal.valueOf(5), Convert.Unit.GWEI).toBigInteger();
            BigInteger GAS_LIMIT = Convert.toWei(BigDecimal.valueOf(60000), Convert.Unit.GWEI).toBigInteger();
            Log.e("GAS_PRICE>>>>",GAS_PRICE.toString());
            Log.e("GAS_LIMIT>>>>",GAS_LIMIT.toString());
            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, GAS_PRICE, GAS_LIMIT, tokenAddres, data);

            //通过私钥获取凭证  当然也可以根据其他的获取 其他方式详情请看web3j
            Credentials credentials = Credentials.create(ecKeyPair);

            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);
            //发送事务
            EthSendTransaction ethSendTransaction = null;
            try {
                ethSendTransaction = getWeb3j().ethSendRawTransaction(hexValue).sendAsync().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            //事物的HASH
            String transactionHash = ethSendTransaction.getTransactionHash();
            // nonce +1 重试
            log(String.format("sendTransaction: hash = %s, nonce = %s", transactionHash, String.valueOf(nonce)));
            if (transactionHash == null) {
                if (resetCount < maxResetCount) {
                    nonce = nonce.add(BigInteger.valueOf(1));
                    resetCount += 1;
                    log(String.format("sendTransaction reset: count = %s, nonce = %s", String.valueOf(resetCount), String.valueOf(nonce)));
                    return sendTransaction(fromAddress, ecKeyPair, toAddress, amount, nonce, resetCount++);
                }
            }
            return transactionHash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static String sendTransaction(String fromAddress, ECKeyPair ecKeyPair, String toAddress, BigInteger amount, BigInteger nonce, int resetCount,float gasPrice,String gasLitmit) {
        try {
//            PersonalUnlockAccount personalUnlockAccount = getAdmin().personalUnlockAccount(fromAddress, "").send();
//            if (personalUnlockAccount.accountUnlocked()) {
//            }
//            amount = new BigInteger("100000");
            List inputParameters = Arrays.asList(new Address(toAddress), new Uint256(amount));
            List outputParameters = Arrays.asList(new TypeReference<Address>() {
            }, new TypeReference<Bool>() {
            });

            //创建交易  注意金额 保留小数点后8位 要转化为整数 比如0.00000001 转化为1
            Function function = new Function("transfer", inputParameters, outputParameters);
            String data = FunctionEncoder.encode(function);
//            智能合约事物
            BigInteger GAS_PRICE = Convert.toWei(BigDecimal.valueOf(gasPrice), Convert.Unit.GWEI).toBigInteger();
//            BigInteger GAS_LIMIT = Convert.toWei(BigDecimal.valueOf(gasLitmit), Convert.Unit.GWEI).toBigInteger();
            BigInteger GAS_LIMIT =new BigInteger(gasLitmit);
//            GAS_PRICE = new BigInteger("7000000000");
//            GAS_LIMIT = new BigInteger("50000");

            Log.e("GAS_PRICE>>>>",GAS_PRICE.toString());
            Log.e("GAS_LIMIT>>>>",GAS_LIMIT.toString());
            Log.e("amount>>>>",amount.toString());
            RawTransaction rawTransaction;
            if (TextUtils.isEmpty(tokenAddres)){
                rawTransaction = RawTransaction.createEtherTransaction(nonce, GAS_PRICE, GAS_LIMIT, toAddress, amount);
            }else {
                 rawTransaction = RawTransaction.createTransaction(nonce, GAS_PRICE, GAS_LIMIT, tokenAddres, data);
            }
            //通过私钥获取凭证  当然也可以根据其他的获取 其他方式详情请看web3j
            Credentials credentials = Credentials.create(ecKeyPair);

            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);
            //发送事务
            EthSendTransaction ethSendTransaction = null;
            try {
                ethSendTransaction = getWeb3j().ethSendRawTransaction(hexValue).sendAsync().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            //事物的HASH
            String transactionHash = ethSendTransaction.getTransactionHash();

            if (ethSendTransaction!=null&&ethSendTransaction.getError()!=null)
            Log.e("error>>>>",ethSendTransaction.getError().getMessage());
            // nonce +1 重试
            log(String.format("sendTransaction: hash = %s, nonce = %s", transactionHash, String.valueOf(nonce)));
//            if (transactionHash == null) {
//                if (resetCount < maxResetCount) {
//                    nonce = nonce.add(BigInteger.valueOf(1));
//                    resetCount += 1;
//                    log(String.format("sendTransaction reset: count = %s, nonce = %s", String.valueOf(resetCount), String.valueOf(nonce)));
//                    return sendTransaction(fromAddress, ecKeyPair, toAddress, amount, nonce, resetCount++,gasPrice,gasLitmit);
//                }
//            }
            Log.e("Hash>>>>",transactionHash);
            return transactionHash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    /**
     * 获取代币余额
     *
     * @param address
     * @param listener
     */
    public static void getTokenBalanceAsync(final String address, final WalletListener listener) {
        getExecutorService().submit(new Runnable() {
            @Override
            public void run() {
                String num = getTokenBalance(address);
                if (listener != null) {
                    listener.onQueryTokenBalance(num);
                }
            }
        });
    }

    /**
     * 获取版本信息
     *
     * @return
     */
    public static String getClientVersion() {
        String clientVersion = "";
        try {
            Web3ClientVersion web3ClientVersion = getWeb3j().web3ClientVersion().sendAsync().get();
            clientVersion = web3ClientVersion.getWeb3ClientVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log("clientVersion:" + clientVersion);
        return clientVersion;
    }

    /**
     * 获取以太币余额
     *
     * @param address
     * @return
     */
    public static BigInteger getEthBalance(String address) {
        BigInteger balance = BigInteger.ZERO;
        try {
            balance = getWeb3j().ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log("eth-balance:" + EthUtils.toStringNum(balance));
        return balance;
    }


    /**
     * 获取NONCE
     */
    public static BigInteger getNonce(String address) {
        BigInteger nonce = BigInteger.ZERO;
        try {
            EthGetTransactionCount ethGetTransactionCount = getWeb3j().ethGetTransactionCount(
                    address, DefaultBlockParameterName.PENDING).sendAsync().get();
            nonce = ethGetTransactionCount.getTransactionCount();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        log("nonce:" + nonce);
        return nonce;
    }

    public static <T> T queryToken(Class<T> t, String methodName, List<Type> inputParameters,
                                   List<TypeReference<?>> outputParameters, String from, String to) {
        Function function = new Function(
                methodName,//交易的方法名称
                inputParameters,
                outputParameters
        );
        String data = FunctionEncoder.encode(function);
        //智能合约事物
        Transaction transaction = Transaction.createEthCallTransaction(from, to, data);
        EthCall ethCall;
        try {
            ethCall = getWeb3j().ethCall(transaction, DefaultBlockParameterName.LATEST).send();
            List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
            return (T) results.get(0).getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询代币余额
     */
    public static String getTokenBalance(String address) {
        List inputParameters = Arrays.asList(new Address(address));
        List outputParameters = Arrays.asList(new TypeReference<Uint256>() {
        });
        BigInteger banalce = queryToken(BigInteger.class, "balanceOf", inputParameters, outputParameters, address, tokenAddres);
        if (banalce == null) {
            banalce = BigInteger.ZERO;
        }
        String num = EthUtils.toStringNum(banalce);
        log(String.format("address:%s, token-balance:%s", address, num));
        return num;
    }

    /**
     * 查询代币名称
     */
    public static String getTokenName(String address) {
        List inputParameters = new ArrayList<>();
        List outputParameters = Arrays.asList(new TypeReference<Utf8String>() {
        });
        String name = queryToken(String.class, "name", inputParameters, outputParameters, address, tokenAddres);
        log("token-name:" + name);
        return name;
    }

    /**
     * 查询代币符号
     */
    public static String getTokenSymbol(String address) {
        List inputParameters = new ArrayList<>();
        List outputParameters = Arrays.asList(new TypeReference<Utf8String>() {
        });
        String symbol = queryToken(String.class, "symbol", inputParameters, outputParameters, address, tokenAddres);
        log("token-symbol:" + symbol);
        return symbol;
    }

    /**
     * 查询代币精度
     */
    public static BigInteger getTokenDecimals(String address) {
        List inputParameters = new ArrayList<>();
        List outputParameters = Arrays.asList(new TypeReference<Uint256>() {
        });
        BigInteger decimals = queryToken(BigInteger.class, "decimals", inputParameters, outputParameters, address, tokenAddres);
        log("token-decimals:" + decimals);
        return decimals;
    }

    /**
     * 查询代币发行总量
     */
    public static BigInteger getTokenTotalSupply(String address) {
        List inputParameters = new ArrayList<>();
        List outputParameters = Arrays.asList(new TypeReference<Uint256>() {
        });
        BigInteger totalSupply = queryToken(BigInteger.class, "totalSupply", inputParameters, outputParameters, address, tokenAddres);
        log("token-totalSupply:" + totalSupply);
        return totalSupply;
    }

    private static void log(String message) {
        if (DEBUG) {
//            Log.i("wallet", "WalletManager->" + message);
            System.out.println("WalletManager->" + message);
        }
    }



}
