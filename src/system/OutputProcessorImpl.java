package system;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import datamodel.Customer;
import datamodel.Order;
import datamodel.OrderItem;
import system.Components.InventoryManager;
import system.Components.OrderProcessor;

final class OutputProcessorImpl implements Components.OutputProcessor{
	private final int printLineWidth = 95;
	private InventoryManager inventoryManager;
	private OrderProcessor orderProcessor;
	

	public OutputProcessorImpl(InventoryManager inventoryManager, OrderProcessor orderProcessor) {
		this.inventoryManager = inventoryManager;
		this.orderProcessor = orderProcessor;
	}

	/**
	 * Prints all Orders to the console
	 * @param orders, List with the type <Order>, containing all Order Objects
	 * @param printVat, not-yet-implemented! -- would decide if VAT gets printed too
	 */
	public void printOrders(List<Order> orders, boolean printVAT) {
		StringBuffer sbAllOrders = new StringBuffer( "-------------" );
		StringBuffer sbLineItem = new StringBuffer();
		long priceTotal = 0;
		long vatPriceTotal = 0;
		for(Order o : orders) {
			long pricePerCostumer = 0;
			Customer customer = o.getCustomer();
			String customerName = splitName( customer, customer.getFirstName()+ " " + customer.getLastName() );
			String tmpLeftSide = String.format("#%d, %s's Bestellung: ", o.getId(), customerName);
			int count = 0;
			for(OrderItem i: o.getItems()) {
				if (count != 0) {
					tmpLeftSide += ", ";
				}
				count++;
				tmpLeftSide = tmpLeftSide + String.format("%dx %s", i.getUnitsOrdered(), i.getDescription());
				pricePerCostumer += i.getArticle().getUnitPrice() * i.getUnitsOrdered();
			}
			priceTotal += pricePerCostumer;
			vatPriceTotal += orderProcessor.vat(pricePerCostumer);
			String fmtPrice = fmtPrice( pricePerCostumer, "EUR");
			sbLineItem = fmtLine( tmpLeftSide, fmtPrice, printLineWidth);
			sbAllOrders.append( "\n" );
			sbAllOrders.append(sbLineItem);
		}
		String fmtPriceTotal = fmtPrice( priceTotal, "EUR");
		String fmtVatPrice = fmtPrice( vatPriceTotal, "EUR");
		sbAllOrders.append( "\n" )
		.append( fmtLine( "-------------", "-------------", printLineWidth ) )
		.append( "\n" )
		.append( fmtLine( "Gesamtwert aller Bestellungen:", fmtPriceTotal, printLineWidth ) );
		if(printVAT) {
			sbAllOrders.append("\n")
			.append( fmtLine( "Im Gesamtbetrag enthaltene Mehrwertsteuer (19%):" , fmtVatPrice, printLineWidth));			
		}
		System.out.println( sbAllOrders.toString() );
	}
	
	
	public void printInventory() { 
	}

	/**
	 * Creates and returns a formatted String consisting of a price and the currency indication
	 * @param price in cents as long 
	 * @param currency indicator as String
	 * @return returns single-String price and currency
	 */
	public String fmtPrice(long price, String currency) {
		double dblPrice = ( (double)price ) / 100.0;			// convert cent to Euro
		DecimalFormat df = new DecimalFormat( "#,##0.00",
			new DecimalFormatSymbols( new Locale( "de" ) ) );	// rounds double to 2 digits

		String fmtPrice = df.format( dblPrice );	
		return String.format("%s %s", fmtPrice, currency);
	}

	/**
	 * Creates and returns a formatted String consisting of a price and the currency indication
	 * @param price in cents as long 
	 * @param currency indicator as String
	 * @param width, provides total width of to-return String
	 * @return returns single-String price and currency with a given width
	 */
	public String fmtPrice(long price, String currency, int width) {
		String fmtter =  "%"  + width + "s";
		return String.format(fmtter, fmtPrice(price, currency));
	}

	/**
	 * Create and return a StringBuffer with formatted text 
	 * @param leftStr, String on the left site of the formatted output
	 * @param rightStr , String on the right site of the formatted output
	 * @param width, Integer deciding the total width of the formatted text
	 * @return returns StringBuffer with given parameters 
	 */
	public StringBuffer fmtLine(String leftStr, String rightStr, int width) {
		StringBuffer sb = new StringBuffer( leftStr );
		int shiftable = 0;		// leading spaces before first digit
		for( int i=1; rightStr.charAt( i ) == ' ' && i < rightStr.length(); i++ ) {
			shiftable++;
		}
		final int tab1 = width - rightStr.length() + 1;	// - ( seperator? 3 : 0 );
		int sbLen = sb.length();
		int excess = sbLen - tab1 + 1;
		int shift2 = excess - Math.max( 0, excess - shiftable );
		if( shift2 > 0 ) {
			rightStr = rightStr.substring( shift2, rightStr.length() );
			excess -= shift2;
		}
		if( excess > 0 ) {
			switch( excess ) {
			case 1:	sb.delete( sbLen - excess, sbLen ); break;
			case 2: sb.delete( sbLen - excess - 2 , sbLen ); sb.append( ".." ); break;
			default: sb.delete( sbLen - excess - 3, sbLen ); sb.append( "..." ); break;
			}
		}
		String strLineItem = String.format( "%-" + ( tab1 - 1 ) + "s%s", sb.toString(), rightStr );
		sb.setLength( 0 );
		sb.append( strLineItem );
		return sb;
	}

	/**
	* Split single‐String name to first‐ and last name and set to the customer * object, e.g. single‐String "Eric Meyer" is split into "Eric" and "Meyer". *
	* @param customer object for which first‐ and lastName are set
	* @param name single‐String name that is split into first‐ and last name
	* @return returns single‐String name extracted from customer object
	*/
	public String splitName(Customer customer, String name) {
		String lastName = "";
		String firstName = "";
		if(name.contains(",")) { 
			lastName = name.substring(0, name.indexOf(","));
			firstName = name.substring(name.lastIndexOf(",")+1);
			customer.setFirstName(firstName.substring(firstName.indexOf(" ")+1));
			customer.setLastName(lastName);
		} else {
			lastName = name.substring(name.lastIndexOf(" ")+1);
			firstName = name.substring(0, name.lastIndexOf(" "));
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
		}
		return singleName(customer);
	}

	/**
	* Returns single‐String name obtained from first‐ and lastName attributes of * Customer object, e.g. "Eric", "Meyer" returns single‐String "Meyer, Eric". *
	* @param customer object referred to
	* @return sanitized name derived from first‐ and lastName attributes
	*/
	public String singleName(Customer customer) {
		return String.format("%s, %s", customer.getLastName(), customer.getFirstName());
	}
}
