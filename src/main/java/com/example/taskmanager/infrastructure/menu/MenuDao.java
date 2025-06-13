package com.example.taskmanager.infrastructure.menu;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@Dao
@ConfigAutowireable
public interface MenuDao {

	@Select
	List<MenuEntity> findAll();
}
