package com.example.taskmanager.domain.category;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositry {

	/**
	 * カテゴリを登録する
	 * @param category 登録するカテゴリ
	 */
	void register(Category category);

	/**
	 * カテゴリを更新する
	 * @param category 更新するカテゴリ
	 */
	void update(Category category);

	/**
	 * カテゴリを削除する
	 * @param id 削除するカテゴリID
	 */
	void delete(String id);
}
