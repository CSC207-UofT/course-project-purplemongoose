package util;

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
    public abstract String hash(String input, String salt);

}
