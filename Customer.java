import java.util.ArrayList;
/**
 * Connor Underwood, Zeyad Adham, Suhani Yadav, Neel Acharya
 *
 * A customer class
 */
public class Customer {

    private String pin; // -> every customer pin is 5 digits!

    private String email; // can change

    private String password; // can change

    private ArrayList<Shoe> purchaseHistory; // this will be used for this Customer object's purchase history

    private ArrayList<Shoe> shoppingCart; // discuss with team how we want to implement shopping cart

    public Customer(String pin, String email, String password) {
        this.pin = pin;
        this.email = email;
        this.password = password;
        this.purchaseHistory = new ArrayList<>();
        this.shoppingCart = new ArrayList<>();
    }

    public String getPin() {
        return this.pin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Customers can view a dashboard with store and seller information.
     * Data will include a list of stores by number of products sold and
     * a list of stores by the products purchased by that particular customer.
     * Customers can choose to sort the dashboard.
     */
    public void viewStatistics(boolean sort) {
        if (sort) {

        } else {

        }
    }

    /**
     * @param search Boolean value. Will ask in main if user would like to search
     * @param searchString If search is true, set searchString to their input. Otherwise, set searchString to "" empty string
     */
    public void viewMarket(boolean search, String searchString) {

    }




}
