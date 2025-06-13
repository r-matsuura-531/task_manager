package com.example.taskmanager.infrastructure.task_category;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface TaskCategoryDao {

	/**
	 * 登録する
	 * */
	@Insert
	int insert(TaskCategoryEntity taskCategoryEntity);

	/**
	 * 更新する
	 * @return 更新した件数
	 */
	//task_idをキーにして更新する
	@Update(sqlFile = true)
	int updateByTaskId(TaskCategoryEntity taskCategoryEntity);
}
