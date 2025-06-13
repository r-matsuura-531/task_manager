package com.example.taskmanager.infrastructure.menu;

import com.example.taskmanager.application.menu.MenuQueryModel;
import com.example.taskmanager.application.menu.MenusQueryModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuInfrastructureMapper {

	private static MenuQueryModel menuEntityMapToMenuQueryModel(MenuEntity menuEntity) {
		return new MenuQueryModel(
				menuEntity.getId(),
				menuEntity.getName(),
				menuEntity.getUrl()
		);
	}

	public static MenusQueryModel menuEntitiesMapToMenusQueryModel(List<MenuEntity> menuEntities) {
		List<MenuQueryModel> menuQueryModels = menuEntities.stream()
				.map(MenuInfrastructureMapper::menuEntityMapToMenuQueryModel)
				.collect(Collectors.toList());

		return MenusQueryModel.of(menuQueryModels);
	}

}
