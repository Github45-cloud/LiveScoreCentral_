package com.LiveScore.util;

public class EncryptTest {
    public static void main(String[] args) {
        String username = "admin";
        String password = "Adminpass1!";
        String encrypted = PasswordUtil.encrypt(username, password);
        System.out.println("Encrypted password: " + encrypted);
    }
}
