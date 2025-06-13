package com.example.taskmanager.domain.category;

import lombok.Getter;

@Getter
public enum CategorySort {

	ID("id", "ID"),
	NAME("name", "カテゴリ名");

	private final String sortKey;
	private final String label;

	CategorySort(String sortKey, String label) {
		this.sortKey = sortKey;
		this.label = label;
	}

	public static CategorySort of(String sort) {
		for (CategorySort value : values()) {
			if (value.sortKey.equals(sort)) {
				return value;
			}
		}
		return ID;
	}
}
