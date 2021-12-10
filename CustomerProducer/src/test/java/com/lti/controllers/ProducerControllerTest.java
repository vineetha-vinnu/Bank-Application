package com.lti.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.HttpStatus;



import com.lti.Service.ProductService;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import com.lti.model.Customer_Bank;
@RunWith(SpringRunner.class)
@WebMvcTest(value = ProducerController.class)
class ProducerControllerTest {

	 @Autowired
	 private MockMvc mockMvc;

	 @MockBean
	 private ProductService ProductSer;
	    
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void PostCustomertest() throws Exception {
		 String URI = "/bank/customer";
		 Customer_Bank cust = new Customer_Bank();
	        cust.setAge(22);
	        cust.setId(2);
	        cust.setName("Hari");
	        cust.setTypeofAccount("Savings");
	        
	       
	        String jsonInput = this.converttoJson(cust);

	        Mockito.when(ProductSer.PostCustomer(Mockito.any(Customer_Bank.class))).thenReturn(cust);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(jsonInput).isEqualTo(jsonOutput);
	        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}
	
	@Test
    public void testGetAllCustomers() throws Exception{
      
        String URI = "/bank/getCustomer";
        Customer_Bank cust = new Customer_Bank();
        cust.setAge(22);
        cust.setId(2);
        cust.setName("Hari");
        cust.setTypeofAccount("Savings");

        Customer_Bank cust2 = new Customer_Bank();
        cust2.setAge(22);
        cust2.setId(2);
        cust2.setName("Hari");
        cust2.setTypeofAccount("Savings");

        List<Customer_Bank> ticketList = new ArrayList<>();
        ticketList.add(cust);
        ticketList.add(cust2);

        String jsonInput = this.converttoJson(ticketList);

        Mockito.when(ProductSer.getCustomers()).thenReturn(ticketList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);
    }
	 @Test
	    public void testDeleteCustById() throws Exception{
	        String URI = "/bank/deleteCustomer/{custid}";
	        Customer_Bank cust = new Customer_Bank();
	        cust.setAge(22);
	        cust.setId(21);
	        cust.setName("Hari");
	        cust.setTypeofAccount("Savings");

	        Mockito.when(ProductSer.getCustomersByID(Mockito.anyInt())).thenReturn(cust);
	        Mockito.when(ProductSer.deleteCust(Mockito.anyInt())).thenReturn("deleted"+Mockito.anyInt());
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 21).accept(MediaType.
	        		APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

	        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	    }
	 
	 @Test
		public void testFindByID() throws Exception {
			
		 String URI = "/bank/getCustomer/{custid}";
		 Customer_Bank cust = new Customer_Bank();
	        cust.setAge(22);
	        cust.setId(21);
	        cust.setName("Hari");
	        cust.setTypeofAccount("Savings");
			
	       
	        String jsonInput = this.converttoJson(cust);

	        Mockito.when(ProductSer.getCustomersByID(Mockito.anyInt())).thenReturn(cust);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI,21).accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(jsonInput).isEqualTo(jsonOutput);
		}

	  @Test
	    public void testUpdatecCust() throws Exception{

	        String URI = "/bank/updateCustomer/{id}";
	        Customer_Bank cust = new Customer_Bank();
	        cust.setAge(22);
	        cust.setId(21);
	        cust.setName("Hari");
	        cust.setTypeofAccount("Savings");
	        String jsonInput = this.converttoJson(cust);
	        ResponseEntity<Customer_Bank> r=new ResponseEntity<>(HttpStatus.OK);

	       
	        Mockito.when(ProductSer.updateCustomer(Mockito.anyInt(),Mockito.any())).thenReturn(r);
	        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,21).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(r).isEqualTo(jsonOutput);
	    }
	private String converttoJson(Object ticket) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(ticket);
    }

}
