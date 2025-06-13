package com.example.taskmanager.infrastructure.category;

import com.example.taskmanager.application.category.search.SearchCategoriesQueryModel;
import com.example.taskmanager.application.category.search.SearchCategoryCondition;
import com.example.taskmanager.application.category.search.SearchCategoryQueryModel;
import com.example.taskmanager.application.category.CategoryQueryService;
import com.example.taskmanager.application.common.PagerQueryModel;
import lombok.AllArgsConstructor;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {

	private final CategoryDao categoryDao;

	@Override
	public List<SearchCategoryQueryModel> findAll() {
		List<CategoryEntity> entities = categoryDao.findAll();
		return entities.stream()
				.map(CategoryInfrastructureMapper::entityMapToCategoryQueryModel)
				.toList();
	}

	@Override
	public SearchCategoriesQueryModel searchCategoryQueryModels(SearchCategoryCondition condition) {
		SelectOptions selectOptions = SelectOptions.get()
				.offset(condition.getOffset())
				.limit(condition.getLimit())
				.count();

		List<CategoryEntity> entities = categoryDao.findBySearchCondition(condition, selectOptions);
		List<SearchCategoryQueryModel> searchCategoryQueryModels = entities.stream()
				.map(CategoryInfrastructureMapper::entityMapToCategoryQueryModel)
				.toList();

		PagerQueryModel pagerQueryModel = PagerQueryModel.of(selectOptions.getCount(), condition.getPage(), condition.getPageSize());
		SearchCategoriesQueryModel searchCategoriesQueryModel = new SearchCategoriesQueryModel(searchCategoryQueryModels, pagerQueryModel);

		return searchCategoriesQueryModel;
	}

	@Override
	public SearchCategoryQueryModel findById(String id) {
		CategoryEntity entity = categoryDao.findById(id);
		return CategoryInfrastructureMapper.entityMapToCategoryQueryModel(entity);
	}
}
