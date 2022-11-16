import java.util.ArrayList;
/**
 * Connor Underwood, Zeyad Adham, Suhani Yadav, Neel Acharya
 *
 * A Seller class
 */
public class Seller {
    private ArrayList<Store> stores; // stores for each seller -- can add a store but can never remove a store

    private String pin; // unique String pin for each seller -- 4 digits, CANNOT BE CHANGED!!!

    private String email; // String email for each seller -- can be changed

    private String password; // String password for each seller -- can be changed

    /**
     *
     * @param pin read from market.txt
     * @param email read from market.txt
     * @param password read from market.txt
     */
    public Seller(String pin, String email, String password) {
        this.pin = pin;
        this.stores = new ArrayList<>();
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    } // in event of change email

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    } // in event of change password

    public String getPin() {
        return this.pin;
    }

    public void addStore(Store store) {
        this.stores.add(store);
    } // in event of adding a store

    /**
     * Discuss with team if we need this method
     * @param store
     * @return
     */
    public String getStoreName(Store store) {
        for (Store s : stores) {
            if (s.getName().equals(store.getName())) {
                int index = stores.indexOf(s);
                return stores.get(index).getName();
            }
        }
        return "This seller does not own " + store.getName();
    }

    public ArrayList<Store> getStores() {
        return this.stores;
    }
}
