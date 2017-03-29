public class Customer {
    private String name;
    private int idNum;

    // constructs a Customer with given name and ID number
    public Customer(String name, int idNum) {
        this.name = name;
        this.idNum = idNum;
    }

    // returns the customer's name
    public String getName() {
        return name;
    }

    // returns the customer's id
    public int getID() {
        return idNum;
    }

    // returns 0 when this customer is equal to other;
    // a positive integer when this customer is greater than other;
    // a negative integer when this customer is less than other
    public int compareCustomer(Customer other) {
        if (getName().compareTo(other.getName()) == 0)
            return getID() - other.getID();
        else if (getName().equals(other.getName()) && getID() == other.getID())
            return 0;
        else
            return getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return getName() + " " + getID();
    }
}
