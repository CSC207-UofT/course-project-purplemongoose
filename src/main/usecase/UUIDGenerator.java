package usecase;

import java.util.Random;

public class UUIDGenerator {
    private final char[] characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    Random gen = new Random();
    int idLength = 5;

    public String getBase36() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<this.idLength; i++) {
            sb.append(characters[gen.nextInt(36)]);
        }
        return sb.toString();
    }

    public String getBase62() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<this.idLength; i++) {
            sb.append(characters[gen.nextInt(62)]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        UUIDGenerator gen = new UUIDGenerator();

        System.out.println(gen.getBase36());
        System.out.println(gen.getBase62());
    }
}
