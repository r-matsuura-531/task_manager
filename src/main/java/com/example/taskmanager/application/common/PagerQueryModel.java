package com.example.taskmanager.application.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagerQueryModel {

	private final Long totalCount;

	private final Integer page;

	private final Integer pageSize;

	private final Long totalPage;

	public static PagerQueryModel of(Long totalCount, Integer page, Integer pageSize) {
		Long totalPage = totalCount / pageSize;
		if (totalCount % pageSize != 0 || totalPage == 0) {
			totalPage++;
		}

		return new PagerQueryModel(totalCount, page, pageSize, totalPage);
	}
}
