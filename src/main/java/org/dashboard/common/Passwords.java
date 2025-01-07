package org.dashboard.common;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Passwords {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    public static class  Password implements Serializable {
        private final byte[] salt;
        private final byte[] hash;

        public Password(byte[] salt, byte[] hash) {
            this.salt = salt;
            this.hash = hash;
        }

        public Password(String salt, String hash) {
            this(hexToBytes(salt), hexToBytes(hash));
        }

        public byte[] getRawSalt() {
            return salt;
        }

        public String getSalt() {
            return bytesToHex(salt);
        }

        public byte[] getRawHash() {
            return hash;
        }

        public String getHash() {
            return bytesToHex(hash);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);

            if (hex.length() == 1) {
                hexString.append('0');
            }

            hexString.append(hex);
        }

        return hexString.toString();
    }

    private static byte[] hexToBytes(String hex) {
        byte[] bytes = new byte[hex.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }

        return bytes;
    }

    private static byte[] getNewSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[20];
        random.nextBytes(salt);
        return salt;
    }

    public static Password encodeNew(String password) {
        byte[] salt = getNewSalt();

        return encode(password, salt);
    }

    public static Password encode(String password, String salt) {
        return encode(password, hexToBytes(salt));
    }

    public static Password encode(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            Password result = new Password(salt, factory.generateSecret(spec).getEncoded());
            password = null;
            return result;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verify(String password, Password stored) {
        Password encoded = encode(password, stored.getSalt());
        password = null;

        return MessageDigest.isEqual(encoded.getRawHash(), stored.getRawHash());
    }
}

