package com.example.taskmanager.domain.task_category;

import com.example.taskmanager.infrastructure.task_category.TaskCategoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoryRepository {

	/**
	 * タスクカテゴリを登録する
	 *
	 * @param taskCategory タスクカテゴリ
	 * */
	void register(TaskCategory taskCategory);

	/**
	 * タスクカテゴリを更新する
	 * */
	void update(TaskCategory taskCategory);
}
