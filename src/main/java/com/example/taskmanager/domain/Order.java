package com.example.taskmanager.domain;

import lombok.Getter;

@Getter
public enum Order {

	ASC("asc", "昇順"),
	DESC("desc", "降順");

	private final String order;
	private final String label;

	Order(String order, String label) {
		this.order = order;
		this.label = label;
	}

	public static Order of(String order) {
		for (Order value : values()) {
			if (value.order.equals(order)) {
				return value;
			}
		}
		return ASC;
	}

	public boolean isAsc() {
		return this == ASC;
	}
}
