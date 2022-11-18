import java.io.*;
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
     * @param pin read from market.csv
     * @param email read from market.csv
     * @param password read from market.csv
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

    /**
     * @param obj An object we use to check if this Seller object is equal to the object
     * @return Returns a boolean value determining if this Seller object is equal to the object passed as a
     * parameter
     */
    public boolean equalsSeller(Object obj) {
        if (obj instanceof Seller) {
            Seller seller = (Seller) obj;
            return ((seller.getStores().equals(this.stores)) &&
                    (seller.getEmail().equals(this.email)) && seller.getPin().equals(this.pin)
            && (seller.getPassword().equals(this.password)));
        }
        return false;
    }

    /**
     * @param store A store object we use to check this Seller object's ArrayList of Stores
     * @return Returns a boolean value determining if this Seller object contains the Store object passed as a
     * parameter
     */
    public boolean checkStore(Store store) {
        for (Store s : stores) {
            if (s.equalsStore(store)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param store The store object we want to add to this Seller object's ArrayList of Stores
     * @param writeFile A boolean determining if we want to write this data to the market.csv file or not
     * @return Returns a boolean value determining if the Store object was successfully added to
     * this Seller's ArrayList of Stores
     */
    public boolean addStore(Store store, boolean writeFile) {
        if (writeFile) {
            if (!checkStore(store)) {
                this.stores.add(store);
                try (PrintWriter writer = new PrintWriter(new FileWriter("market.csv", true))) {
                    writer.println(this.pin + "," + this.email + "," + this.password + "," + store.getName() + ",");
                    writer.flush();
                    return true;
                } catch (IOException ioException) {
                    System.out.println("Error writing to the market.csv file.");
                    return false;
                }
            } else {
                // seller already owns store
                return false;
            }
        } else {
            if (!checkStore(store)) {
                this.stores.add(store);
                return true;
            } else {
                return false;
            }
        }

    } // in event of adding a store

    /**
     * @param store The Store object we want to add the Shoe to
     * @param shoe The Shoe object we are adding to the specific Store object
     * @param writeFile A boolean value determining if we want to write this data to a file or not
     * @return Returns a boolean determining whether the Shoe object successfully added to the Store object
     */
    public boolean addShoe(Store store, Shoe shoe, boolean writeFile) {
        if (writeFile) { // if we want to write to market.csv file
            if (checkStore(store)) { // we need to check if this Seller object owns the Store object we pass through
                if (!store.checkShoe(shoe)) { // we need to check if this store already owns this Shoe object
                    int index = stores.indexOf(store);
                    Store s = stores.get(index);
                    s.addShoe(shoe);
                    stores.set(index, s);

                    ArrayList<String> updatedMarket = new ArrayList<>();
                    try (BufferedReader reader = new BufferedReader(new FileReader("market.csv"))) {
                        String line = "";
                        while ((line = reader.readLine()) != null) { // read from current market.csv
                            if (line.startsWith(this.pin) && line.contains(store.getName())) { // find Store object we want
                                // to add to
                                line += shoe.getName() + "," + shoe.getDescription() + "," + shoe.getPrice() + ","
                                        + shoe.getQuantity() + ",";
                                // adding shoe fields to line
                            }
                            updatedMarket.add(line); // this ArrayList should have all lines including the one
                            // we modificed
                        }
                    } catch (IOException ioException) {
                        System.out.println("Error reading to the market.csv file.");
                        return false;
                    }
                    try (PrintWriter writer = new PrintWriter(new FileWriter("market.csv"))) {
                        for (int i = 0; i < updatedMarket.size(); i++) {
                            writer.println(updatedMarket.get(i));
                            writer.flush();
                        }
                        writer.close();
                        return true;
                    } catch (IOException ioException) {
                        System.out.println("Error writing to the market.csv file.");
                        return false;
                    }
                } else {
                    // store already owns this Shoe object
                    System.out.println(store.getName() + " already owns this shoe!");
                    return false;
                }
            } else {
                // seller doesn't own store, can't add a shoe to a store they don't own
                System.out.println("You don't own this store!");
                return false;
            }
        } else {
            if (checkStore(store)) {
                if (!store.checkShoe(shoe)) {
                    int index = stores.indexOf(store);
                    Store s = stores.get(index);
                    s.addShoe(shoe);
                    stores.set(index, s);
                    return true;
                } else {
                    // store already owns this shoe object
                    System.out.println(store.getName() + " already owns this shoe!");
                    return false;
                }
            } else {
                // seller doesn't own store, can't add a shoe to a store they don't own
                System.out.println("You don't own this store!");
                return false;
            }
        }
    }

    /**
     * @param store The store object that contains the shoe we want to remove
     * @param shoe The Shoe object we want to remove this Seller object
     * @param writeFile Boolean value determining if we want to write this to the file or not
     * @return Returns a boolean value determining if the
     */
    public boolean removeShoe(Store store, Shoe shoe, boolean writeFile) {
        if (writeFile) { // if we want to write to File
            if (checkStore(store)) { // if this Seller object has the Store object
                if (store.checkShoe(shoe)) { // if the store object has the Shoe object
                    int index = this.stores.indexOf(store); // these 4 lines of code modifies this Seller object's Store arrayList DIRECTLY!!!!
                    Store tempStore = this.stores.get(index);
                    tempStore.removeShoe(shoe);
                    this.stores.set(index, tempStore);


                    ArrayList<String> updatedMarket = new ArrayList<>();
                    try (BufferedReader reader = new BufferedReader(new FileReader("market.csv"))) {
                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            if (line.startsWith(this.pin) && line.toLowerCase().contains(store.getName().toLowerCase())
                                    && line.toLowerCase().contains(shoe.getName().toLowerCase())) {
                                String[] lineArray = line.split(",");
                                Store sto = new Store(lineArray[3], this);
                                for (int i = 4; i < lineArray.length; i+=4) {
                                    Shoe shoo = new Shoe(sto, lineArray[i], lineArray[i+1], Double.parseDouble(lineArray[i+2]),
                                            Integer.parseInt(lineArray[i+3]));
                                    sto.addShoe(shoo);
                                }
                                for (Shoe shoeObj : sto.getShoes()) {
                                    if (shoeObj.equalsShoe(shoe)) {
                                        sto.removeShoe(shoeObj);
                                        line = this.pin + "," + this.email + "," + this.password + "," + sto.getName() + ",";
                                        for (int i = 0; i < sto.getShoes().size(); i++) {
                                            line += sto.getShoes().get(i).getName() + "," + sto.getShoes().get(i).getDescription() + ","
                                                    + sto.getShoes().get(i).getPrice() + "," + sto.getShoes().get(i).getQuantity() + ",";
                                        }
                                        break;
                                    }
                                }
                            }
                            updatedMarket.add(line);
                        }
                    } catch (IOException io) {
                        System.out.println("Error reading to the market.csv.");

                    }
                    try (PrintWriter writer = new PrintWriter(new FileWriter("market.csv"))) {
                        for (int i = 0; i < updatedMarket.size(); i++) {
                            writer.println(updatedMarket.get(i));
                            writer.flush();
                        }
                        writer.close();
                        return true;
                    } catch (IOException io) {
                        System.out.println("Error writing to the market.csv file.");
                        return false;
                    }
                } else {
                    // this store object does not own this shoe, therefore cannot remove shoe
                    return false;
                }
            } else {
                // This seller object does not own this store, therefore cannot remove a shoe
                return false;
            }
        } else {
            if (checkStore(store)) {
                if (store.checkShoe(shoe)) {
                    int index = this.stores.indexOf(store);
                    Store tempStore = this.stores.get(index);
                    tempStore.removeShoe(shoe);
                    this.stores.set(index, tempStore);
                    return true;
                } else {
                    // this store does not own this shoe object so cannot a remove a shoe from it
                    return false;
                }
            } else {
                // this seller does not own this store, which means we cannot remove a shoe from it
                return false;
            }
        }
    }

    /**
     * @return Returns the ArrayList of Store objects for this Seller object
     */
    public ArrayList<Store> getStores() {
        return this.stores;
    }
}