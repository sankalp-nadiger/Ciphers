import java.util.Scanner;
public class AffineCipher {
        public static String encrypt(String text, int a, int b) {
            if (gcd(a, 26) != 1) {
                throw new IllegalArgumentException("'a' must be coprime with 26");
            }

            StringBuilder result = new StringBuilder();
            text = text.toUpperCase();

            for (char c : text.toCharArray()) {
                if (Character.isLetter(c)) {
                    int x = c - 'A';
                    int encrypted = (a * x + b) % 26;
                    result.append((char) (encrypted + 'A'));
                } else {
                    result.append(c);
                }
            }
            return result.toString();
        }

        public static String decrypt(String text, int a, int b) {
            if (gcd(a, 26) != 1) {
                throw new IllegalArgumentException("'a' must be coprime with 26");
            }

            StringBuilder result = new StringBuilder();
            text = text.toUpperCase();
            int aInverse = modInverse(a, 26);

            for (char c : text.toCharArray()) {
                if (Character.isLetter(c)) {
                    int x = c - 'A';
                    int decrypted = (aInverse * (x - b + 26)) % 26;
                    result.append((char) (decrypted + 'A'));
                } else {
                    result.append(c);
                }
            }
            return result.toString();
        }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Affine Cipher:");
        
        System.out.print("Enter text: ");
        String text = scanner.nextLine();
        
        System.out.print("Enter value of a (must be coprime with 26): ");
        int a = scanner.nextInt();
        
        System.out.print("Enter value of b: ");
        int b = scanner.nextInt();
        
        scanner.nextLine();
        
        String encrypted = AffineCipher.encrypt(text, a, b);
        System.out.println("Encrypted: " + encrypted);
        
        String decrypted = AffineCipher.decrypt(encrypted, a, b);
        System.out.println("Decrypted: " + decrypted);
    }
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        throw new IllegalArgumentException("Modular inverse does not exist");
    }    
}
