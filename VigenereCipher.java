import java.util.*;
import java.io.*;

public class VigenereCipher {
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();
        int keyLength = key.length();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                int charValue = c - 'A';
                int keyValue = key.charAt(i % keyLength) - 'A';
                int encrypted = (charValue + keyValue) % 26;
                result.append((char) (encrypted + 'A'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();
        int keyLength = key.length();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                int charValue = c - 'A';
                int keyValue = key.charAt(i % keyLength) - 'A';
                int decrypted = (charValue - keyValue + 26) % 26;
                result.append((char) (decrypted + 'A'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
    
            // Input plaintext
            System.out.print("Enter the plaintext: ");
            String plaintext = scanner.nextLine();
    
            // Input key
            System.out.print("Enter the key: ");
            String key = scanner.nextLine();
    
            // Encrypt the plaintext
            String encryptedText = VigenereCipher.encrypt(plaintext, key);
            System.out.println("Encrypted Text: " + encryptedText);
    
            // Decrypt the ciphertext
            String decryptedText = VigenereCipher.decrypt(encryptedText, key);
            System.out.println("Decrypted Text: " + decryptedText);
    
            scanner.close();
        }
    }    
}
