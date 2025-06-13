package com.example.taskmanager.application.menu;

import org.springframework.stereotype.Component;

@Component
public interface MenuQueryService {

	/**
	 * メニュー情報を取得する
	 * @return メニュー情報
	 */
	MenusQueryModel findMenus();
}
