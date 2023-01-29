package com.flytodata.console.comm.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomAESKeyGen {

    public static String generate(final int keyLen) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keyLen);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return DatatypeConverter.printHexBinary(encoded).toLowerCase();
    }

    public static String generate2(final int keyLen) throws NoSuchAlgorithmException {

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[keyLen/8];
        random.nextBytes(bytes);
        return DatatypeConverter.printHexBinary(bytes).toLowerCase();
    }

    public static String generateBase64(final int keyLen) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keyLen);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        String key = DatatypeConverter.printHexBinary(encoded).toLowerCase();
        byte[] base64Key = Base64.encodeBase64(key.getBytes());
        return new String(base64Key);
    }

    public static String generate2Base64(final int keyLen) throws NoSuchAlgorithmException {

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[keyLen/8];
        random.nextBytes(bytes);
        String key =  DatatypeConverter.printHexBinary(bytes).toLowerCase();
        byte[] base64Key = Base64.encodeBase64(key.getBytes());
        return new String(base64Key);
    }

}
