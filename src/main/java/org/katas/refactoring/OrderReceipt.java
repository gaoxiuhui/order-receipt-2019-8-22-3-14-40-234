package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order o) {
        this.order = o;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        // print headers
        printHeaders(output);
        // print date, bill no, customer name
        printDetails(output);
//        output.append(order.getCustomerLoyaltyNumber());
        // prints lineItems
        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : order.getLineItems()) {
         printlineItems(order,output);
            // calculate sales tax @ rate of 10%
          totSalesTx = calculateSalesTax(order);
            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            tot =calculateTotalAmount(order);
        }

        // prints the state tax
        printStateTax(output, totSalesTx);

        // print total amount
        printAmount(output, tot);
        return output.toString();
    }

 public void printStateTax(StringBuilder output, double totSalesTx) {
  output.append("Sales Tax").append('\t').append(totSalesTx);
 }

 public void printAmount(StringBuilder output, double tot) {
  output.append("Total Amount").append('\t').append(tot);
 }

 public void printDetails(StringBuilder output) {
  output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
 }

 public void printHeaders(StringBuilder output) {
  output.append("======Printing Orders======\n");
 }
 public void printlineItems(Order order,StringBuilder output) {
	    for (LineItem lineItem : order.getLineItems()) {
	              output.append(lineItem.getDescription());
	              output.append('\t');
	              output.append(lineItem.getPrice());
	              output.append('\t');
	              output.append(lineItem.getQuantity());
	              output.append('\t');
	              output.append(lineItem.totalAmount());
	              output.append('\n');
	    }

	   }
	 public double calculateSalesTax(Order order) {
	   double totSalesTx = 0d;
	         for (LineItem lineItem : order.getLineItems()) {
	             double salesTax = lineItem.totalAmount() * .10;
	             totSalesTx += salesTax;
	         }
	         return totSalesTx;

	 }
	 public double calculateTotalAmount(Order order) {
	   double tot = 0d;
	         for (LineItem lineItem : order.getLineItems()) {
	             double salesTax = lineItem.totalAmount() * .10;
	             tot +=lineItem.totalAmount() + salesTax;
	         }
	         return tot;

	 }
	 
	}