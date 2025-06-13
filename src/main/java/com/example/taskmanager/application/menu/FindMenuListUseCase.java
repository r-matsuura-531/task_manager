package com.example.taskmanager.application.menu;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindMenuListUseCase {

	private final MenuQueryService menuQueryService;

	public MenusQueryModel execute() {
		return menuQueryService.findMenus();
	}
}
