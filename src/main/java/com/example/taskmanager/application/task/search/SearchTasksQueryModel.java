package com.example.taskmanager.application.task.search;

import com.example.taskmanager.application.common.PagerQueryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchTasksQueryModel {

	private final List<SearchTaskQueryModel> searchTaskQueryModels;
	private final PagerQueryModel pagerQueryModel;

	public static SearchTasksQueryModel of(List<SearchTaskQueryModel> searchTaskQueryModels, PagerQueryModel pagerQueryModel) {
		return new SearchTasksQueryModel(searchTaskQueryModels, pagerQueryModel);
	}

}
