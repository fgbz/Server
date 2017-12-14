package com.kotei.testng.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import phalaenopsis.common.controller.AuthController;
/**
 * 
 * @author langl
 *
 */

public class ExampleTests {
	
	

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup( new AuthController()).build();
    }

   
    @Test
    public void getAccount() throws Exception {
        this.mockMvc.perform(get("/Sys/Auth/Test"))
        		.andExpect(content().string("true"));
    }


}
