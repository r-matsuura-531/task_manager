package com.example.taskmanager.presentation.common;


import com.example.taskmanager.application.common.PagerQueryModel;

public class PresentationMapper {

	public static PagerResultResponse pagerQueryModelMapToPagerResultResponse(PagerQueryModel pagerQueryModel) {
		return new PagerResultResponse(
				pagerQueryModel.getTotalCount(),
				pagerQueryModel.getPage(),
				pagerQueryModel.getPageSize(),
				pagerQueryModel.getTotalPage()
		);
	}

}