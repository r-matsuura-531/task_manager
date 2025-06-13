package com.example.taskmanager.presentation.category.search;

import com.example.taskmanager.presentation.task.search.SearchTaskResponseElem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchCategoryResponse {

	private final List<SearchCategoryResponseElem> categories;

}
