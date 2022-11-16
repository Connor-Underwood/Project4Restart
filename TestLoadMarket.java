import java.util.ArrayList;

public class TestLoadMarket {
    public static void main(String[] args) {
        ArrayList<Seller> sellers = new ArrayList<>();
        String s1 = "1001,email1,password1001,store_name,shoe_name,shoe_description,100,100,";
        String s2 = "1001,email1,password1001,store_name2,";
        String s3 = "1002,email2,password1002,store_name3,shoe_name,shoe_description,100,100,";
        String s4 = "1001,email1,password1001,store_name4,shoe_name,shoe_description,100,100,";
        String s5 = "1003,email3,password1003,";
        String s6 = "1004,email4,password1004,store_name5,";
        String s7 = "1003,email3,password1003,Nike,airboosts,fast,50,25";
        // sellers = {1001,1002}
        ArrayList<String> strings = new ArrayList<>();
        strings.add(s1);
        strings.add(s2);
        strings.add(s3);
        strings.add(s4);
        strings.add(s5);
        strings.add(s6);
        strings.add(s7);
        for (int i = 0; i < strings.size(); i++) {
            String[] line = strings.get(i).split(",");
            String s = line[0].replaceAll(",", "");
            if (line.length > 3) { // if the seller has either added a store or
                boolean no = false;
                int index = sellers.size();
                for (int j = 0; j < sellers.size(); j++) {
                    if (s.equals(sellers.get(j).getPin())) {
                        no = true;
                        index = j;
                    }
                }
                if (!no) {
                    Seller seller = new Seller(s, line[1], line[2]);
                    Store store = new Store(line[3], seller);
                    for (int j = 4; j < line.length; j += 4) {
                        Shoe shoe = new Shoe(store, line[j ], line[j+1], Double.parseDouble(line[j + 2]),
                                Integer.parseInt(line[j + 3]));
                        store.addShoe(shoe);
                    }
                    seller.addStore(store);
                    sellers.add(seller);
                } else {
                    String storeName = line[3].replaceAll(",", "");
                    Store store = new Store(storeName, sellers.get(index));
                    for (int j = 4; j < line.length; j += 4) {
                        Shoe shoe = new Shoe(store, line[j ], line[j+1],
                                Double.parseDouble(line[j + 2]),  Integer.parseInt(line[j+3]));
                        store.addShoe(shoe);
                    }
                    sellers.get(index).addStore(store);
                }
            } else { // if the seller hasn't added a store or product yet. we just initialize their pin, email, and password
                boolean no = false;
                int index = sellers.size();
                for (int j = 0; j < sellers.size(); j++) {
                    if (s.equals(sellers.get(j).getPin())) {
                        no = true;
                        index = j;
                    }
                }
                if (!no) {
                    Seller seller = new Seller(s, line[1], line[2]);
                    sellers.add(seller);
                } else {
                    /**
                     * do we need the code below??
                     */
//                    String storeName = line[3].replaceAll(",", "");
//                    Store store = new Store(storeName, sellers.get(index));
//                    for (int j = 4; j < line.length; j += 4) {
//                        Shoe shoe = new Shoe(store, line[j ], line[j+1],
//                                Double.parseDouble(line[j + 2]),  Integer.parseInt(line[j+3]));
//                        store.addShoe(shoe);
//                    }
//                    sellers.get(index).addStore(store);
                }
            }

        }


        System.out.println("!" + sellers.get(0).getPassword() + "!" + sellers.get(0).getPin());
        System.out.println("!" + sellers.get(0).getPassword() + "!");
        System.out.println("!" + sellers.get(0).getPassword() + "!");
        System.out.println("!" + sellers.get(1).getPassword() + "!" + sellers.get(1).getPin());
        System.out.println("!" + sellers.get(2).getPassword() + "!" + sellers.get(2).getStores().get(0).getName());
        System.out.println("!" + sellers.get(3).getStores().get(0).getName() + "!");



    }
    /**
     * THIS IS LOAD MARKET!!!
     *
     * may be a problem. what if seller creates an account, logs out, logs back in, then adds a store.
     * our implemenation right now will probably make two lines instead of appending the store
     * E.g. 1003,email3,password1003,
     * E.g  1003,email3,password1003,Nike,
     *
     */
}
