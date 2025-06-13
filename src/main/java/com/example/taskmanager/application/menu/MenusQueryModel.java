package com.example.taskmanager.application.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MenusQueryModel {

	private final List<MenuQueryModel> menuQueryModels;

	public static MenusQueryModel of(List<MenuQueryModel> menuQueryModels) {
		return new MenusQueryModel(menuQueryModels);
	}
}
