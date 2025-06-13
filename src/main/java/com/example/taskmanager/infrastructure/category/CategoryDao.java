package com.example.taskmanager.infrastructure.category;

import com.example.taskmanager.application.category.search.SearchCategoryCondition;
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
public interface CategoryDao {

	/**
	 * 全カテゴリを取得する
	 * @return カテゴリ
	 */
	@Select
	List<CategoryEntity> findAll();

	/**
	 * カテゴリを検索する
	 * @param categorySearchCondition 検索条件
	 * */
	@Select
	List<CategoryEntity> findBySearchCondition(SearchCategoryCondition categorySearchCondition, SelectOptions selectOptions);

	/**
	 * カテゴリを登録する
	 * @param categoryEntity 登録するカテゴリ
	 */
	@Insert
	int insert(CategoryEntity categoryEntity);

	/**
	 * カテゴリを更新する
	 * @param categoryEntity 更新するカテゴリ
	 */
	@Update
	int update(CategoryEntity categoryEntity);

	/**
	 * カテゴリを削除する
	 * @param categoryEntity 削除するカテゴリ
	 */
	@Delete
	int delete(CategoryEntity categoryEntity);

	/**
	 * 指定のカテゴリIDのカテゴリを取得する
	 * @param id カテゴリID
	 * */
	@Select
	CategoryEntity findById(String id);
}
