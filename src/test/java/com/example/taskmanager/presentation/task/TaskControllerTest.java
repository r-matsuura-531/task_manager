package com.example.taskmanager.presentation.task;

import com.example.taskmanager.application.menu.FindMenuListUseCase;
import com.example.taskmanager.application.menu.MenusQueryModel;
import com.example.taskmanager.application.task.create.CreateTaskUseCase;
import com.example.taskmanager.domain.task.TaskStatus;
import com.example.taskmanager.presentation.task.create.CreateTaskRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TaskControllerTest {

	private MockMvc mockMvc;

	@Mock
	private CreateTaskUseCase createTaskUseCase;

	@Mock
	private FindMenuListUseCase findMenuListUseCase;

	@InjectMocks
	private TaskController taskController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		// ViewResolver を設定
		//これを設定しないと、テスト実行時に以下のエラーが発生する
		//javax.servlet.ServletException: Circular view path [create]: would dispatch back to the current handler URL [/create] again. Check your ViewResolver setup!
		//これは、ViewResolverが設定されていないため、ビュー名が解決されず、リクエストが再度同じURLにディスパッチされるためです。
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".html"); // 仮の拡張子を設定（実際のプロジェクトに合わせて変更）

		// MockMvc のインスタンスを作成
		mockMvc = MockMvcBuilders.standaloneSetup(taskController)
				.setViewResolvers(viewResolver)
				.build();

		when(findMenuListUseCase.execute()).thenReturn(new MenusQueryModel(List.of()));
	}

	//FIXME: 修正
//	@Test
//	void testCreateTask_Success() throws Exception {
//
//		// CreateTaskRequest のインスタンスを作成
//		CreateTaskRequest request = new CreateTaskRequest();
//		request.setTitle("新しいタスク");
//		request.setDescription("タスクの説明");
//		request.setDueDate(LocalDate.of(2025, 3, 20));
//		request.setStatus(TaskStatus.DONE);
//
//		// ObjectMapper を使って JSON に変換
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.registerModule(new JavaTimeModule()); // LocalDate を JSON に変換できるようにする
//
//		String json = objectMapper.writeValueAsString(request);
//
//		mockMvc.perform(post("/create")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(request))
//				) // リクエストボディにJSONをセット
//				.andExpect(status().is2xxSuccessful());
//
//		verify(createTaskUseCase, times(1)).handle(any());
//	}

	@Test
	void testCreateTask_ValidationFailure() throws Exception {

		// CreateTaskRequest のインスタンスを作成
		CreateTaskRequest request = new CreateTaskRequest();
		request.setTitle(null);
		request.setDescription("タスクの説明");
		request.setDueDate(LocalDate.of(2025, 3, 20));
		request.setStatus(TaskStatus.DONE);

		// ObjectMapper を使って JSON に変換
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule()); // LocalDate を JSON に変換できるようにする

		String invalidRequestJson = objectMapper.writeValueAsString(request).replace("DONE", "INVALID_STATUS");

		mockMvc.perform(post("/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(invalidRequestJson))
				.andExpect(status().is2xxSuccessful());

		verify(createTaskUseCase, never()).handle(any());
	}
}
