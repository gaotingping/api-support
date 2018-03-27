package com.gtp.apisupport.common;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	private static final String IV = "+1A-2B?3C!4E~5F_";

	public static String decrypt(String cipherText, String publicKey) throws Exception {

		byte[] encrypted = Base64Utils.decode(cipherText);
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		SecretKeySpec keyspec = new SecretKeySpec(publicKey.getBytes("UTF-8"), "AES");
		IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes("UTF-8"));
		cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
		byte[] original = cipher.doFinal(encrypted);
		String originalString = new String(original);
		originalString = originalString.trim();
		return originalString;
	}

	public static String encrypt(String plainText, String publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		int blockSize = cipher.getBlockSize();
		byte[] dataBytes = plainText.getBytes("UTF-8");
		int plaintextLength = dataBytes.length;
		if (plaintextLength % blockSize != 0) {
			plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
		}
		byte[] plaintext = new byte[plaintextLength];
		System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
		SecretKeySpec keyspec = new SecretKeySpec(publicKey.getBytes("UTF-8"), "AES");
		IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes("UTF-8"));
		cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
		byte[] encrypted = cipher.doFinal(plaintext);
		return Base64Utils.encode(encrypted);

	}
}
