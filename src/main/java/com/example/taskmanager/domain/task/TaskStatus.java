package com.example.taskmanager.domain.task;

import lombok.Getter;

@Getter
public enum TaskStatus {

	DONE(0, "完了"),
	NOT_YET(1, "未完了");

	private final int value;
	private final String label;

	private TaskStatus(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public static TaskStatus of(int value) {
		for (TaskStatus status : values()) {
			if (status.value == value) {
				return status;
			}
		}
		throw new IllegalArgumentException("Invalid TaskStatus value: " + value);
	}

	public boolean isNotYet() {
//		return this.value != NOT_YET.value;
		return this.value == NOT_YET.value;
	}
}
