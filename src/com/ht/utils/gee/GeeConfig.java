package com.ht.utils.gee;

import javax.swing.text.StyledEditorKit.BoldAction;

/**
 * GeetestWeb配置文件
 * 
 *
 */
public class GeeConfig {

	// 填入自己的captcha_id和private_key
	private static final String gee_id = "03323ce502c984947a17f3f08c858df2";
	private static final String gee_key = "64ac13208310e9c2d974a2e18cdb00d1";
	private static final boolean newfailback = true;

	public static final String getGee_id() {
		return gee_id;
	}

	public static final String getGee_key() {
		return gee_key;
	}
	
	public static final boolean isnewfailback() {
		return newfailback;
	}

}
