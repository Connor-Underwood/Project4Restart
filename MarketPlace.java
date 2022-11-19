import java.util.ArrayList;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class MarketPlace {
    /**
     * Connor Underwood, Zeyad Adham, Suhani Yadav, Neel Acharya
     * <p>
     * This is our main method where input is received from Users
     */
    private static final String WELCOME_MESSAGE = "Welcome to the Shoe Marketplace!";

    private static final String INVALID_VALUE = "Please enter a valid value.";
    private static final String INVALID_EMAIL = "Please enter a valid email (Must contain @).";
    private static final String TAKEN_EMAIL = "This e-mail has already been taken!";
    private static final String INVALID_PASSWORD = "Please enter a valid password (Must contain more than 5 characters).";
    public static final String ACCOUNT_PROMPT = "1: Sign In\n2: Create an account.";
    public static final String ENTER_YOUR_EMAIL = "Please enter your e-mail.";
    public static final String LOGIN_PASSWORD_PROMPT = "Please enter your password.";
    public static final String CREATE_PASSWORD_PROMPT = "Create a password greater than 5 characters.";
    public static final String CUSTOMER_OR_SELLER = "Choose your User Type\n1: Seller\n2: Customer";
    public static final String LOGIN_SUCCESSFUL = "Login Successful!";
    public static final String WRONG_EMAIL = "The email you entered is incorrect. Please enter another email";
    public static final String WRONG_PASSWORD = "The password you entered is incorrect. Please enter another password";

    // Seller Prompts
    public static final String ADD_STORE = "1: Add a store.";
    public static final String ADD_SHOE = "2: Add a shoe.";
    public static final String REMOVE_SHOE = "3: Remove a shoe.";
    public static final String EDIT_SHOE = "4: Edit a shoe";
    public static final String VIEW_STORES = "5: View your sales information.";
    public static final String CHANGE_EMAIL = "6: Change e-mail.";
    public static final String CHANGE_PASSWORD = "7: Change password.";
    // End of Seller Prompts
    private static ArrayList<Seller> sellers = new ArrayList<>();

    private static ArrayList<Customer> customers = new ArrayList<>();

    private static Random random = new Random(); // use this to create pins

    public static void loadMarket() {
        File market = new File("market.csv");
        if (market.exists()) { // if market exists, this means a user has already logged in before
            try (BufferedReader reader = new BufferedReader(new FileReader("market.csv"))) {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    String[] lineArray = line.split(",");
                    String cutComma = lineArray[0].replaceAll(",", "");
                    if (lineArray.length > 3) { // if the seller has either added a store or a shoe
                        boolean no = false;
                        int index = sellers.size();
                        for (int j = 0; j < sellers.size(); j++) {
                            if (cutComma.equals(sellers.get(j).getPin())) {
                                no = true;
                                index = j;
                            }
                        }
                        if (!no) { // if the for loop above doesn't execute that means the line
                            // found a unique pin, so we create a new seller
                            Seller seller = new Seller(cutComma, lineArray[1], lineArray[2]);
                            Store store = new Store(lineArray[3], seller);
                            for (int j = 4; j < lineArray.length; j += 4) {
                                Shoe shoe = new Shoe(store, lineArray[j], lineArray[j + 1], Double.parseDouble(lineArray[j + 2]),
                                        Integer.parseInt(lineArray[j + 3]));
                                store.addShoe(shoe);
                            }
                            seller.addStore(store, false); // false because we do not want to write to file
                            sellers.add(seller);
                        } else { // if the for loop did execute, that means we just need to add the stores and products,
                            // because this seller has already been added to our sellers ArrayList
                            String storeName = lineArray[3].replaceAll(",", "");
                            Store store = new Store(storeName, sellers.get(index));
                            for (int j = 4; j < lineArray.length; j += 4) {
                                Shoe shoe = new Shoe(store, lineArray[j], lineArray[j + 1],
                                        Double.parseDouble(lineArray[j + 2]), Integer.parseInt(lineArray[j + 3]));
                                store.addShoe(shoe);
                            }
                            sellers.get(index).addStore(store, false);
                        }
                    } else { // if the seller hasn't added a store or product yet. we just initialize their pin, email, and password
                        boolean no = false;
                        int index = sellers.size();
                        for (int j = 0; j < sellers.size(); j++) {
                            if (cutComma.equals(sellers.get(j).getPin())) {
                                no = true;
                                index = j;
                            }
                        }
                        if (!no) {
                            Seller seller = new Seller(cutComma, lineArray[1], lineArray[2]);
                            sellers.add(seller);
                        } else {
                            /**
                             * do we need the code below??
                             */
//                    String storeName = lineArray[3].replaceAll(",", "");
//                    Store store = new Store(storeName, sellers.get(index));
//                    for (int j = 4; j < lineArray.length; j += 4) {
//                        Shoe shoe = new Shoe(store, lineArray[j ], lineArray[j+1],
//                                Double.parseDouble(lineArray[j + 2]),  Integer.parseInt(lineArray[j+3]));
//                        store.addShoe(shoe);
//                    }
//                    sellers.get(index).addStore(store);
                        }
                    }
                }
            } catch (IOException ioException) {
                System.out.println("Error reading to the market.csv file.");
            }
        } else { // create a new file called market.csv
            try {
                boolean b = market.createNewFile();
            } catch (IOException ioException) {
                System.out.println("Could not create market.csv");
            }
        }
        File stores = new File("stores.csv");
        if (stores.exists()) {

        } else {

        }
    }
    public static boolean checkEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/accounts.csv"))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String usedEmail = line.split(",")[1];
                if (usedEmail.equalsIgnoreCase(email)) {
                    return false;
                }
            }
            reader.close();
            return true;
        } catch (IOException io) {
            System.out.println("Error reading to the accounts.csv file.");
            return false;
        }
    }
    public static boolean checkPin(String pin) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/accounts.csv"))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String usedPin = line.split(",")[0];
                if (usedPin.equals(pin)) {
                    return false;
                }
            }
            return true;
        } catch (IOException io) {
            System.out.println("Error reading to the accounts.csv file.");
            return false;
        }
    }
    public static boolean checkPassword(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/accounts.csv"))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr[1].equalsIgnoreCase(email) && arr[2].equals(password)) {
                    return true;
                }
            }
            return false;
        } catch (IOException io) {
            System.out.println("Error reading to the accounts.csv file.");
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadMarket(); // load all previous Customers and Sellers
        int pin = -1; // PIN to set user to
        String email = ""; // E-mail to set user to
        String password = ""; // Password to set user to
        int userType = -1; // Set to 1 if Seller, 2 if Customer
        /**
         * Start of Login Implementation.
         */
        System.out.println(WELCOME_MESSAGE);
        System.out.println(ACCOUNT_PROMPT);
        String choice = scanner.nextLine();

        while (!"1".equals(choice) && !"2".equals(choice)) {
            System.out.println(INVALID_VALUE);
            choice = scanner.nextLine();
        }
        /**
         * Sign-In Implementation
         */
        if ("1".equals(choice)) {
            System.out.println(ENTER_YOUR_EMAIL);
            email = scanner.nextLine();
            while (checkEmail(email)) { // while check e-mail is true, then the email they entered is not in the file, so keep asking for a valid email
                System.out.println(WRONG_EMAIL);
                email = scanner.nextLine();
            }
            System.out.println(LOGIN_PASSWORD_PROMPT);
            password = scanner.nextLine();
            while (!checkPassword(email, password)) { // while the password is not in the file, keep asking them to enter a new password
                System.out.println(WRONG_PASSWORD);
                password = scanner.nextLine();
            }
            System.out.println(LOGIN_SUCCESSFUL); // when the email and password are correct print the login successful prompt.

        } else {
            /**
             * Create an Account Implementation
             */
            File f = new File("src/accounts.csv"); // if it is the VERY FIRST USER, accounts.csv does NOT EXIST!!
            if (!f.exists()) {
                try {
                    boolean b = f.createNewFile();
                } catch (IOException io) {
                    System.out.println("Error creating the accounts.csv file.");
                }
            }

            System.out.println(ENTER_YOUR_EMAIL);
            email = scanner.nextLine();
            while (!email.contains("@")) { // Case of Invalid E-mail
                System.out.println(INVALID_EMAIL);
                email = scanner.nextLine();
            }
            while (!checkEmail(email)) { // Case of taken e-mail
                System.out.println(TAKEN_EMAIL);
                System.out.println(ENTER_YOUR_EMAIL);
                email = scanner.nextLine();
            }

            System.out.println(CREATE_PASSWORD_PROMPT);
            password = scanner.nextLine();
            while (password.length() <= 5) { // Case of Invalid Password
                System.out.println(INVALID_PASSWORD);
                password = scanner.nextLine();
            }

            System.out.println(CUSTOMER_OR_SELLER);
            userType = scanner.nextInt();
            scanner.nextLine();
            while (userType != 1 && userType != 2) { // Case of invalid input
                System.out.println(INVALID_VALUE);
                userType = scanner.nextInt();
                scanner.nextLine();
            }
            if (userType == 1) { // Means they are a Seller
                pin = random.nextInt(1000, 9999);
                while (!checkPin(Integer.toString(pin))) { // make sure pin is not taken
                    pin = random.nextInt(1000, 9999);
                }
            } else { // Means they are a Customer
                pin = random.nextInt(10000,99999);
                while (!checkPin(Integer.toString(pin))) { // make sure pin is not taken
                    pin = random.nextInt(10000, 99999);
                }
            }
            /**
             * At this point, they have created an account with completely valid credentials, so now we can write
             * to accounts.csv
             */
            try (PrintWriter writer = new PrintWriter(new FileWriter("src/accounts.csv", true))) {
                writer.println(pin + "," + email + "," + password + ",");
            } catch (IOException io) {
                System.out.println("Error writing to the accounts.csv file.");
            }

        }


    }
}
