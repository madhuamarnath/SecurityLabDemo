package com.securitylab;

import java.util.Scanner;

class CeaserCipher {
	public String encryption(String plainText, int key) {
		String cipherText = "";
		char[] plainTextArray = plainText.toUpperCase().toCharArray();
		char[] cipherTextArray = new char[plainTextArray.length];
		for (int i = 0; i < plainTextArray.length; i++) {
			/*
			 * cipherTextArray[i] = (char) (((int) plainTextArray[i] + key - 65) % 26 + 65);
			 */
			cipherTextArray[i] = (char) (((int) Math.floorMod(plainTextArray[i] + key - 65, 26)) + 65);
		}
		cipherText = String.valueOf(cipherTextArray);
		return cipherText;
	}

	public String decryption(String cipherText, int key) {
		String plainText = "";
		char[] cipherTextArray = cipherText.toCharArray();
		char[] plainTextArray = new char[cipherTextArray.length];
		for (int i = 0; i < cipherTextArray.length; i++) {
			/*
			 * plainTextArray[i] = (char) (((int) cipherTextArray[i] - key - 65) % 26 + 65);
			 * Negative value produces wrong value
			 */
			plainTextArray[i] = (char) (((int) Math.floorMod(cipherTextArray[i] - key - 65, 26)) + 65);
		}
		plainText = String.valueOf(plainTextArray).toLowerCase();
		return plainText;
	}

}

public class CeaserCipherDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CeaserCipher ceaserCipher = new CeaserCipher();
		Scanner scanner = new Scanner(System.in);
		String plainText = scanner.nextLine();
		int key = scanner.nextInt();
		String cipherText = ceaserCipher.encryption(plainText, key);
		System.out.println("CipherText=" + cipherText);
		String recoveredPlainText = ceaserCipher.decryption(cipherText, key);
		System.out.println("PlainText=" + recoveredPlainText);
		scanner.close();
	}

}
