package com.wom.ttchat.common.Utils;

import java.util.Collection;
import java.util.Map;

public class CommonUtils {
	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}
		if (object instanceof String) {
			return ((String) object).trim().isEmpty();
		}
		if (object instanceof Collection<?>) {
			return ((Collection<?>) object).isEmpty();
		}
		if (object instanceof Map) {
			return ((Map<?, ?>) object).isEmpty();
		}
		if (object.getClass().isArray()) {
			return java.lang.reflect.Array.getLength(object) == 0;
		}

		return false;
	}

}
