package com.securitylab.rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

//import com.sun.crypto.provider.SunJCE;

public class RSADemo {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException {
		KeyPair kp = getKeys();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the plaintext message");
		String plainTextMessage = br.readLine();
		byte[] cipherText = encrypt(plainTextMessage, kp.getPublic());
		System.out.println("CipherText in the hexadecimal form: " + new BigInteger(1, cipherText).toString(16));
		byte[] recoveredPlainText = decrypt(cipherText, kp.getPrivate());
		System.out.println("Recovered PlainText : " + new String(recoveredPlainText));
	}

	public static KeyPair getKeys() throws NoSuchAlgorithmException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair kp = kpg.genKeyPair();
		return kp;
	}

	public static byte[] encrypt(String plainTextMessage, Key publicKey) {
		byte[] cipherText = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
			byte[] input = plainTextMessage.getBytes();
			BigInteger b = new BigInteger(1, input);
			System.out.println("PlainText in the hexadecimal form: " + b.toString(16));
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			cipherText = cipher.doFinal(input);
			// System.out.println(new String(cipherText));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			e.printStackTrace();
		}
		return cipherText;
	}

	public static byte[] decrypt(byte[] cipherText, Key privateKey) {
		byte[] plainText = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			plainText = cipher.doFinal(cipherText);
		} catch (NullPointerException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException
				| NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		return plainText;
	}

}
