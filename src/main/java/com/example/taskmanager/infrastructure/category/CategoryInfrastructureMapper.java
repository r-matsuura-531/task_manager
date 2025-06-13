package com.example.taskmanager.infrastructure.category;

import com.example.taskmanager.application.category.search.SearchCategoryQueryModel;

public class CategoryInfrastructureMapper {

	public static SearchCategoryQueryModel entityMapToCategoryQueryModel(CategoryEntity categoryEntity) {
		return new SearchCategoryQueryModel(categoryEntity.getId(), categoryEntity.getName());
	}
}
