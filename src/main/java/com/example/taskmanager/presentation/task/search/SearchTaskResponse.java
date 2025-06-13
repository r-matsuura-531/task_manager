package com.example.taskmanager.presentation.task.search;

import com.example.taskmanager.presentation.common.PagerResultResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchTaskResponse {

	private final List<SearchTaskResponseElem> searchTasks;

}
