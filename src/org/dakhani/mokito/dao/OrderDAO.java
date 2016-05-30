package org.dakhani.mokito.dao;

import java.sql.SQLException;

import org.dakhani.mokito.dto.Order;

public interface OrderDAO {
	
	int create(Order order) throws SQLException;
	
	Order read(int orderId) throws SQLException;
	
	int update(Order order) throws SQLException;
	
	int delete(int orderId) throws SQLException;
	
	

}
