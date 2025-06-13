package com.example.taskmanager.application.category.update;

import com.example.taskmanager.domain.category.Category;
import com.example.taskmanager.domain.category.CategoryId;
import com.example.taskmanager.domain.category.CategoryRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UpdateCategoryUseCase {

	private final CategoryRepositry categoryRepositry;

	/**
	 * カテゴリを更新する
	 * @param input 更新するカテゴリの情報
	 */
	@Transactional
	public void handle(final UpdateCategoryInput input) {
		final Category category = Category.createUpdateCategory(
				new CategoryId(input.getId()),
				input.getName()
		);

		categoryRepositry.update(category);
	}
}