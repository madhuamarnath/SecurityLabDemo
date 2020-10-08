package com.securitylab.asymmetric;

import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellmanDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the primitive root");
		BigInteger g = sc.nextBigInteger();
		System.out.println("Enter the prime number");
		BigInteger q = sc.nextBigInteger();
		System.out.println("Enter the private key of User A");
		BigInteger xA = sc.nextBigInteger();
		System.out.println("Enter the private key of User B");
		BigInteger xB = sc.nextBigInteger();
		BigInteger yA = computePublicKey(g, xA, q);
		BigInteger yB = computePublicKey(g, xB, q);
		System.out.println("Public Key of A: " + yA);
		System.out.println("Public Key of B: " + yB);
		BigInteger K1 = computeSharedKey(xA, yB, q);
		BigInteger K2 = computeSharedKey(xB, yA, q);
		System.out.println("Shared Key Computed by User A = " + K1);
		System.out.println("Shared Key Computed by User B = " + K2);
		sc.close();
	}

	public static BigInteger computePublicKey(BigInteger g, BigInteger xA, BigInteger q) {
		BigInteger Y = g.modPow(xA, q);
		return Y;
	}

	public static BigInteger computeSharedKey(BigInteger X, BigInteger Y, BigInteger q) {
		BigInteger K = Y.modPow(X, q);
		return K;
	}

}
