package com.example.taskmanager.domain.task;

import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositry {

	/**
	 * タスクを登録する
	 *
	 * @param task タスク
	 * */
	void register(Task task);

	/**
	 * タスクを更新する
	 * */
	void update(Task task);

	/**
	 * タスクを削除する
	 * */
	void delete(String id);
}
