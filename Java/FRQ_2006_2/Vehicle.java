public class Vehicle extends TaxableItem {
	private double dealerCost, dealerMarkup;

	public Vehicle(double dealerCost, double dealerMarkup, double taxRate) {
		super(taxRate);
		this.dealerMarkup = dealerMarkup;
		this.dealerCost = dealerCost;
	}

	@Override
	public double getListPrice() {
		return dealerCost + dealerMarkup;
	}

	public void changeMarkup(double dealerMarkup) {
		this.dealerMarkup = dealerMarkup;
	}

	@Override
	public String toString() {
		return "Dealer Cost: " + dealerCost
		   + "\nDealer Markup: " + dealerMarkup
		   + "\nList Price: " + getListPrice()
		   + "\npurchasePrice: " + purchasePrice();
	}
}