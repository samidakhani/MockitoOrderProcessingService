package org.dakhani.mokito.bo;

import org.dakhani.mokito.bo.exception.BOException;
import org.dakhani.mokito.dto.Order;

public interface OrderBO {

	boolean placeOrder(Order order) throws BOException;
	
	boolean cancelOrder(int orderId) throws BOException;
	
	boolean deleteOrder(int orderId) throws BOException;
	
}
