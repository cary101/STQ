/*
 * Copyright (c) 2010-2020 Founder International Co., Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Founder. 
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the agreements you entered into with Founder.
 */

package com.cary.stq.utils;

import java.util.UUID;

/**
 * UUID utility methods.
 * 
 * @author Founder
 * 
 */
public final class UUIDUtil {

	private UUIDUtil() {
	}

	/**
	 * get an array of UUID by specific length and bits.
	 * 
	 * @param length
	 *            the length of array
	 * @param bits
	 *            the bit of UUID
	 * @return String[]
	 */
	private static String[] getUUID(Integer length, int bits) {
		String[] uuids = new String[length];
		for (int i = 0; i < length; i++) {
			if (bits == 32) {
				uuids[i] = getUUID32Bits();
			} else if (bits == 36) {
				uuids[i] = getUUID36Bits();
			}
		}
		return uuids;
	}

	/**
	 * get 32 bits UUID that exclude character.
	 * 
	 * @return
	 */
	public static String getUUID32Bits() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * Get a string array of 32 bit UUID.
	 * 
	 * @param count
	 * @return String[]
	 */
	public static String[] getUUID32Bits(Integer count) {
		return getUUID(count, 32);
	}

	/**
	 * get 36 bits UUID that include character.
	 * 
	 * @return
	 * @param
	 * @return String
	 */
	public static String getUUID36Bits() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Get a string array of 36 bit UUID.
	 * 
	 * @param count
	 *            the length of array
	 * @return String[]
	 */
	public static String[] getUUID36Bits(Integer count) {
		return getUUID(count, 36);
	}
}
