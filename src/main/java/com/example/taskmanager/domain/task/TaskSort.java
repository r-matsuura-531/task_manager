package com.example.taskmanager.domain.task;

import lombok.Getter;

@Getter
public enum TaskSort {

	TITLE("title", "タイトル"),
	DUE_DATE("due_date", "期限"),
	STATUS("status", "ステータス");

	private final String sortKey;
	private final String label;

	TaskSort(String sortKey, String label) {
		this.sortKey = sortKey;
		this.label = label;
	}

	public static TaskSort of(String sort) {
		for (TaskSort value : values()) {
			if (value.sortKey.equals(sort)) {
				return value;
			}
		}
		return DUE_DATE;
	}
}
