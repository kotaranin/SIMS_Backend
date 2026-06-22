/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.sims_backend.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author kotar
 */
public class HashUtils {

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hash(String rawText, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Lepljenje: salt + sirovi tekst
            String combined = salt + rawText;
            byte[] hashBytes = digest.digest(combined.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Greška pri inicijalizaciji SHA-256 algoritma", e);
        }
    }
}
