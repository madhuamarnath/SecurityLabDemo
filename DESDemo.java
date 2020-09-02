package com.securitylab.symmetric;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class DESDemo {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		Key key = getKey();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the plaintext message");
		String plainTextMessage = br.readLine();
		byte[] cipherText = encrypt(plainTextMessage, key);
		BigInteger b = new BigInteger(1, cipherText);
		System.out.println("CipherText in Hexadecimal Form:" + b.toString(16));
		System.out.println("Recovered Plain Text Message:" + decrypt(cipherText, key));
	}

	public static Key getKey() throws NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		kg.init(56);
		Key k = kg.generateKey();
		return k;
	}

	public static byte[] encrypt(String plainTextMessage, Key key) {
		byte[] cipherText = null;
		try {
			Cipher cipher = Cipher.getInstance("DES");
			byte[] input = plainTextMessage.getBytes();
			BigInteger b = new BigInteger(1, input);
			System.out.println("PlainText in the hexadecimal form: " + b.toString(16));
			cipher.init(Cipher.ENCRYPT_MODE, key);
			cipherText = cipher.doFinal(input);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			e.printStackTrace();
		}
		return cipherText;
	}

	public static String decrypt(byte[] cipherTextMessage, Key key) {
		byte[] plainText = null;
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			plainText = cipher.doFinal(cipherTextMessage);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			e.printStackTrace();
		}
		return new String(plainText);
	}

}
