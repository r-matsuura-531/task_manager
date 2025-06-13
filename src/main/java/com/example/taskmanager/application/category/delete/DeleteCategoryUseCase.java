package com.example.taskmanager.application.category.delete;

import com.example.taskmanager.domain.category.CategoryRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeleteCategoryUseCase {

	private final CategoryRepositry categoryRepositry;

	/**
	 * カテゴリを削除する
	 *
	 * @param id カテゴリID
	 * */
	@Transactional
	public void handle(final String id) {
		categoryRepositry.delete(id);
	}
}
