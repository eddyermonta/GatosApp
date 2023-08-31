package org.example.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptionUtility {
    public static SecretKey generateEncryptionKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ConfigConstants.AES_ALGORITHM);
        keyGen.init(256); // Tamano de la clave en bits
        return keyGen.generateKey();
    }

    public static String encrypt(String apiKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ConfigConstants.AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, generateEncryptionKey());
        byte[] encryptedBytes = cipher.doFinal(apiKey.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedApiKey, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ConfigConstants.AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedApiKey));
        return new String(decryptedBytes);
    }
}
