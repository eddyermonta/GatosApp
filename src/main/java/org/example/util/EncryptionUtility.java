package org.example.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptionUtility {
    /**
     * @return genera llave de encriptacion
     */
    private static SecretKey generateEncryptionKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ConfigConstants.AES_ALGORITHM);
        keyGen.init(256); // Tamano de la clave en bits
        return keyGen.generateKey();
    }
    /**
     * @param apiKey apikey de catAPI
     * @return encriptacion de apiKey
     */
    private static String encrypt(String apiKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ConfigConstants.AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, generateEncryptionKey());
        byte[] encryptedBytes = cipher.doFinal(apiKey.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    /**
     *
     * @param encryptedApiKey codigo encriptado
     * @param key             llave de encriptacion
     * @return                codigo desencriptado
     */
    private static String decrypt(String encryptedApiKey, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ConfigConstants.AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedApiKey));
        return new String(decryptedBytes);
    }
}
