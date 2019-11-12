package com.develop.wallet.eth;

import org.web3j.crypto.WalletFile;

import java.io.Serializable;

/**
 * @author Angus
 */
public class Wallet implements Serializable {
    private String mnemonic;
    private String address;
    private String privateKey;
    private String publicKey;
    private String keystore;
    private String walletName;
    private String coinType;
    private Boolean isMakeCopy;



    public Boolean getMakeCopy() {
        if (isMakeCopy==null){
            isMakeCopy=false;
        }
        return isMakeCopy;
    }

    public void setMakeCopy(Boolean makeCopy) {
        isMakeCopy = makeCopy;
    }

    public String getCoinType() {
        return "ETH";
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    /**
     * Path to wallet file.
     */
    private String filename;

    public Wallet(String mnemonic, String address) {
        this.mnemonic = mnemonic;
        this.address = address;
    }

    public Wallet(String mnemonic, String address, String privateKey, String publicKey) {
        this.mnemonic = mnemonic;
        this.address = address;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }



    public Wallet(String mnemonic, String address, String privateKey, String publicKey, String keystore) {
        this(mnemonic, address, privateKey, publicKey);
        this.keystore = keystore;
    }



    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getKeystore() {
        return keystore;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
