package system;

import datamodel.Order;
import system.Components.InventoryManager;
import system.Components.OrderProcessor;

final class OrderProcessorImpl implements OrderProcessor{
	protected final InventoryManager inventoryManager;
	protected static int rateIndex1 = 19;
	protected static int rateIndex2 = 7;
	
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
		long netto = (long) (grossValue / 1.19 * 0.19);
		return netto;
	}

	@Override
	public long vat(long grossValue, int rateIndex) {
		double factor = 1;
		double quot = 0;
		if( rateIndex == 1 ) {
			quot = rateIndex1 / 100;
			factor = grossValue / (( 1 + quot) * quot);
		} else if( rateIndex ==2 ) {
			quot = rateIndex2 / 100;
			factor = grossValue / (( 1 + quot) * quot);
		}
		long netto = (long) (grossValue * factor);
		return netto;
	}

}
