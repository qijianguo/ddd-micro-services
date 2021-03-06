package com.qijianguo.micro.services.base.libs.util;

import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class GenerateAsymmetricMasterKey {

    private static final String keyDir  = System.getProperty("java.io.tmpdir");

    private static final SecureRandom srand = new SecureRandom();

    public static void main(String[] args) throws Exception {

        // Generate RSA key pair of 1024 bits
        KeyPair keypair = genKeyPair("RSA", 1024);

        // Save to file system
        saveKeyPair(keyDir, keypair);

        // Loads from file system
        KeyPair loaded = loadKeyPair(keyDir, "RSA");

        // Sanity check
        Assert.isTrue(Arrays.equals(keypair.getPublic().getEncoded(), loaded.getPublic().getEncoded()), "");

        Assert.isTrue(Arrays.equals(keypair.getPrivate().getEncoded(), loaded.getPrivate().getEncoded()), "");

    }

    public static KeyPair genKeyPair(String algorithm, int bitLength) throws NoSuchAlgorithmException {

        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(algorithm);

        keyGenerator.initialize(1024, srand);

        return keyGenerator.generateKeyPair();

    }

    public static void saveKeyPair(String dir, KeyPair keyPair) throws IOException {

        PrivateKey privateKey = keyPair.getPrivate();

        PublicKey publicKey = keyPair.getPublic();

        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(

            publicKey.getEncoded());

        FileOutputStream fos = new FileOutputStream(dir + "/public.key");

        fos.write(x509EncodedKeySpec.getEncoded());

        fos.close();

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(

            privateKey.getEncoded());

        fos = new FileOutputStream(dir + "/private.key");

        fos.write(pkcs8EncodedKeySpec.getEncoded());

        fos.close();

    }

    public static KeyPair loadKeyPair(String path, String algorithm)

        throws IOException, NoSuchAlgorithmException,

        InvalidKeySpecException {

        // read public key from file

        File filePublicKey = new File(path + "/public.key");

        FileInputStream fis = new FileInputStream(filePublicKey);

        byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];

        fis.read(encodedPublicKey);

        fis.close();

        // read private key from file

        File filePrivateKey = new File(path + "/private.key");

        fis = new FileInputStream(filePrivateKey);

        byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];

        fis.read(encodedPrivateKey);

        fis.close();

        // Convert them into KeyPair

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(

            encodedPublicKey);

        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(

            encodedPrivateKey);

        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        return new KeyPair(publicKey, privateKey);

    }

}