package com.cary.stq.utils;

import java.net.InetAddress;
import java.security.SecureRandom;

public class SequenceUUID {
	private static SequenceUUID instance = null;

	protected SequenceUUID() {
	}

	public static synchronized SequenceUUID getInstance() {
		if (instance == null) {
			instance = new SequenceUUID();
		}
		return instance;
	}

	public String getUUID() {
		String midValue = null;
		long timeNow = System.currentTimeMillis();
		int timeLow = (int) timeNow & 0xFFFFFFFF;

		if (midValue == null) {
			try {
				InetAddress inet = InetAddress.getLocalHost();
				byte[] bytes = inet.getAddress();
				String hexInetAddress = hexFormat(getInt(bytes), 8);
				String thisHashCode = hexFormat(System.identityHashCode(this),
						8);
				midValue = hexInetAddress + thisHashCode;
			} catch (Exception e) {
			}
		}

		SecureRandom random = new SecureRandom();
		int node = random.nextInt();

		return (hexFormat(timeLow, 8).substring(2, 6)
				+ midValue.substring(4, 12) + hexFormat(node, 8)
				.substring(2, 6));
	}

	/**
	 * Returns an integer based on a byte array
	 * 
	 * @param bytes
	 * @return
	 */
	private static int getInt(byte[] bytes) {
		int i = 0;
		int j = 24;

		for (int k = 0; j >= 0; k++) {
			int l = bytes[k] & 0xff;
			i += (l << j);
			j -= 8;
		}

		return i;
	}

	/**
	 * Returns the string representation of an integer with the necessary
	 * padding
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private static String hexFormat(int i, int j) {
		String s = Integer.toHexString(i);

		return padHex(s, j) + s;
	}

	/**
	 * Pads a string with the required number of zeroes
	 * 
	 * @param s
	 * @param i
	 * @return
	 */
	private static String padHex(String s, int i) {
		StringBuffer tmpBuffer = new StringBuffer();

		if (s.length() < i) {
			for (int j = 0; j < (i - s.length()); j++) {
				tmpBuffer.append('0');
			}
		}

		return tmpBuffer.toString();
	}

    //plus 1 every time in each month
//    public String getNextCode(){
//          int start = 0;
//
//    }
}
