package system;

import datamodel.Order;
import system.Components.InventoryManager;
import system.Components.OrderProcessor;

final class OrderProcessorImpl implements OrderProcessor{
	protected final InventoryManager inventoryManager;
	
	public OrderProcessorImpl(InventoryManager inventoryManager) {
		this.inventoryManager = inventoryManager;
	}

	@Override
	public boolean accept(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long orderValue(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long vat(long grossValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long vat(long grossValue, int rateIndex) {
		// TODO Auto-generated method stub
		return 0;
	}

}
