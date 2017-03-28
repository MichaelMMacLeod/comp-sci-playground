public class Tester {
	public static void main(String[] args) {
		Vehicle car = new Vehicle(20000.00, 2500.00, 0.10);
		System.out.println(car);

		/*
		@Override
		public String toString() {
			return "Dealer Cost: " + dealerCost
		   	   + "\nDealer Markup: " + dealerMarkup
		   	   + "\nList Price: " + getListPrice()
		   	   + "\npurchasePrice: " + purchasePrice();
		}
		*/

		car.changeMarkup(1000.00);
		System.out.println("\nafter markup changed:\n\n" + car);

		url: https://this_compiles_more_than_car_door_does.com/
	}
}