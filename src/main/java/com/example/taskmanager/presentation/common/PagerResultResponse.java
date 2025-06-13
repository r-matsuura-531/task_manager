package com.example.taskmanager.presentation.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagerResultResponse {

	/**
	 * 総件数
	 */
	private final Long totalCount;

	/**
	 * ページ
	 */
	private final Integer page;

	/**
	 * 1ページあたりの件数
	 */
	private final Integer pageSize;

	/**
	 * 総ページ数
	 */
	private final Long totalPage;

	/**
	 * 次のページが存在するか
	 * @return 次のページが存在する場合はtrue
	 */
	public boolean hasNext() {
		return page < totalPage;
	}

	/**
	 * 前のページが存在するか
	 * @return 前のページが存在する場合はtrue
	 */
	public boolean hasPrevious() {
		return page > 1;
	}

	/**
	 * 次のページ番号を取得
	 * */
	public Integer getNextPage() {
		if (!hasNext()) {
			return page;
		}

		return page + 1;
	}

	/**
	 * 前のページ番号を取得
	 * */
	public Integer getPreviousPage() {
		if (!hasPrevious()) {
			return page;
		}

		return page - 1;
	}
}
