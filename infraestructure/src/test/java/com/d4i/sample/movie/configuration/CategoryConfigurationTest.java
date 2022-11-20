package com.d4i.sample.movie.configuration;

import com.d4i.sample.movie.category.usecase.CreateCategoryUseCaseImpl;
import com.d4i.sample.movie.category.usecase.GetAllCategoriesUseCaseImpl;
import com.d4i.sample.movie.delivery.converters.CategoryRestConverter;
import com.d4i.sample.movie.persistence.converters.CategoryRepositoryConverter;
import com.d4i.sample.movie.persistence.impl.CategoryServiceImpl;
import com.d4i.sample.movie.persistence.repositories.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryConfigurationTest {

    private final CategoryConfiguration categoryConfiguration = new CategoryConfiguration();

    @Test
    void should_create_category_repository_converter_bean() {
        Assertions.assertThat(categoryConfiguration.createCategoryRepositoryConverter())
                .isInstanceOf(CategoryRepositoryConverter.class);
    }

    @Test
    void should_create_category_rest_converter_bean() {
        Assertions.assertThat(categoryConfiguration.createCategoryRestConverter())
                .isInstanceOf(CategoryRestConverter.class);
    }

    @Test
    void should_create_category_service_impl_bean() {
        Assertions.assertThat(categoryConfiguration.createCategoriServiceImpl())
                .isInstanceOf(CategoryServiceImpl.class);
    }

    @Test
    void should_create_get_all_categories_use_case_bean() {
        Assertions.assertThat(categoryConfiguration.createGetAllCategoriesUseCase())
                .isInstanceOf(GetAllCategoriesUseCaseImpl.class);
    }

    @Test
    void should_create_create_category_use_case_bean() {
        Assertions.assertThat(categoryConfiguration.createCreateCategoryUseCase())
                .isInstanceOf(CreateCategoryUseCaseImpl.class);
    }
}