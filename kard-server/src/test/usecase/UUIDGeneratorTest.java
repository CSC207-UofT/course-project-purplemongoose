package usecase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.UUIDGenerator;

import static org.junit.jupiter.api.Assertions.*;

class UUIDGeneratorTest {
    UUIDGenerator gen;

    @BeforeEach
    void setUp() {
        gen = new UUIDGenerator();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("base36 uuid not equal")
    void getBase36() {
        String first = gen.getBase36();
        String second = gen.getBase36();

        assertNotEquals(first, second);
    }

    @Test
    @DisplayName("base62 uuid not equal")
    void getBase62() {
        String first = gen.getBase62();
        String second = gen.getBase62();

        assertNotEquals(first, second);
    }
}