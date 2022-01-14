package com.wipro.cloud.api.helloworld;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import com.wipro.cloud.api.helloworld.controller.HelloWorldController;

import com.wipro.cloud.api.helloworld.domain.service.*;
import com.wipro.cloud.api.helloworld.domain.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.wipro.cloud.api.helloworld.ServletInitializer.class)
@AutoConfigureMockMvc
public class HelloWorldApiApplicationTest {

	@Autowired
	HelloWorldController controller;

	@Autowired
	private MockMvc mockMvc;
	

	/**
	 * 
	 */
	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void shouldReturnClientDetails() throws Exception {
		ClientApi clientApi  = Mockito.mock(ClientApi.class);
		
		Client client = new Client();

		this.mockMvc.perform(get("/helloworldapi/v1/client/1234")
		
		.header("User-Login-Id", "28026648")
		.header("UserId", "28026648")
				.header("x-lbg-txn-correlation-id", "0x1vc816udufcg2ofi3mv2jk")
				.header("x-lbg-user-role", "ssa")
				.header("x-forwarded-host", "cbsecure.lloyds.com:30000")
				).andDo(print())
		
		.andExpect(status().isOk());
	}
	
	
	@Test
	public void shouldHandleBadRequest() throws Exception {
		ClientApi clApi = Mockito.mock(ClientApi.class);
		when(clApi.findClientDetailById(123400)).thenThrow(new RuntimeException("Emulating Runtime exception"));
		this.mockMvc.perform(get("/helloworldapi/v1/client")).andDo(print())
		.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void shouldHandleUncaughtExceptions() throws Exception {
		this.mockMvc.perform(get("/helloworldapi/v1/client/56789")
		.header("x-lbg-internal-user-id", "28026648")
		.header("UserId", "28026648")
				.header("x-lbg-txn-correlation-id", "0x1vc816udufcg2ofi3mv2jk")
				.header("User-Role", "ssa")
				.header("x-forwarded-host", "cbsecure.lloyds.com:30000")
		
		).andDo(print())
		.andExpect(status().isInternalServerError());
		
	}
}

