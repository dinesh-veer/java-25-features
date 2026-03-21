package com.dinesh.kdfApi;

import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class KdfDemo {
    public static void main(String[] args) throws Exception {

        System.out.println("----------------------------------------------------");

        char[] password = "s3cr3t-password".toCharArray();

        // Generate a random salt
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        // Parameters
        int iterations = 100_000;
        int keyLenBits = 256; // 256-bit key

        // Derive key bytes using PBKDF2WithHmacSHA256
        byte[] derived = derivePBKDF2(password, salt, iterations, keyLenBits);

        // Wrap as a SecretKey for use with AES
        SecretKey secretKey = new SecretKeySpec(derived, "AES");

        System.out.println("Salt: " + HexFormat.of().formatHex(salt));
        System.out.println("Derived key: " + HexFormat.of().formatHex(secretKey.getEncoded()));


        System.out.println("----------------------------------------------------");
    }

    private static byte[] derivePBKDF2(char[] password, byte[] salt, int iterations, int keyLenBits)
            throws InvalidKeySpecException {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLenBits);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKey key = skf.generateSecret(spec);
            return key.getEncoded();
        } catch (InvalidKeySpecException e) {
            throw e;
        } catch (Exception e) {
            // wrap other exceptions as InvalidKeySpecException for simplicity
            throw new InvalidKeySpecException("KDF failed", e);
        }
    }
}
