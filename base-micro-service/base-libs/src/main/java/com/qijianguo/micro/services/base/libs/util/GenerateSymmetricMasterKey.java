package com.qijianguo.micro.services.base.libs.util;

import org.springframework.util.Assert;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class GenerateSymmetricMasterKey {

    private static final String keyDir = System.getProperty("java.io.tmpdir");

    private static final String keyName = "secret.key";

    public static void main(String[] args) throws Exception {

        //Generate symmetric 256 bit AES key.
        KeyGenerator symKeyGenerator = KeyGenerator.getInstance("AES");
        symKeyGenerator.init(256);
        SecretKey symKey = symKeyGenerator.generateKey();

        //Save key.
        saveSymmetricKey(keyDir, symKey);

        //Load key.
        SecretKey symKeyLoaded = loadSymmetricAESKey(keyDir, "AES");
        Assert.isTrue(Arrays.equals(symKey.getEncoded(), symKeyLoaded.getEncoded()), "");

    }

    public static void saveSymmetricKey(String path, SecretKey secretKey) throws IOException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(secretKey.getEncoded());
        FileOutputStream keyfos = new FileOutputStream(path + "/" + keyName);
        keyfos.write(x509EncodedKeySpec.getEncoded());
        keyfos.close();
    }

    public static SecretKey loadSymmetricAESKey(String path, String algorithm)

            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        //Read private key from file.
        File keyFile = new File(path + "/" + keyName);
        FileInputStream keyfis = new FileInputStream(keyFile);
        byte[] encodedPrivateKey = new byte[(int) keyFile.length()];
        keyfis.read(encodedPrivateKey);
        keyfis.close();
        //Generate secret key.
        return new SecretKeySpec(encodedPrivateKey, "AES");

    }

}