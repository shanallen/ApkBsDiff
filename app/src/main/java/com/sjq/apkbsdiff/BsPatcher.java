package com.sjq.apkbsdiff;

public class BsPatcher {
    static {
        System.loadLibrary("native-lib");
    }

    /**安装包
     * 合成
     * @param oldApk  旧版本安装包1.1.1版本
     * @param patch   差分包Patch文件
     * @param output  合成后新版本apk的输出路径
     */
    public native static void bsPatch(String oldApk,String patch,String output);
}
