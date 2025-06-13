package com.example.taskmanager.domain.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskStatusTest {

	@ParameterizedTest
	@ValueSource(ints = {0, 1})
	@DisplayName("0または1の値を渡すと、TaskStatusのインスタンスが返ること")
	void shouldNotThrowExceptionWhenValueIs0Or1(int value) {
		assertDoesNotThrow(() -> TaskStatus.of(value));
	}

	@ParameterizedTest
	@ValueSource(ints = {-1, 2})
	@DisplayName("0または1以外の値を渡すと、IllegalArgumentExceptionが発生すること")
	void shouldThrowExceptionWhenValueIsNot0Or1(int value) {
		assertThrows(IllegalArgumentException.class, () -> TaskStatus.of(value));
	}

	@Test
	@DisplayName("TaskStatusがNOT_YETの場合、isNotYetメソッドがtrueを返し、DONEの場合はfalseを返すこと")
	void shouldReturnTrueForNotYetAndFalseForDone() {
		assertTrue(TaskStatus.NOT_YET.isNotYet(), "NOT_YETの場合、trueを返すべき");
		assertFalse(TaskStatus.DONE.isNotYet(), "DONEの場合、falseを返すべき");
	}
}
