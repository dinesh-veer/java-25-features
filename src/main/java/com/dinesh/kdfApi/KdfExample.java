package com.dinesh.kdfApi;

import javax.crypto.KDF;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import javax.crypto.spec.HKDFParameterSpec;
import java.util.HexFormat;

// Example using HKDF-SHA256 (API names follow JDK 25 docs)
public class KdfExample {
    public static void main(String[] args) throws Exception {


        System.out.println("----------------------------------------------------");

        byte[] ikm = "shared-secret".getBytes();
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        // Obtain a KDF instance (algorithm name per JDK docs)
        KDF kdf = KDF.getInstance("HKDF-SHA256");

        // Build parameters (use the JDK's parameter spec classes)
        var params = HKDFParameterSpec.ofExtract()
                        .addIKM(ikm)
                        .addSalt(salt)
                        .thenExpand("info".getBytes(), 32);

        // Derive a 256-bit AES key
        SecretKey derived = kdf.deriveKey("AES", params);

        System.out.println("Salt: " + HexFormat.of().formatHex(salt));
        System.out.println("Derived: " + HexFormat.of().formatHex(derived.getEncoded()));


        System.out.println("----------------------------------------------------");
    }
}
