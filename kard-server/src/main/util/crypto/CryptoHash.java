package util.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * A class for cryptographic hashing of text.
 */
public abstract class CryptoHash {
    /**
     * The hash function for string salt input.
     *
     * @param salt the salt for the hash.
     * @param input the input to the hashing algorithm
     * @return the hashed input.
     */
    public String hash(String input, String salt) {
        SecureRandom rng = null;
        try {
            rng = SecureRandom.getInstance("SHA1PRNG"); // makes SecureRandom Deterministic.
            // Thanks to: https://stackoverflow.com/questions/27341294/get-deterministic-values-from-securerandom
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        rng.setSeed(salt.getBytes());

        byte[] new_salt = new byte[16];
        rng.nextBytes(new_salt);

        return hash(input, new_salt);
    }

    /**
     * The hash function for bytes salt input.
     *
     * @param salt the salt for the hash.
     * @param input the input to the hashing algorithm
     * @return the hashed input.
     */
    public abstract String hash(String input, byte[] salt);

}
