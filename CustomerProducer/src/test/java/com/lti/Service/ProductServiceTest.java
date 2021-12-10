package com.lti.Service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.lti.Repository.CustomerRepository;
import com.lti.model.Customer_Bank;

class ProductServiceTest {

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
	
	 @MockBean
	    //@Mock
	    private CustomerRepository CustRep;

	    @Autowired
	    //@InjectMocks 
	    private ProductService ProductSer;

	    @Test
	    public void testCreatecust(){
	    	Customer_Bank cust = new Customer_Bank();
	        cust.setAge(22);
	        cust.setId(2);
	        cust.setName("Hari");
	        cust.setTypeofAccount("Savings");

	        Mockito.when(CustRep.save(cust)).thenReturn(cust);
	        assertThat(ProductSer.PostCustomer(cust)).isEqualTo(cust);
	    }

	    @Test
	    public void testGetcustById() throws Exception{
	    	Customer_Bank cust = new Customer_Bank();
	        cust.setAge(22);
	        cust.setId(2);
	        cust.setName("Hari");
	        cust.setTypeofAccount("Savings");
	        Customer_Bank tt=CustRep.findById(2).get();
	        Mockito.when(tt).thenReturn(cust);
	        assertThat(ProductSer.getCustomersByID(2)).isEqualTo(cust);
	    }

	    @Test
	    public void testGetAllcusts() throws Exception{
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

	        List<Customer_Bank> custList = new ArrayList<>();
	        custList.add(cust);
	        custList.add(cust2);

	        Mockito.when(CustRep.findAll()).thenReturn(custList);
	        assertThat(ProductSer.getCustomers()).isEqualTo(custList);
	    }




	    @Test
	    public void testDeletecustById() throws Exception{
	    	Customer_Bank cust = new Customer_Bank();
	        cust.setAge(22);
	        cust.setId(2);
	        cust.setName("Hari");
	        cust.setTypeofAccount("Savings");

	        Mockito.when(CustRep.save(cust)).thenReturn(cust);
	        Mockito.when(CustRep.findById(2).get()).thenReturn(cust);
	        CustRep.deleteById(cust.getId());
	        Mockito.when(CustRep.findById(2).get()).thenReturn(cust);
	        Assert.assertNotEquals(cust, new Customer_Bank());
	        Assert.assertEquals(ProductSer.deleteCust(2), false);
	    }

	    @Test
	    public void testDeletecustByNull() throws Exception{
	    	Customer_Bank cust = new Customer_Bank();
	        cust.setId(2);
	        
	        Customer_Bank nullcust = null;
	        Mockito.when(CustRep.findById(2).get()).thenReturn(nullcust);
	        CustRep.deleteById(cust.getId());
	        Assert.assertEquals(ProductSer.deleteCust(2), true);
	    }

		/*
		 * @Test public void testUpdatecust() throws Exception{
		 * 
		 * Customer cust2 = new Customer(); cust2.setAge(22); cust2.setId(2);
		 * cust2.setName("Hari"); cust2.setTypeofAccount("Savings");
		 * 
		 * Mockito.when(CustRep.findById(100).get()).thenReturn(cust2);
		 * cust2.setTypeofAccount("Savings");
		 * Mockito.when(CustRep.save(cust2)).thenReturn(cust2);
		 * System.out.println(cust2.getTypeofAccount());
		 * assertThat(ProductSer.updateProduct(100, cust)).isEqualTo(cust2); }
		 */

}
