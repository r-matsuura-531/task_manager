package com.example.taskmanager.infrastructure.category;

import com.example.taskmanager.domain.category.Category;
import com.example.taskmanager.domain.category.CategoryRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositry {

	private final CategoryDao categoryDao;

	@Override
	public void register(Category category) {
		final CategoryEntity categoryEntity = categoryMapToNewTaskEntity(category);
		int count = categoryDao.insert(categoryEntity);
		if (count != 1) {
			throw new RuntimeException("カテゴリの登録に失敗しました");
		}
	}

	@Override
	public void update(Category category) {
		final CategoryEntity categoryEntity = categoryMapToNewTaskEntity(category);
		int count = categoryDao.update(categoryEntity);
		if (count != 1) {
			throw new RuntimeException("カテゴリの更新に失敗しました");
		}
	}

	@Override
	public void delete(String id) {
		final CategoryEntity categoryEntity = categoryDao.findById(id);
		int count = categoryDao.delete(categoryEntity);
		if (count != 1) {
			throw new RuntimeException("カテゴリの削除に失敗しました");
		}
	}

	private CategoryEntity categoryMapToNewTaskEntity(Category category) {
		final CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setId(category.getId().getValue());
		categoryEntity.setName(category.getName());

		return categoryEntity;
	}
}
