package org.threexui.utils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.jetbrains.annotations.NotNull;

public class JsonUtil {

	private static final Gson GSON = new Gson();

	public static String toJson(@NotNull Object object) {
		return GSON.toJson(object);
	}

	/**
	 * JSON back to java Object
	 *
	 * @param json  - JSON
	 * @param clazz - object class
	 */
	public static <T> T fromJson(@NotNull String json, @NotNull Class<T> clazz) {
		try {
			return GSON.fromJson(json, clazz);
		} catch (JsonSyntaxException | JsonIOException e) {
			throw new RuntimeException("Invalid JSON structure: " + e.getMessage(), e);
		}
	}
}
