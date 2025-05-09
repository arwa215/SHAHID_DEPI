package com.sample;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShahidSecurityTest {
    private static final Logger logger = LoggerFactory.getLogger(ShahidSecurityTest.class);

    // Mock user credentials
    private static final String USER_EMAIL = "user@shahid.net";
    private static final String OLD_PASSWORD = "oldPassword123";
    private static final String NEW_PASSWORD = "newPassword456";

    private AuthenticationService authService = new AuthenticationService();

    @Test
    public void testSessionManagementAfterPasswordReset() {
        // Step 1: User logs in with old password
        boolean loginSuccessOldPassword = authService.login(USER_EMAIL, OLD_PASSWORD);
        Assert.assertTrue(loginSuccessOldPassword, "Login should be successful with old password.");

        // Step 2: Reset the user's password
        boolean passwordReset = authService.resetPassword(USER_EMAIL, OLD_PASSWORD, NEW_PASSWORD);
        Assert.assertTrue(passwordReset, "Password reset should be successful.");

        // Step 3: Try to use the old session (user should be logged out)
        boolean sessionValidAfterReset = authService.isSessionValid(USER_EMAIL);
        Assert.assertFalse(sessionValidAfterReset, "Session should be invalidated after password reset.");

        // Step 4: Log in with the new password
        boolean loginSuccessNewPassword = authService.login(USER_EMAIL, NEW_PASSWORD);
        Assert.assertTrue(loginSuccessNewPassword, "Login should be successful with the new password.");

        // Log results
        logger.info("Password reset and session management test passed.");
    }

    // Mock AuthenticationService to simulate authentication actions
    private static class AuthenticationService {
        private boolean sessionValid = false;

        // Simulate login
        public boolean login(String email, String password) {
            if (password.equals(OLD_PASSWORD)) {
                sessionValid = true;
                return true; // Simulate success for old password
            } else if (password.equals(NEW_PASSWORD)) {
                sessionValid = true;
                return true; // Simulate success for new password
            }
            return false; // Simulate failure for incorrect passwords
        }

        // Simulate password reset
        public boolean resetPassword(String email, String oldPassword, String newPassword) {
            if (oldPassword.equals(OLD_PASSWORD)) {
                return true; // Simulate successful password reset
            }
            return false; // Simulate failure if old password doesn't match
        }

        // Check if session is valid
        public boolean isSessionValid(String email) {
            return sessionValid;
        }
    }
}

