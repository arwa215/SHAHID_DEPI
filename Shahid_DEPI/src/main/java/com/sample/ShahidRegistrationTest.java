package com.sample;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShahidRegistrationTest {
    private static final Logger logger = LoggerFactory.getLogger(ShahidRegistrationTest.class);

    @Test
    public void testPasswordTooShort() {
        // Test case where the password is too short.
        String password = "Pass1!";
        boolean isPasswordValid = isPasswordComplex(password);
        Assert.assertFalse(isPasswordValid, "Password should fail due to being too short.");
        logger.info("Password 'Pass1!' failed due to being too short.");
    }

    @Test
    public void testPasswordNoUppercase() {
        // Test case where the password doesn't contain an uppercase letter.
        String password = "password123!";
        boolean isPasswordValid = isPasswordComplex(password);
        Assert.assertFalse(isPasswordValid, "Password should fail due to missing uppercase letter.");
        logger.info("Password 'password123!' failed due to missing uppercase letter.");
    }

    @Test
    public void testPasswordNoLowercase() {
        // Test case where the password doesn't contain a lowercase letter.
        String password = "PASSWORD123!";
        boolean isPasswordValid = isPasswordComplex(password);
        Assert.assertFalse(isPasswordValid, "Password should fail due to missing lowercase letter.");
        logger.info("Password 'PASSWORD123!' failed due to missing lowercase letter.");
    }

    @Test
    public void testPasswordNoSpecialCharacter() {
        // Test case where the password doesn't contain a special character.
        String password = "Password123";
        boolean isPasswordValid = isPasswordComplex(password);
        Assert.assertFalse(isPasswordValid, "Password should fail due to missing special character.");
        logger.info("Password 'Password123' failed due to missing special character.");
    }

    @Test
    public void testPasswordNoNumber() {
        // Test case where the password doesn't contain a number.
        String password = "Password!";
        boolean isPasswordValid = isPasswordComplex(password);
        Assert.assertFalse(isPasswordValid, "Password should fail due to missing number.");
        logger.info("Password 'Password!' failed due to missing number.");
    }

    // Mock method to check password complexity (real test would interact with backend or UI)
    private boolean isPasswordComplex(String password) {
        // Define complexity rules
        if (password.length() < 8) {
            return false;  // Password too short
        }
        if (!password.matches(".*[A-Z].*")) {
            return false;  // No uppercase letter
        }
        if (!password.matches(".*[a-z].*")) {
            return false;  // No lowercase letter
        }
        if (!password.matches(".*[0-9].*")) {
            return false;  // No number
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            return false;  // No special character
        }
        return true;  // All rules passed
    }
}

