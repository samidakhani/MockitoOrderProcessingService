package org.dakhani.mokito.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.dakhani.mokito.bo.OrderBOImpl;
import org.dakhani.mokito.bo.exception.BOException;
import org.dakhani.mokito.dao.OrderDAO;
import org.dakhani.mokito.dto.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.SQLException;

public class OrderBOImplTest {

	private final static int ORDER_ID =123;
	
	@Mock
	OrderDAO dao;
	
	OrderBOImpl bo;
	
	@Before
	public void setUp(){
	   MockitoAnnotations.initMocks(this);	
	   bo = new OrderBOImpl();
	   bo.setDao(dao);
	}
	
	@Test
	public void placeOrder_Should_Create_Order() throws SQLException, BOException {
		
		Order order = new Order();
		when(dao.create(any(Order.class))).thenReturn(1);
		boolean result = bo.placeOrder(order);
		
		assertTrue(result);
		verify(dao,times(1)).create(order);
	}
	
	@Test
	public void placeOrder_Should_Not_Create_Order() throws SQLException, BOException {
		
		Order order = new Order();
		when(dao.create(any(Order.class))).thenReturn(0);
		boolean result = bo.placeOrder(order);
		
		assertFalse(result);
		verify(dao,times(1)).create(order);
	}
	
	@Test(expected=BOException.class)
	public void placeOrder_Should_Throw_Exception() throws SQLException, BOException {
		
		Order order = new Order();
		when(dao.create(any(Order.class))).thenThrow(SQLException.class);
		bo.placeOrder(order);
	}
	
	@Test
	public void cancelOrder_Should_Cancel_Order() throws SQLException, BOException{
		 
		Order order = new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean result = bo.cancelOrder(ORDER_ID);
		
		assertTrue(result);
		verify(dao,times(1)).read(ORDER_ID);
		verify(dao,times(1)).update(order);
		
	}
	
	@Test
	public void cancelOrder_Should_Not_Cancel_Order() throws SQLException, BOException{
		 
		Order order = new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);
		boolean result = bo.cancelOrder(ORDER_ID);
		
		assertFalse(result);
		verify(dao,times(1)).read(ORDER_ID);
		verify(dao,times(1)).update(order);
		
	}
	
	@Test(expected=BOException.class)
	public void cancelOrder_Should_Throw_BOException_On_Read() throws SQLException, BOException{
		
		when(dao.read(ORDER_ID)).thenThrow(SQLException.class);
		bo.cancelOrder(ORDER_ID);
	}
	
	@Test(expected=BOException.class)
	public void cancelOrder_Should_Throw_BOException_On_Update() throws SQLException, BOException{
		
		Order order = new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenThrow(SQLException.class);
		bo.cancelOrder(ORDER_ID);
	}

}
