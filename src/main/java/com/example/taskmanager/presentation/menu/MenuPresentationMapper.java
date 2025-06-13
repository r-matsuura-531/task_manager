package com.example.taskmanager.presentation.menu;


import com.example.taskmanager.application.menu.MenuQueryModel;
import com.example.taskmanager.application.menu.MenusQueryModel;

import java.util.List;

public class MenuPresentationMapper {

	private static MenuResponseElem menuQueryModelMapToMenuResponseElem(MenuQueryModel menuQueryModel) {
		return new MenuResponseElem(
				menuQueryModel.getId(),
				menuQueryModel.getName(),
				menuQueryModel.getUrl()
		);
	}

	public static MenuResponse menuQueryModelsMapToMenuResponse(MenusQueryModel menusQueryModel) {
		List<MenuQueryModel> menuQueryModelList = menusQueryModel.getMenuQueryModels();

		List<MenuResponseElem> menuResponseElemList = menuQueryModelList.stream()
				.map(MenuPresentationMapper::menuQueryModelMapToMenuResponseElem)
				.toList();

		return new MenuResponse(menuResponseElemList);
	}

}