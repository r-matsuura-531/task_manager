package com.example.taskmanager.infrastructure.task;

import com.example.taskmanager.application.common.PagerQueryModel;
import com.example.taskmanager.application.task.TaskQueryService;
import com.example.taskmanager.application.task.search.SearchTaskCondition;
import com.example.taskmanager.application.task.search.SearchTaskQueryModel;
import com.example.taskmanager.application.task.search.SearchTasksQueryModel;
import lombok.AllArgsConstructor;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskQueryServiceImpl implements TaskQueryService {

	private final TaskDao taskDao;

	@Override
	public SearchTasksQueryModel searchTaskQueryModel(SearchTaskCondition condition) {
		SelectOptions selectOptions = SelectOptions.get()
				.offset(condition.getOffset())
				.limit(condition.getLimit())
				.count();

		List<SearchTaskEntity> tasks = taskDao.findBySearchCondition(condition, selectOptions);

		List<SearchTaskQueryModel> searchTaskQueryModelList = TaskInfrastructureMapper.searchTaskEntitiesMapToSearchTaskQueryModels(tasks);

		PagerQueryModel pagerQueryModel = PagerQueryModel.of(selectOptions.getCount(), condition.getPage(), condition.getPageSize());
		SearchTasksQueryModel searchTasksQueryModel = SearchTasksQueryModel.of(searchTaskQueryModelList, pagerQueryModel);

		return searchTasksQueryModel;
	}

	@Override
	public SearchTaskQueryModel findSearchTaskQueryModelById(String id) {
		Optional<SearchTaskEntity> taskEntityOptional = Optional.ofNullable(taskDao.findById(id));
		SearchTaskEntity taskEntity = taskEntityOptional.orElseThrow(
				() -> new IllegalArgumentException("タスクが見つかりませんでした。")
		);

		return TaskInfrastructureMapper.searchTaskEntityMapToSearchTaskQueryModel(taskEntity);
	}
}
