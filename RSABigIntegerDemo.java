package com.securitylab;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;

public class RSABigIntegerDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		System.out.println("Enter Prime p:");
		BigInteger p = new BigInteger(br.readLine());
		System.out.println("Enter Prime q:");
		BigInteger q = new BigInteger(br.readLine());
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		BigInteger n = p.multiply(q);
		System.out.println("Enter Encryption Constant e:");
		BigInteger e = new BigInteger(br.readLine());
		BigInteger d = e.modInverse(phi);
		System.out.println("Enter Plaintext Message M:");
		BigInteger M = new BigInteger(br.readLine());
		BigInteger C = encrypt(M, e, n);
		System.out.println("Encrypted Message C=" + C);
		BigInteger M2 = decrypt(C, d, n);
		System.out.println("Decrypted Message M=" + M2);
	}

	public static BigInteger encrypt(BigInteger M, BigInteger e, BigInteger n) {
		BigInteger C = M.modPow(e, n);
		return C;
	}

	public static BigInteger decrypt(BigInteger C, BigInteger d, BigInteger n) {
		BigInteger M = C.modPow(d, n);
		return M;
	}

}
