package com.example.taskmanager.application.task;

import com.example.taskmanager.application.task.search.SearchTaskCondition;
import com.example.taskmanager.application.task.search.SearchTaskQueryModel;
import com.example.taskmanager.application.task.search.SearchTasksQueryModel;
import org.springframework.stereotype.Component;

@Component
public interface TaskQueryService {

	/**
	 * タスクを検索する
	 * @return 検索結果
	 */
	SearchTasksQueryModel searchTaskQueryModel(SearchTaskCondition condition);

	/**
	 * 指定のIDに対応するタスクを取得する
	 * */
	SearchTaskQueryModel findSearchTaskQueryModelById(String id);
}
