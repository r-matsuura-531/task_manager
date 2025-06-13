package com.example.taskmanager.presentation.task;

import com.example.taskmanager.application.category.search.SearchCategoryQueryModel;
import com.example.taskmanager.application.category.CategoryQueryService;
import com.example.taskmanager.application.menu.FindMenuListUseCase;
import com.example.taskmanager.application.menu.MenusQueryModel;
import com.example.taskmanager.application.task.TaskQueryService;
import com.example.taskmanager.application.task.create.CreateTaskInput;
import com.example.taskmanager.application.task.create.CreateTaskUseCase;
import com.example.taskmanager.application.task.delete.DeleteTaskUseCase;
import com.example.taskmanager.application.task.search.SearchTaskCondition;
import com.example.taskmanager.application.task.search.SearchTaskQueryModel;
import com.example.taskmanager.application.task.search.SearchTaskUseCase;
import com.example.taskmanager.application.task.search.SearchTasksQueryModel;
import com.example.taskmanager.application.task.update.UpdateTaskInput;
import com.example.taskmanager.application.task.update.UpdateTaskUseCase;
import com.example.taskmanager.domain.Order;
import com.example.taskmanager.domain.task.TaskSort;
import com.example.taskmanager.presentation.common.PagerResultResponse;
import com.example.taskmanager.presentation.common.PresentationMapper;
import com.example.taskmanager.presentation.menu.MenuPresentationMapper;
import com.example.taskmanager.presentation.menu.MenuResponse;
import com.example.taskmanager.presentation.task.create.CreateTaskRequest;
import com.example.taskmanager.presentation.task.search.SearchTaskRequest;
import com.example.taskmanager.presentation.task.search.SearchTaskResponse;
import com.example.taskmanager.presentation.task.search.SearchTaskResponseElem;
import com.example.taskmanager.presentation.task.update.UpdateTaskRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {

	private final TaskQueryService taskQueryService;
	private final CategoryQueryService categoryQueryService;
	private final SearchTaskUseCase searchTaskUseCase;
	private final CreateTaskUseCase createTaskUseCase;
	private final UpdateTaskUseCase updateTaskUseCase;
	private final DeleteTaskUseCase deleteTaskUseCase;
	private final FindMenuListUseCase findMenuListUseCase;

	@ModelAttribute("menus")
	public MenuResponse addMenus(Model model) {
		MenusQueryModel menusQueryModel = findMenuListUseCase.execute();
		return MenuPresentationMapper.menuQueryModelsMapToMenuResponse(menusQueryModel);
	}

	@GetMapping("/")
	public String index(Model model, final SearchTaskRequest request) {
		SearchTaskCondition condition = SearchTaskCondition.builder()
				.title(request.getTitle())
				.description(request.getDescription())
				.dueDate(request.getDueDate())
				.status(request.getStatus() != null ? request.getStatus() : null)
				.categoryId(request.getCategoryId() != null ? request.getCategoryId() : null)
				.sort(request.getSort() != null ? request.getSort() : TaskSort.DUE_DATE)
				.order(request.getOrder() != null ? request.getOrder() : Order.ASC)
				.page(request.getPage())
				.pageSize(request.getPageSize())
				.build();

		SearchTasksQueryModel pagerResult = searchTaskUseCase.execute(condition);
		SearchTaskResponse response = TaskPresentationMapper.searchTaskQueryModelsMapToTaskSearchResponse(pagerResult);
		PagerResultResponse pager = PresentationMapper.pagerQueryModelMapToPagerResultResponse(pagerResult.getPagerQueryModel());
		List<SearchCategoryQueryModel> categories = categoryQueryService.findAll();

		model.addAttribute("tasks", response);
		model.addAttribute("categories", categories);
		model.addAttribute("pager", pager);
		model.addAttribute("request", request);
		model.addAttribute("condition", condition);

		return "index";
	}

	@GetMapping("/create")
	public String create(
			Model model,
			final CreateTaskRequest request
	) {
		List<SearchCategoryQueryModel> categories = categoryQueryService.findAll();

		model.addAttribute("createTaskRequest", request);
		model.addAttribute("categories", categories);
		return "create";
	}

	@PostMapping("/create")
	public String create(
			Model model,
			@Validated @ModelAttribute CreateTaskRequest request,
			BindingResult bindingResult
	) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("createTaskRequest", request);
			return "create";
		}

		CreateTaskInput input = CreateTaskInput.builder()
				.title(request.getTitle())
				.description(request.getDescription())
				.dueDate(request.getDueDate())
				.status(request.getStatus())
				.categoryId(request.getCategoryId())
				.build();

		createTaskUseCase.handle(input);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String edit(
			Model model,
			UpdateTaskRequest updateTaskRequest,
			@ModelAttribute("id") String id
	) {

		SearchTaskQueryModel task = taskQueryService.findSearchTaskQueryModelById(id);
		SearchTaskResponseElem response = TaskPresentationMapper.searchTaskQueryModelMapToTaskSearchResponseElem(task);
		List<SearchCategoryQueryModel> categories = categoryQueryService.findAll();

		model.addAttribute("updateTaskRequest", updateTaskRequest);
		model.addAttribute("task", response);
		model.addAttribute("categories", categories);
		return "edit";
	}

	@PostMapping("/edit/{id}")
	public String edit(
			Model model,
			@Valid UpdateTaskRequest request,
			BindingResult bindingResult,
			@ModelAttribute("id") String id
	) {

		if (bindingResult.hasErrors()) {
			SearchTaskQueryModel task = taskQueryService.findSearchTaskQueryModelById(id);
			SearchTaskResponseElem response = TaskPresentationMapper.searchTaskQueryModelMapToTaskSearchResponseElem(task);

			model.addAttribute("updateTaskRequest", request);
			model.addAttribute("task", response);
			return "edit";
		}

		UpdateTaskInput input = UpdateTaskInput.builder()
				.id(id)
				.title(request.getTitle())
				.description(request.getDescription())
				.dueDate(request.getDueDate())
				.status(request.getStatus())
				.categoryId(request.getCategoryId())
				.build();

		updateTaskUseCase.handle(input);
		return "redirect:/";
	}

	@PostMapping("/delete/{id}")
	public String delete(
			Model model,
			@PathVariable String id
	) {
		deleteTaskUseCase.handle(id);
		return "redirect:/";
	}
}
