package com.example.taskmanager.presentation.category;

import com.example.taskmanager.application.category.search.SearchCategoriesQueryModel;
import com.example.taskmanager.application.category.search.SearchCategoryQueryModel;
import com.example.taskmanager.presentation.category.search.SearchCategoryResponse;
import com.example.taskmanager.presentation.category.search.SearchCategoryResponseElem;

import java.util.List;

public class CategoryPresentationMapper {

	public static SearchCategoryResponseElem categoryQueryModelMapToResponseElem(SearchCategoryQueryModel searchCategoryQueryModel) {
		return new SearchCategoryResponseElem(
				searchCategoryQueryModel.getId(),
				searchCategoryQueryModel.getName()
		);
	}

	private static List<SearchCategoryResponseElem> categoryQueryModelsMapToResponseElemList(List<SearchCategoryQueryModel> searchCategoryQueryModels) {
		return searchCategoryQueryModels.stream()
				.map(CategoryPresentationMapper::categoryQueryModelMapToResponseElem)
				.toList();
	}

	public static SearchCategoryResponse categoryQueryModelsMapToResponse(List<SearchCategoryQueryModel> searchCategoryQueryModels) {
		List<SearchCategoryResponseElem> searchCategoryResponseElemList = categoryQueryModelsMapToResponseElemList(searchCategoryQueryModels);
		return new SearchCategoryResponse(searchCategoryResponseElemList);
	}

	public static SearchCategoryResponse categoryQueryModelMapToResponse(SearchCategoriesQueryModel searchCategoriesQueryModel) {
		List<SearchCategoryQueryModel> searchCategoryQueryModels = searchCategoriesQueryModel.getSearchCategoryQueryModels();
		return categoryQueryModelsMapToResponse(searchCategoryQueryModels);
	}
}
