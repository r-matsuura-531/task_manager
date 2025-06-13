package com.example.taskmanager.application.category;

import com.example.taskmanager.application.category.search.SearchCategoriesQueryModel;
import com.example.taskmanager.application.category.search.SearchCategoryCondition;
import com.example.taskmanager.application.category.search.SearchCategoryQueryModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryQueryService {

	/**
	 * カテゴリを取得する
	 * @return カテゴリ
	 */
	List<SearchCategoryQueryModel> findAll();

	/**
	 * カテゴリを検索する
	 * */
	SearchCategoriesQueryModel searchCategoryQueryModels(SearchCategoryCondition condition);

	/**
	 * 指定のカテゴリIDのカテゴリを取得する
	 * */
	SearchCategoryQueryModel findById(String id);
}
