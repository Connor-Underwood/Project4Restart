/**
 * Connor Underwood, Zeyad Adham, Suhani Yadav, Neel Acharya
 *
 * A Shoe class
 */
public class Shoe {
    private Store store; // each shoe has a Store object associated with it
    private String name; // each shoe has a String name
    private String description; // each shoe has a String description
    private int quantity; // each shoe has an int quantity
    private double price; // each shoe has a double price

    /**
     * @param store Store object
     * @param name String name
     * @param description String description
     * @param price double price
     * @param quantity int quantity
     * Order of params is the order in which we read from the
     */
    public Shoe(Store store, String name, String description, double price, int quantity) {
        this.store = store;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * @return the Store object of this Shoe
     */
    public Store getStore() {
        return store;
    }

    /**
     * @param store Sets the Store object of this Shoe to [store]
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * @return the String name of this Shoe object
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Sets the String name of this Shoe object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the String description of this Shoe object
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description Sets the String description of this Shoe object
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The int quantity of this Shoe object
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity Sets the int quantity of this Shoe object
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return The double price of this Shoe object
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price Sets the double price of this Shoe object
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Discuss with team how we want to do this
     * @param obj
     * @return True if obj equals this Shoe object
     */
    public boolean equalsShoe(Object obj) {
        return true;
    }
}
