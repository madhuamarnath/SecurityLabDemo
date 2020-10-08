package com.securitylab.authentication;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MessageDigestDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the message");
		String message = sc.nextLine();
		byte[] hash = generateMessagedigest(message);
		BigInteger b = new BigInteger(1, hash);
		String hashValue = b.toString(16);
		System.out.println("MessageDigest in HexaDecimal Format: " + hashValue);
	}

	public static byte[] generateMessagedigest(String message) {
		byte[] hash = null;
		byte[] input = message.getBytes();
		System.out.println("Message: " + new String(input));
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(input);
			hash = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}

}
