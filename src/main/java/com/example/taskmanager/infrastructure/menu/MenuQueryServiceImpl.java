package com.example.taskmanager.infrastructure.menu;

import com.example.taskmanager.application.menu.MenuQueryService;
import com.example.taskmanager.application.menu.MenusQueryModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuQueryServiceImpl implements MenuQueryService {

	private final MenuDao menuDao;

	@Override
	public MenusQueryModel findMenus() {
		List<MenuEntity> menuEntities = menuDao.findAll();

		MenusQueryModel menusQueryModel = MenuInfrastructureMapper.menuEntitiesMapToMenusQueryModel(menuEntities);
		return menusQueryModel;
	}
}
