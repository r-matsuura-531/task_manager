package com.example.taskmanager.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.Nullable;

public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

	public static Class<?> getEnumType(Class<?> targetType) {
		if (targetType == null || !targetType.isEnum()) {
			throw new IllegalArgumentException(targetType.getName() + " is not an enum type");
		}
		return targetType;
	}

	@Override
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToEnum(StringToEnumConverterFactory.getEnumType(targetType));
	}

	private static class StringToEnum<T extends Enum> implements Converter<String, T> {
		private final Class<T> enumType;

		StringToEnum(Class<T> enumType) {
			this.enumType = enumType;
		}

		@Override
		@Nullable
		public T convert(String source) {
			if (source.isEmpty()) {
				return null;
			}

			String trimmed = source.trim();

			if (trimmed.matches("\\d+")) {
				int ordinal = Integer.parseInt(trimmed);
				T[] constants = enumType.getEnumConstants();
				if (ordinal >= 0 && ordinal < constants.length) {
					return constants[ordinal];
				}
			}

			return (T) Enum.valueOf(this.enumType, source.toUpperCase());
		}
	}

}
