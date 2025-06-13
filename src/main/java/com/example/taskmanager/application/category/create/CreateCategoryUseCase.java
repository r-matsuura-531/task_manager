package com.example.taskmanager.application.category.create;

import com.example.taskmanager.domain.category.Category;
import com.example.taskmanager.domain.category.CategoryRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreateCategoryUseCase {


	private final CategoryRepositry categoryRepositry;

	/**
	 * カテゴリを登録する
	 * @param input 登録するカテゴリ情報
	 */
	@Transactional
	public void handle(final CreateCategoryInput input) {

		Category newCategory = Category.createNewCategory(
				input.getName()
		);

		categoryRepositry.register(newCategory);
	}
}