package com.example.taskmanager.domain.category;

import de.huxhorn.sulky.ulid.ULID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

	private final CategoryId id;

	private final String name;

	public static Category createNewCategory(
			String name
	) {
		ULID ulid = new ULID();
		CategoryId categoryId = new CategoryId(ulid.nextULID());

		return new Category(
				categoryId,
				name
		);
	}

	public static Category createUpdateCategory(
			CategoryId id,
			String name
	) {
		return new Category(
				id,
				name
		);
	}
}
