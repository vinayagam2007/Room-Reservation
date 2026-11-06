package com.example.dummyportal;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashTest {
    @Test
    void bcryptHashesAndMatches() {
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder(10);
        String hash = enc.encode("123456");
        assertNotEquals("123456", hash);
        assertTrue(hash.startsWith("$2"));
        assertTrue(enc.matches("123456", hash));
        assertFalse(enc.matches("wrong", hash));
    }
}
