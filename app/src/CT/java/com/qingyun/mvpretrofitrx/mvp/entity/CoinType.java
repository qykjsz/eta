package com.qingyun.mvpretrofitrx.mvp.entity;

public class CoinType {
    private int resId;
    private int selectResId;
    private String coinType;
    public static String EOS="EOS";
    public static String ETH="ETH";
    public static String IOST="IOST";
    public static String Tron="Tron";
    public static String BINANCE="BINANCE";
    public static String BOS="BOS";
    public static String COSMOS="COSMOS";
    public static String MOAC="MOAC";
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }


    public CoinType(int resId, int selectResId, String coinType, String name) {
        this.resId = resId;
        this.selectResId = selectResId;
        this.coinType = coinType;
        this.name = name;
    }

    public CoinType(int resId, int selectResId, String coinType) {
        this.resId = resId;
        this.selectResId = selectResId;
        this.coinType = coinType;
    }

    public static String getEOS() {
        return EOS;
    }

    public static void setEOS(String EOS) {
        CoinType.EOS = EOS;
    }

    public static String getETH() {
        return ETH;
    }

    public static void setETH(String ETH) {
        CoinType.ETH = ETH;
    }

    public static String getIOST() {
        return IOST;
    }

    public static void setIOST(String IOST) {
        CoinType.IOST = IOST;
    }

    public static String getTron() {
        return Tron;
    }

    public static void setTron(String tron) {
        Tron = tron;
    }

    public static String getBINANCE() {
        return BINANCE;
    }

    public static void setBINANCE(String BINANCE) {
        CoinType.BINANCE = BINANCE;
    }

    public static String getBOS() {
        return BOS;
    }

    public static void setBOS(String BOS) {
        CoinType.BOS = BOS;
    }

    public static String getCOSMOS() {
        return COSMOS;
    }

    public static void setCOSMOS(String COSMOS) {
        CoinType.COSMOS = COSMOS;
    }

    public static String getMOAC() {
        return MOAC;
    }

    public static void setMOAC(String MOAC) {
        CoinType.MOAC = MOAC;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getSelectResId() {
        return selectResId;
    }

    public void setSelectResId(int selectResId) {
        this.selectResId = selectResId;
    }
}
