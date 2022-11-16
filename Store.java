import java.util.ArrayList;
/**
 * Connor Underwood, Zeyad Adham, Suhani Yadav, Neel Acharya
 *
 * A Store class
 */
public class Store {
    /**
     * ArrayList of Customer -> Sellers can view a dashboard that lists statistics for each of their stores
     * Data will include a list of customers with the number of items
     * that they have purchased and a list of products with the number of sales.
     *
     * Store 1: [Customer1,xItemsPurchased,product1,product2,product3]
     * xItems purchased IS THE SALES!
     * worry about this later when we get to Customer implementation
     *
     */
    private Seller seller; // each singular store has only one seller Each store has one seller associated with it
    // Each store has one seller associated with it. Another Seller may not have the same store

    private ArrayList<Shoe> shoes; // each store has a list of shoes

    private String name; // each store has a name

    private int sales; // each store has a number of sales that increase with each purchase from a customer

    private ArrayList<Customer> customers; // each store has a list of customers that have purchased something from them

    public Store(String name, Seller seller) { // to make a store, you only need a name
        this.sales = 0; // initialize sales to 0, may have to change this when we
        // implement customers because when we read from file we need to get the sales of store

        this.name = name; // passed through constructor from file we read from

        this.seller = seller; // passed through constructor from file we read from

        this.shoes = new ArrayList<>(); // new arraylist of shoes for every store
        // that is created, so we can add to it and not get null pointer exception

        this.customers = new ArrayList<>(); // new arraylist of customers for every store that is created,
        // so we can add customers to it if they purchase from this store specifically
    }


    /**
     * Sellers should be able to call this method in their ArrayList
     * of Stores to add a Shoe object to that specific Store
     * Adds a Shoe object to this Store's Shoe ArrayList
     * @param shoe
     */
    public void addShoe(Shoe shoe) {
        this.shoes.add(shoe);
    }


    /**
     * Sellers should be able to call this method in their ArrayList
     * of Stores to remove a Shoe object from that specific Store
     *
     * Removes a shoe object from this Store's arrayList
     * @param shoe
     */
    public void removeShoe(Shoe shoe) {
        this.shoes.remove(shoe);
    }

    /**
     * Discuss with team how we want to do this method
     * @param shoe
     */
    public void editShoe(Shoe shoe) {

    }


    /**
     * @return the name of this Store object
     */
    public String getName() {
        return this.name;
    }



    /**
     * Discuss with team how we want to do this
     * @param obj
     * @return True if obj is equal to another Store object
     * E.g. all params are equal
     */
    public boolean equalsStore(Object obj) {
        return true;
    }
}
