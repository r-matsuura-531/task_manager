package com.example.taskmanager.application.category.search;

import com.example.taskmanager.application.common.PagerQueryModel;
import com.example.taskmanager.application.task.search.SearchTaskQueryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchCategoriesQueryModel {

	private final List<SearchCategoryQueryModel> searchCategoryQueryModels;
	private final PagerQueryModel pagerQueryModel;

	public static SearchCategoriesQueryModel of(List<SearchCategoryQueryModel> searchCategoryQueryModels, PagerQueryModel pagerQueryModel) {
		return new SearchCategoriesQueryModel(searchCategoryQueryModels, pagerQueryModel);
	}

}
