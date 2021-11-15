package com.securitylab.asymmetric;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Scanner;

public class DSSDemo {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String inputMessage = sc.nextLine();
		Signature signAlgorithm = Signature.getInstance("SHA256withDSA");
		KeyPair kp = getKeys();
		byte[] signBytes = generateSignature(inputMessage, kp.getPrivate(), signAlgorithm);
		BigInteger signedMessage = new BigInteger(1, signBytes);
		System.out.println("Digital Signature generated by sender:" + signedMessage.toString(16));

		if (verifySignature(inputMessage, signBytes, kp.getPublic(), signAlgorithm))
			System.out.println("Signature is verified");
		else
			System.out.println("Signature is not matching");
		sc.close();
	}

	public static byte[] generateSignature(String inputMessage, PrivateKey privateKey, Signature signAlgorithm) {
		byte[] sigBytes = null;
		try {
			signAlgorithm.initSign(privateKey);
			byte[] message = inputMessage.getBytes();
			signAlgorithm.update(message);
			sigBytes = signAlgorithm.sign();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sigBytes;
	}

	public static boolean verifySignature(String inputMessage, byte[] sigBytes, PublicKey publicKey,
			Signature signAlgorithm) {
		boolean result = true;
		try {
			signAlgorithm.initVerify(publicKey);
			signAlgorithm.update(inputMessage.getBytes());
			result = signAlgorithm.verify(sigBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static KeyPair getKeys() throws NoSuchAlgorithmException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
		kpg.initialize(512);
		KeyPair kp = kpg.genKeyPair();
		return kp;
	}

}