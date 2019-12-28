package com.ylj.sky.boot.common.util;


import org.junit.Test;

class RSAUtilsTest {


    public static void main(String[] args) throws Exception{
         String publicKeyFile = "D:\\key\\sky_boot_rsa.pub";
         String privateKeyFile ="D:\\key\\sky_boot_rsa";
        RSAUtils.generateKey(publicKeyFile,privateKeyFile,"sky_boot",2048);
    }
   /* @Test
    void getPublicKey() throws Exception{
        System.out.println(RSAUtils.getPublicKey(publicKeyFile));
    }

    @Test
    void getPrivateKey() throws Exception{
        System.out.println(RSAUtils.getPrivateKey(privateKeyFile));
    }*/

   /* @Test
    void generateKey() throws Exception{
        RSAUtils.generateKey(publicKeyFile,privateKeyFile,"sky_boot",2048);
    }*/
}