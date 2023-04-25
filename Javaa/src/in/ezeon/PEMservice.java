package in.ezeon;

import java.io.IOException;
import java.util.*;

public class PEMservice {
    Repository repo = Repository.getRepository();
    ReportService reportService = new ReportService();
    private Scanner sc = new Scanner(System.in);
    private String choice;

    public void showMenu() {
        while (true) {
            printMenu();
            switch (choice) {
                case "1":
                    onAddCategory();
                    pressAnyKeyToContinue();
                    break;
                case "2":
                    onCategoryList();
                    pressAnyKeyToContinue();
                    break;
                case "3":
                    onExpenseEntry();
                    pressAnyKeyToContinue();
                    break;
                case "4":
                    onExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case "5":
                    onCategorizedExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case "0":
                    onExit();
                    break;
                default:
                    System.out.println("You are only allowed to enter number b/w 0 to 5");
            }
        }
    }

    public void printMenu() {
        System.out.println("----------Personal Expense Menu----------");
        System.out.println("1.Add Category");
        System.out.println("2.Category List");
        System.out.println("3.Expense Entry");
        System.out.println("4.Expense List");
        System.out.println("5.Categorized Expense List");
        System.out.println("0.Exit");
        System.out.println("-----------------------------------------");
        System.out.print("Enter Your Choice: ");
        choice = sc.next();
    }

    public void pressAnyKeyToContinue() {
        System.out.println("Press any key to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onAddCategory() {
        sc.nextLine();
        System.out.print("Enter Category Name: ");
        String catName = sc.nextLine();
        Category cat = new Category(catName);
        repo.catList.add(cat);
        System.out.println("Success: Category Added");
    }

    public void onCategoryList() {
        System.out.println("Listing Categories");
        System.out.println("Category List");
        List<Category> clist = repo.catList;
        for (int i = 0; i < clist.size(); i++) {
            Category c = clist.get(i);
            System.out.println((i + 1) + ". " + c.getName() + ", " + c.getCategoryId());
        }

    }

    public void onExpenseEntry() {
        System.out.println("Enter Details for Expense Entry...");
        onCategoryList();
        System.out.print("Choose Category: ");
        int catChoice = sc.nextInt();
        Category selectedCat = repo.catList.get(catChoice - 1);

        System.out.println("Enter Amount: ");
        float amount = sc.nextFloat();

        System.out.println("Enter Remark: ");
        sc.nextLine();
        String remark = sc.nextLine();

        // TODO Date can be take as input from user also
        Date date = new Date();

        // Add Expense detail in Expense object
        Expense exp = new Expense();
        exp.setCategoryId(selectedCat.getCategoryId());
        exp.setAmount(amount);
        exp.setRemark(remark);
        exp.setDate(date);

        // Store Expense object in repository
        repo.expList.add(exp);
        System.out.println("Success: Expense Added Successfully");
    }

    public void onExpenseList() {
        System.out.println("Expense Listing");
        List<Expense> expList = repo.expList;
        for (int i = 0; i < expList.size(); i++) {
            Expense exp = expList.get(i);
            String catName = reportService.getCategoryNameById(exp.getCategoryId());
            System.out.println(
                    (i + 1) + ". " + catName + ", " + exp.getAmount() + ", " + exp.getRemark() + ", " + exp.getDate());
        }
    }

    public void onCategorizedExpenseList() {
        System.out.println("Categorized Expense List");
        Map<String, Float> resultMap = reportService.calculateCategoryTotal();
        Set<String> categories = resultMap.keySet();
        float netTotal = 0.0f;
        for (String categoryName : categories) {
            float catWisetTotal = resultMap.get(categoryName);
            netTotal = netTotal + catWisetTotal;
            System.out.println(categoryName + " : " + catWisetTotal);
        }
        System.out.println("<----------------------------------------------------->");
        System.out.println("Net Total : " + netTotal);
    }

    public void onExit() {
        System.exit(0);
    }

}
