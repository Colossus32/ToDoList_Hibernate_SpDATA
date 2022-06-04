package com.colossus.todolist.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:/mainTest.xml")
@WebAppConfiguration(value = "WEB-INF/web.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ControllersIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void checkServletContext(){

        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("userController"));
    }

    @Test
    public void createUser() throws Exception{
        this.mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"email\": \"email@gmail.com\", \"password\": \"123\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("email").value("email@gmail.com"))
                .andExpect(jsonPath("password").value("123"))
                .andExpect(jsonPath("id").isNotEmpty());
    }

    //deleting created in previous test user, run pack tests
    @Test
    public void deleteUser() throws Exception{

        this.mockMvc.perform(delete("/user/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getUser() throws Exception{

        this.mockMvc.perform(get("/user/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("email").value("updatedEmail"))
                .andExpect(jsonPath("password").value("456"))
                .andExpect(jsonPath("id").value(1));
    }

    @Test
    public void updateUser() throws Exception{

        this.mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"email\": \"email@gmail.com\", \"password\": \"123\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("email").value("email@gmail.com"))
                .andExpect(jsonPath("password").value("123"))
                .andExpect(jsonPath("id").isNotEmpty());


        this.mockMvc.perform(put("/user/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"email\": \"updatedEmail\", \"password\": \"456\"}"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/user/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("email").value("updatedEmail"))
                .andExpect(jsonPath("password").value("456"))
                .andExpect(jsonPath("id").value(1));
    }
}
