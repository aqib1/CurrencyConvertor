package com.conichi.currency.converter.constant;

import java.util.Objects;

public final class HelperConst {

	public static final String COMPONENT_SCAN_PATH = "com.conichi.currency.converter";

	public static boolean isNullOrEmptyString(String string) {
		return Objects.isNull(string) || string.isEmpty();
	}

	private HelperConst() {

	}
}
