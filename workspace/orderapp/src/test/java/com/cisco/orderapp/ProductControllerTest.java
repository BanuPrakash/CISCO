package com.cisco.orderapp;

import com.cisco.orderapp.api.ProductController;
import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import  static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

//    @Autowired
    @MockitoBean
    private OrderService service; // mock, not the actual OrderService

    @Test
    public void getProductsTest() throws  Exception {
        // mock data, not from database
        List<Product> products = Arrays.asList(Product.builder().id(132).name("A").price(900).quantity(100).build(),
                Product.builder().id(52).name("B").build());

        // mocking
        when(service.getProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("A")))
                .andExpect(jsonPath("$[1].name", is("B")));

        verify(service, times(1)).getProducts();
    }

    @Test
    public void addProductTest() throws  Exception {
        // valid product
        Product product = Product.builder().name("A").price(342.22).quantity(100).build();
        // convert Object to JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(product);

        when(service.saveProduct(Mockito.any(Product.class)))
                .thenReturn(Mockito.any(Product.class));
        mockMvc.perform(post("/api/products")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()); // 201

        verify(service, times(1)).saveProduct(Mockito.any(Product.class));
    }

    @Test
    public void addInvalidProductTest() throws  Exception {
        Product product = Product.builder().build(); // invalid product
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(product);

        mockMvc.perform(post("/api/products")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors", hasSize(3)))
                .andExpect(jsonPath("$.errors", hasItem("Name is required!!!")));

        verifyNoInteractions(service);
    }
}
