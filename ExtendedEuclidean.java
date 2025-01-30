import java.util.*;
import java.io.*;
public class ExtendedEuclidean {
    static class Result {
        int gcd, x, y;
        Result(int gcd, int x, int y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }
    }
    public static Result compute(int a, int b) {
        if (a == 0) {
            return new Result(b, 0, 1);
        }
        Result temp = compute(b % a, a);
        int x = temp.y - (b / a) * temp.x;
        int y = temp.x;

        return new Result(temp.gcd, x, y);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number (a): ");
        int a = sc.nextInt();

        System.out.print("Enter second number (b): ");
        int b = sc.nextInt();

        // Compute Extended Euclidean result
        Result result = compute(a, b);
        System.out.println("\nGCD of " + a + " and " + b + " is: " + result.gcd);
        System.out.println("Coefficients x and y: " + result.x + ", " + result.y);

        // Verification: a*x + b*y should equal GCD(a, b)
        System.out.println("Verification: " + (a * result.x + b * result.y) + " = " + result.gcd);
    }
}
