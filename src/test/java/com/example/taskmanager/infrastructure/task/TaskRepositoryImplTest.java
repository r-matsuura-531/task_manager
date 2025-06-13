package com.example.taskmanager.infrastructure.task;

import com.example.taskmanager.domain.task.Task;
import com.example.taskmanager.domain.task.TaskStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskRepositoryImplTest {

	@Mock
	TaskDao taskDao;

	@InjectMocks
	TaskRepositoryImpl taskRepository;

	/**
	 * 共通のテストデータ作成メソッド
	 */
	private static Task createTask(String title, String description, Date dueDate, TaskStatus status) {
		return Task.createNewTask(title, description, dueDate, status);
	}

	// 成功ケースのデータ
	static Stream<Arguments> provideValidTaskEntities() {
		return Stream.of(
				Arguments.of(createTask("タスク1", "説明1", new Date(System.currentTimeMillis()), TaskStatus.NOT_YET)),
				Arguments.of(createTask("タスク2", "説明2", new Date(System.currentTimeMillis()), TaskStatus.DONE))
		);
	}

	// 失敗ケースのデータ
	static Stream<Arguments> provideInvalidTaskEntities() {
		return Stream.of(
				Arguments.of(createTask("説明なし", null, new Date(System.currentTimeMillis()), TaskStatus.NOT_YET)),
				Arguments.of(createTask(null, "タイトルなし", new Date(System.currentTimeMillis()), TaskStatus.DONE)),
				Arguments.of(createTask("日付なし", "日付なし", null, TaskStatus.DONE))
		);
	}

	@ParameterizedTest
	@MethodSource("provideValidTaskEntities")
	@DisplayName("タスクの登録が成功する")
	void shouldSuccessRegisterTask(Task task) {
		doReturn(1).when(taskDao).insert(ArgumentMatchers.any(TaskEntity.class));
		assertDoesNotThrow(() -> taskRepository.register(task));
		verify(taskDao, times(1)).insert(ArgumentMatchers.any(TaskEntity.class));
	}

	@ParameterizedTest
	@MethodSource("provideInvalidTaskEntities")
	@DisplayName("例外が発生する")
	void shouldDbConstraintViolationExceptionRegisterTask(Task task) {
		doReturn(0).when(taskDao).insert(ArgumentMatchers.any(TaskEntity.class)); // 登録が失敗するケースをモック

		assertThrows(IllegalStateException.class, () -> taskRepository.register(task));

		verify(taskDao, times(1)).insert(ArgumentMatchers.any(TaskEntity.class));
	}
}