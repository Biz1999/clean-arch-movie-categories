package com.d4i.sample.movie.delivery.impl;

import com.d4i.sample.movie.category.Category;
import com.d4i.sample.movie.category.exception.CategoryAlreadyExistException;
import com.d4i.sample.movie.category.usecase.CreateCategoryUseCase;
import com.d4i.sample.movie.category.usecase.GetAllCategoriesUseCase;
import com.d4i.sample.movie.delivery.converters.CategoryRestConverter;
import com.d4i.sample.movie.delivery.rest.CategoryRest;
import com.d4i.sample.movie.shared.constants.RestConstants;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryControllerImpl.class)
class CategoryControllerImplTest {

    @MockBean
    private GetAllCategoriesUseCase getAllCategoriesUseCase;

    @MockBean
    private CreateCategoryUseCase createCategoryUseCase;

    @MockBean
    private CategoryRestConverter categoryRestConverter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_get_all_categories_created_successfully() throws Exception {
        String expectedResponse = getExpectedResponse("get_categories_200_response.json");

        Category dramaCategory = new Category(Long.parseLong("0"), "Drama", true);
        CategoryRest dramaCategoryRest = new CategoryRest("Drama", true);

        Category horrorCategory = new Category(Long.parseLong("1"), "Horror", false);
        CategoryRest horrorCategoryRest = new CategoryRest("Horror", false);

        Collection<Category> categories = List.of(dramaCategory, horrorCategory);


        when(getAllCategoriesUseCase.execute()).thenReturn(categories);
        when(categoryRestConverter.mapToRest(dramaCategory)).thenReturn(dramaCategoryRest);
        when(categoryRestConverter.mapToRest(horrorCategory)).thenReturn(horrorCategoryRest);

        String responseBody = mockMvc.perform(get(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_CATEGORY))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(expectedResponse, responseBody, true);
    }

    @Test
    void should_get_empty_array_of_categories_successfully() throws Exception{
        String expectedResponse = getExpectedResponse("empty_get_categories_200_response.json");

        Collection<Category> categories = List.of();

        when(getAllCategoriesUseCase.execute()).thenReturn(categories);

        String responseBody = mockMvc.perform(get(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_CATEGORY))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(expectedResponse, responseBody, true);
    }

    @Test
    void should_create_a_category_successfully() throws Exception {
        String expectedResponse = getExpectedResponse("create_category_200_response.json");

        String responseBody = mockMvc.perform(post(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_CATEGORY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Drama\",\"available\": \"true\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(expectedResponse, responseBody, true);
    }


    @Test
    void should_throw_an_exception_when_category_already_exists_but_answers_with_200_code() throws Exception {
        String expectedResponse = getExpectedResponse("create_category_200_response.json");

        CategoryRest dramaCategoryRest = new CategoryRest("Drama", true);
        Category dramaCategory = new Category(null, "Drama", true);

        when(categoryRestConverter.mapToEntity(dramaCategoryRest)).thenReturn(dramaCategory);
        doThrow(new CategoryAlreadyExistException()).when(createCategoryUseCase).execute(any());

        String responseBody = mockMvc.perform(post(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_CATEGORY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Drama\",\"available\": \"true\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThrows(CategoryAlreadyExistException.class, () -> createCategoryUseCase.execute(any()));
        JSONAssert.assertEquals(expectedResponse, responseBody, true);
    }

    private String getExpectedResponse(String filename) throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getResource("/payload").toURI()).resolve(filename);
        return readString(path, StandardCharsets.UTF_8);
    }
}