package com.LiveScore.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    // Check if a string is null or empty
    public boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Validate if a string is alphabetic (letters only)
    public boolean isAlphabetic(String str) {
        return str.matches("[a-zA-Z]+");
    }

    // Validate if the username is alphanumeric and starts with a letter, length between 4-20
    public boolean isAlphanumericStartingWithLetter(String str) {
        return str.matches("^[A-Za-z][A-Za-z0-9]{3,19}$");
    }

    // Validate if the email format is correct
    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Validate if the password is strong: at least 8 characters, 1 uppercase, 1 lowercase, 1 digit, and 1 special character
    public boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
 // Validate if the string contains both letters and digits (e.g., "Kylian Mbappe-4")
    public boolean containsLettersAndDigits(String str) {
        if (isNullOrEmpty(str)) return false;
        return str.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\s-]*\\d+$");
    }
 // Validate if a string contains only valid team name characters (letters, spaces, hyphens)
    public boolean isValidTeamName(String str) {
        return !isNullOrEmpty(str) && str.matches("^[A-Za-z\\s-]+$");
    }

    // Validate if a result string is in the format like "W-W-L-D" (only W, L, D with hyphens)
    public boolean isValidResultsFormat(String str) {
        return !isNullOrEmpty(str) && str.matches("^([WLD](-[WLD])*)$");
    }


}
