package util.crypto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A class for hashing using the SHA256 algorithm.
 *
 * Created by referencing https://www.geeksforgeeks.org/cryptographic-hash-function-in-java/ as well
 * as java documentation.
 */
public class SHA256Hash extends CryptoHash{
    @Override
    public String hash(String input, byte[] salt) {
        ByteArrayOutputStream inputBytes = new ByteArrayOutputStream();

        try {
            inputBytes.write(salt); // include salt
            inputBytes.write(input.getBytes());
            byte[] hashInput = inputBytes.toByteArray();

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashOutput = digest.digest(hashInput);

            StringBuilder hashedString = new StringBuilder();
            for (byte i : hashOutput)
                hashedString.append(Integer.toHexString(0xff & i)); // & is for when i is negative.

            return hashedString.toString();

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
