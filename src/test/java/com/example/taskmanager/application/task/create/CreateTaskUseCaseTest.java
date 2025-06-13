package com.example.taskmanager.application.task.create;

import com.example.taskmanager.domain.task.Task;
import com.example.taskmanager.domain.task.TaskRepositry;
import com.example.taskmanager.domain.task.TaskStatus;
import com.example.taskmanager.domain.task_category.TaskCategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseTest {

	/**
	 * @Mockの役割： TaskRepositryの仮の実装を作成する
	 * 実際のデータベースにアクセスせずにテストを行う
	 */
	@Mock
	private TaskRepositry taskRepositry;

	@Mock
	private TaskCategoryRepository taskCategoryRepository;

	/**
	 * @InjectMocksの役割： テスト対象のクラスに@Mockで作成した仮の実装を注入する
	 * 手動でインスタンスを生成する必要がない
	 */
	@InjectMocks
	private CreateTaskUseCase createTaskUseCase;

	/**
	 * テスト用の入力データ
	 * @return CreateTaskInput のストリーム
	 */
	static Stream<CreateTaskInput> createTaskInputProvider() {
		return Stream.of(
				CreateTaskInput.builder()
						.title("title")
						.description("description")
						.dueDate(LocalDate.of(2021, 1, 1))
						.status(TaskStatus.DONE)
						.build(),
				CreateTaskInput.builder()
						.title("title")
						.description("description")
						.dueDate(LocalDate.of(2024, 2, 21))
						.status(TaskStatus.NOT_YET)
						.build(),
				CreateTaskInput.builder()
						.title("title")
						.description("description")
						.dueDate(LocalDate.of(2022, 3, 31))
						.status(TaskStatus.DONE)
						.build()
		);
	}


	//ParameterizedTestアノテーションを使用することで、パラメータ化テストを行うことができる
	@ParameterizedTest
	//テストデータをMethodSourceアノテーションで指定
	@MethodSource("createTaskInputProvider")
	@DisplayName("タスクの登録が成功すること")
	void shouldSuccessCreateTask(CreateTaskInput input) {

		//Arrange(準備)
		//registerのメソッドが呼ばれた時に、何もしないように設定
		doNothing().when(taskRepositry).register(ArgumentMatchers.any(Task.class));

		//Act(実行)
		//handleメソッドが呼ばれた時に、例外が発生しないことを確認
		assertDoesNotThrow(() -> createTaskUseCase.handle(input));

		//Assert(検証)
		//registerメソッドが1回呼ばれたことを確認
		verify(taskRepositry, times(1)).register(ArgumentMatchers.any(Task.class));

		//次のテストに備えて、taskRepositryの振る舞いをリセット
		reset(taskRepositry);

	}

	@Test
	@DisplayName("タスクの登録が失敗すること")
	void shouldThrowNullPointerExceptionWhenInputIsNull() {
		assertThrows(NullPointerException.class, () -> createTaskUseCase.handle(null));
	}

}