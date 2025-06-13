package com.example.taskmanager.presentation.task;


import com.example.taskmanager.application.task.search.SearchTaskQueryModel;
import com.example.taskmanager.application.task.search.SearchTasksQueryModel;
import com.example.taskmanager.presentation.common.PagerResultResponse;
import com.example.taskmanager.presentation.common.PresentationMapper;
import com.example.taskmanager.presentation.task.search.SearchTaskResponse;
import com.example.taskmanager.presentation.task.search.SearchTaskResponseElem;

import java.time.ZoneId;
import java.util.List;

public class TaskPresentationMapper {

	public static SearchTaskResponseElem searchTaskQueryModelMapToTaskSearchResponseElem(SearchTaskQueryModel searchTaskQueryModel) {
		return new SearchTaskResponseElem(
				searchTaskQueryModel.getId(),
				searchTaskQueryModel.getTitle(),
				searchTaskQueryModel.getDescription(),
				searchTaskQueryModel.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				searchTaskQueryModel.getStatus(),
				searchTaskQueryModel.getCategoryName()
		);
	}

	private static List<SearchTaskResponseElem> searchTaskQueryModelsMapToTaskSearchResponseElemList(SearchTasksQueryModel searchTasksQueryModel) {
		List<SearchTaskQueryModel> searchTaskQueryModelList = searchTasksQueryModel.getSearchTaskQueryModels();

		return searchTaskQueryModelList.stream()
				.map(TaskPresentationMapper::searchTaskQueryModelMapToTaskSearchResponseElem)
				.toList();
	}

	public static SearchTaskResponse searchTaskQueryModelsMapToTaskSearchResponse(SearchTasksQueryModel searchTasksQueryModel) {
		List<SearchTaskResponseElem> searchTaskResponseElemList = searchTaskQueryModelsMapToTaskSearchResponseElemList(searchTasksQueryModel);
		return new SearchTaskResponse(searchTaskResponseElemList);
	}

}