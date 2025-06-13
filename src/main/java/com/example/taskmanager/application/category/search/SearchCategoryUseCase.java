package com.example.taskmanager.application.category.search;

import com.example.taskmanager.application.category.CategoryQueryService;
import com.example.taskmanager.application.task.TaskQueryService;
import com.example.taskmanager.application.task.search.SearchTaskCondition;
import com.example.taskmanager.application.task.search.SearchTasksQueryModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchCategoryUseCase {

	private final CategoryQueryService categoryQueryService;

	public SearchCategoriesQueryModel execute(SearchCategoryCondition condition) {
		return categoryQueryService.searchCategoryQueryModels(condition);
	}
}
