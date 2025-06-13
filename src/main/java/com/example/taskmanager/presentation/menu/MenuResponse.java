package com.example.taskmanager.presentation.menu;

import com.example.taskmanager.presentation.task.search.SearchTaskResponseElem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MenuResponse {

	private final List<MenuResponseElem> menuResponses;

}
