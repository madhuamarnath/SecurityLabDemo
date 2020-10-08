package com.securitylab.symmetric;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class AESDemo {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		SecretKey key = getKey();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the plaintext message");
		String plainTextMessage = br.readLine();
		byte[] cipherText = encrypt(plainTextMessage, key);
		BigInteger b = new BigInteger(1, cipherText);
		System.out.println("CipherText in Hexadecimal Form:" + b.toString(16));
		System.out.println("Recovered Plain Text Message:" + decrypt(cipherText, key));
	}

	public static SecretKey getKey() throws NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(128);
		SecretKey k = kg.generateKey();
		return k;
	}

	public static byte[] encrypt(String plainTextMessage, SecretKey key) {
		byte[] cipherText = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
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

	public static String decrypt(byte[] cipherTextMessage, SecretKey key) {
		byte[] plainText = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			plainText = cipher.doFinal(cipherTextMessage);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			e.printStackTrace();
		}

		return new String(plainText);
	}

}
