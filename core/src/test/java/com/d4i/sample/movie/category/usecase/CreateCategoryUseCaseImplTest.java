package com.d4i.sample.movie.category.usecase;

import com.d4i.sample.movie.category.exception.CategoryAlreadyExistException;
import com.d4i.sample.movie.category.usecase.CreateCategoryUseCase;
import com.d4i.sample.movie.category.ports.CategoryRepositoryService;
import com.d4i.sample.movie.category.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCategoryUseCaseImplTest {

    private final CategoryRepositoryService categoryRepositoryService = mock(CategoryRepositoryService.class);

    private final CreateCategoryUseCaseImpl createCategoryUseCase = new CreateCategoryUseCaseImpl(categoryRepositoryService);
    @Test
    void created_categories_sucessfully()  {
        //Teste Registro Ok
        Category registryOk = new Category(Long.parseLong("0"), "Ação", true);
        when(categoryRepositoryService.doesCategoryNameExists(registryOk.getName())).thenReturn(false);
        assertDoesNotThrow(() -> createCategoryUseCase.execute(registryOk));

        verify(categoryRepositoryService).saveCategory(registryOk);

    }

    @Test
    void created_categories_name_null_error()   {
        //Teste Registro Ok
        Category registryError = new Category(Long.parseLong("0"), null, true);
        when(categoryRepositoryService.doesCategoryNameExists(registryError.getName())).thenReturn(true);
        assertThrows(CategoryAlreadyExistException.class, ()->createCategoryUseCase.execute(registryError));

        verify(categoryRepositoryService, times(0)).saveCategory(registryError);
    }


    @Test
    void created_categories_registry_duplicate_error() {
        //Teste Registro Ok
        Category registryError = new Category(Long.parseLong("0"), "Ação", true);
        when(categoryRepositoryService.doesCategoryNameExists(registryError.getName())).thenReturn(true);

        assertThrows(CategoryAlreadyExistException.class, ()->createCategoryUseCase.execute(registryError));

        verify(categoryRepositoryService, times(0)).saveCategory(registryError);
    }




}