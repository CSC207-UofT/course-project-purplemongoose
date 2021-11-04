package usecase;

import java.util.Random;

/**
 * Class used to generate human-readable UUIDs
 */
public class UUIDGenerator {
    private final char[] characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    Random gen = new Random();
    int idLength = 5; // length of the UUID

    /**
     * Generates a UUID using 36 characters; 0-9, a-z
     * @return the generated UUID
     */
    public String getBase36() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<this.idLength; i++) {
            sb.append(characters[gen.nextInt(36)]);
        }
        return sb.toString();
    }

    /**
     * Generates a UUID using all 62 characters; 0-9, a-z, A-Z
     * @return the generated UUID
     */
    public String getBase62() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<this.idLength; i++) {
            sb.append(characters[gen.nextInt(62)]);
        }
        return sb.toString();
    }
}
