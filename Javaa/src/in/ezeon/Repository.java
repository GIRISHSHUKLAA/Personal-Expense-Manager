package in.ezeon;

import java.util.ArrayList;
import java.util.List;

// The class is used as Database/Repository, and it's a singleton
// You cannot create multiple object of singleton class
public class Repository {
    public List<Expense> expList = new ArrayList<>();
    public List<Category> catList = new ArrayList<>();
    private static Repository repository; //Declaring instance

//    We will make Repository singleton by making constructor private
//    Otherwise it will create new list each time we create object
//    We restrict Repository by making constructor as private, so we cannot create its object outside

    private Repository() {

    }

    public static Repository getRepository() {
        if (repository == null) {
            repository = new Repository(); // You can use the private constructor in same class
        }
        return repository;
    }
}
