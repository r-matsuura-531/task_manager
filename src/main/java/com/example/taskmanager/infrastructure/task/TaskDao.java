package com.example.taskmanager.infrastructure.task;

import com.example.taskmanager.application.task.search.SearchTaskCondition;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.List;

@Dao
@ConfigAutowireable
public interface TaskDao {

	@Select
	SearchTaskEntity findById(String id);

	@Select
	List<SearchTaskEntity> findBySearchCondition(SearchTaskCondition taskCondition, SelectOptions options);

	@Insert
	int insert(TaskEntity taskEntity);

	@Update(excludeNull = true)
	int update(TaskEntity taskEntity);

	@Delete
	int delete(TaskEntity taskEntity);
}
