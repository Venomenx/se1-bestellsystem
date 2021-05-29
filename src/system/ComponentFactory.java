package system;

import datamodel.RawDataFactory;
import system.Components.DataFactory;
import system.Components.InventoryManager;
import system.Components.OrderProcessor;
import system.Components.OutputProcessor;

public final class ComponentFactory {
	private static ComponentFactory instance = null;
	private OrderProcessor orderProcessor;
	private InventoryManager inventoryManager;
	private DataFactory dataFactory;
	private OutputProcessor outputProcessor;
	/**
	 * Private constructor.
	 */
	private ComponentFactory() {
		this.inventoryManager = new InventoryManagerImpl();
		this.orderProcessor = new OrderProcessorImpl( inventoryManager ); 
		this.outputProcessor = new OutputProcessorImpl(inventoryManager, orderProcessor);
		RawDataFactory.RawDataFactoryIntf objectRawFactory = RawDataFactory.getInstance( this );
		this.dataFactory = new DataFactoryImpl( objectRawFactory,
                    inventoryManager, outputProcessor );
	}
	/**
	 * Public access method to Factory singleton instance that is created * when getInstance() is first invoked. Subsequent invocations return * the reference to the same instance.
	 *
	 * @return Factory singleton instance
	 */
	public static ComponentFactory getInstance() { 
		if( instance == null ) {
			instance = new ComponentFactory(); 
		}
		return instance; }
	
	public InventoryManager getInventoryManager() {
		return inventoryManager;
	}
	
	public OrderProcessor getOrderProcessor() {
		return orderProcessor;
	}
	
	public OutputProcessor getOutputProcessor() {
		return outputProcessor;
	}
	
	public DataFactory getDataFactory() {
		return dataFactory;
	}
}