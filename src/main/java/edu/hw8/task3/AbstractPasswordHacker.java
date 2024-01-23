package edu.hw8.task3;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractPasswordHacker {
    protected static final int MD5_HASH_LENGTH = 32;
    protected final Map<String, String> leakedPasswords;
    protected final Map<String, String> hashLoginMap;
    protected final Map<String, String> results;
    protected final String alphabet;
    protected final int minPasswordsLength;
    protected final int maxPasswordsLength;
    private static final int HASH_CONVERSION_CONSTANT = 16;

    public AbstractPasswordHacker(
        Map<String, String> leakedPasswords,
        String alphabet,
        int minPasswordsLength,
        int maxPasswordsLength
    ) {
        if (leakedPasswords == null || alphabet == null || minPasswordsLength > maxPasswordsLength) {
            throw new IllegalArgumentException();
        }
        this.leakedPasswords = leakedPasswords;
        this.hashLoginMap = getHashLoginMap(leakedPasswords);
        this.results = new HashMap<>();
        this.alphabet = alphabet;
        this.minPasswordsLength = minPasswordsLength;
        this.maxPasswordsLength = maxPasswordsLength;
    }

    public abstract Map<String, String> hack();

    protected Map<String, String> getHashLoginMap(Map<String, String> leakedPasswords) {
        if (leakedPasswords == null) {
            throw new IllegalArgumentException();
        }
        return leakedPasswords.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    protected String getHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] hashBytes = md.digest();
            String hash = new BigInteger(1, hashBytes).toString(HASH_CONVERSION_CONSTANT);
            if (hash.length() != MD5_HASH_LENGTH) {
                hash = "0".repeat(MD5_HASH_LENGTH - hash.length()) + hash;
            }
            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    protected void handlePassword(String password, Map<String, String> results) {
        String hash = getHash(password);
        if (hashLoginMap.containsKey(hash)) {
            results.put(hashLoginMap.get(hash), password);
        }
    }

}
