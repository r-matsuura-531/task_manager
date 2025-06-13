package com.example.taskmanager.presentation.category;

import com.example.taskmanager.application.category.CategoryQueryService;
import com.example.taskmanager.application.category.create.CreateCategoryInput;
import com.example.taskmanager.application.category.create.CreateCategoryUseCase;
import com.example.taskmanager.application.category.delete.DeleteCategoryUseCase;
import com.example.taskmanager.application.category.search.SearchCategoriesQueryModel;
import com.example.taskmanager.application.category.search.SearchCategoryCondition;
import com.example.taskmanager.application.category.search.SearchCategoryQueryModel;
import com.example.taskmanager.application.category.update.UpdateCategoryInput;
import com.example.taskmanager.application.category.update.UpdateCategoryUseCase;
import com.example.taskmanager.application.menu.FindMenuListUseCase;
import com.example.taskmanager.application.menu.MenusQueryModel;
import com.example.taskmanager.domain.Order;
import com.example.taskmanager.domain.category.CategorySort;
import com.example.taskmanager.presentation.category.create.CreateCategoryRequest;
import com.example.taskmanager.presentation.category.search.SearchCategoryRequest;
import com.example.taskmanager.presentation.category.search.SearchCategoryResponse;
import com.example.taskmanager.presentation.category.search.SearchCategoryResponseElem;
import com.example.taskmanager.presentation.category.update.UpdateCategoryRequest;
import com.example.taskmanager.presentation.common.PagerResultResponse;
import com.example.taskmanager.presentation.common.PresentationMapper;
import com.example.taskmanager.presentation.menu.MenuPresentationMapper;
import com.example.taskmanager.presentation.menu.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

	private final CategoryQueryService categoryQueryService;
	private final FindMenuListUseCase findMenuListUseCase;
	private final CreateCategoryUseCase createCategoryUseCase;
	private final UpdateCategoryUseCase updateCategoryUseCase;
	private final DeleteCategoryUseCase deleteCategoryUseCase;

	@ModelAttribute("menus")
	public MenuResponse addMenus(Model model) {
		MenusQueryModel menusQueryModel = findMenuListUseCase.execute();
		return MenuPresentationMapper.menuQueryModelsMapToMenuResponse(menusQueryModel);
	}

	/**
	 * カテゴリ一覧画面を表示する
	 */
	@GetMapping("")
	public String index(
			Model model,
			final SearchCategoryRequest request
	) {

		SearchCategoryCondition condition = SearchCategoryCondition.builder()
				.name(request.getName())
				.sort(request.getSort() != null ? request.getSort() : CategorySort.NAME)
				.order(request.getOrder() != null ? request.getOrder() : Order.ASC)
				.page(request.getPage())
				.pageSize(request.getPageSize())
				.build();

		SearchCategoriesQueryModel categoriesQueryModel = categoryQueryService.searchCategoryQueryModels(condition);
		SearchCategoryResponse response = CategoryPresentationMapper.categoryQueryModelMapToResponse(categoriesQueryModel);
		PagerResultResponse pager = PresentationMapper.pagerQueryModelMapToPagerResultResponse(categoriesQueryModel.getPagerQueryModel());


		model.addAttribute("categories", response);
		model.addAttribute("pager", pager);
		model.addAttribute("request", request);
		model.addAttribute("condition", condition);

		return "category/index";
	}

	/**
	 * カテゴリ登録画面を表示する
	 */
	@GetMapping("/create")
	public String create(
			Model model,
			@ModelAttribute final CreateCategoryRequest createCategoryRequest
	) {
		return "category/create";
	}

	/**
	 * カテゴリを登録する
	 */
	@PostMapping("/create")
	public String create(
			Model model,
			@Validated @ModelAttribute final CreateCategoryRequest createCategoryRequest,
			BindingResult bindingResult
	) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("createCategoryRequest", createCategoryRequest);
			return "category/create";
		}

		CreateCategoryInput input = CreateCategoryInput.builder()
				.name(createCategoryRequest.getName())
				.build();

		createCategoryUseCase.handle(input);

		return "redirect:/category";
	}

	/**
	 * カテゴリ編集画面を表示する
	 * */
	@GetMapping("/edit/{id}")
	public String edit(
			Model model,
			@ModelAttribute final UpdateCategoryRequest updateCategoryRequest,
			@ModelAttribute("id") String id
	) {

		SearchCategoryQueryModel category = categoryQueryService.findById(id);
		SearchCategoryResponseElem response = CategoryPresentationMapper.categoryQueryModelMapToResponseElem(category);

		model.addAttribute("updateCategoryRequest", updateCategoryRequest);
		model.addAttribute("category", response);

		return "category/edit";
	}

	/**
	 * カテゴリを更新する
	 * */
	@PostMapping("/edit/{id}")
	public String edit(
			Model model,
			@Validated @ModelAttribute final UpdateCategoryRequest updateCategoryRequest,
			BindingResult bindingResult,
			@ModelAttribute("id") String id
	) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("updateCategoryRequest", updateCategoryRequest);
			return "category/edit";
		}

		final UpdateCategoryInput input = UpdateCategoryInput.builder()
				.id(id)
				.name(updateCategoryRequest.getName())
				.build();

		updateCategoryUseCase.handle(input);

		return "redirect:/category";
	}

	/**
	 * カテゴリを削除する
	 * */
	@PostMapping("/delete/{id}")
	public String delete(
			Model model,
			@ModelAttribute("id") String id
	) {
		deleteCategoryUseCase.handle(id);
		return "redirect:/category";
	}
}
