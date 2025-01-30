import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public static class ElGamal {
    private BigInteger p, g, x, y;
    private Random rand = new Random();

    public ElGamal(int bitLength) {
        generateKeys(bitLength);
    }

    private void generateKeys(int bitLength) {
        p = BigInteger.probablePrime(bitLength, random);

        g = BigInteger.valueOf(2);
        while (!isGenerator(g, p)) {
            g = g.add(BigInteger.ONE);
        }

        // Generate private key x
        x = new BigInteger(bitLength - 1, rand);
        
        // Calculate public key y = g^x mod p
        y = g.modPow(x, p);
    }

    private boolean isGenerator(BigInteger g, BigInteger p) {
        BigInteger pMinusOne = p.subtract(BigInteger.ONE);
        BigInteger factor = pMinusOne.divide(BigInteger.valueOf(2));
        return !g.modPow(factor, p).equals(BigInteger.ONE);
    }

    public BigInteger[] encrypt(BigInteger message) {
        // Generate random k
        BigInteger k = new BigInteger(p.bitLength() - 1, rand);
            
            // Calculate c1 = g^k mod p
            BigInteger c1 = g.modPow(k, p);
            
            // Calculate c2 = m * y^k mod p
            BigInteger c2 = message.multiply(y.modPow(k, p)).mod(p);
            
            return new BigInteger[]{c1, c2};
        }

        public BigInteger decrypt(BigInteger[] cipher) {
            BigInteger c1 = cipher[0];
            BigInteger c2 = cipher[1];
            
            // Calculate s = c1^x mod p
            BigInteger s = c1.modPow(x, p);
            
            // Calculate m = c2 * s^(-1) mod p
            return c2.multiply(s.modInverse(p)).mod(p);
        }
    
    }
