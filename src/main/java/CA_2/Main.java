package CA_2;

import java.util.*;
import java.io.*;

public class Main {
    private static final String FILENAME = "Applicants_Form.txt";
    private static List<Employee> employees = new ArrayList<>();
    private static List<String> sortedNames = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static BinaryTree hierarchy = new BinaryTree();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getIntInput("Selection: ");
            MenuOption option = MenuOption.fromId(choice);

            if (option == null) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            switch (option) {
                case SORT:
                    handleSort();
                    break;
                case SEARCH:
                    handleSearch();
                    break;
                case ADD_RECORDS:
                    handleAddRecord();
                    break;
                case CREATE_BINARY_TREE:
                    handleCreateBinaryTree();
                    break;
                case EXIT:
                    exit = true;
                    System.out.println("Exiting program. Goodbye!");
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Organization Prototype Menu ---");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.getId() + ". " + option.getDescription());
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print(prompt);
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return input;
    }

    // Skeletons for now, implementation in following commits
    private static void handleSort() { System.out.println("Sort selected (Skeleton)"); }
    private static void handleSearch() { System.out.println("Search selected (Skeleton)"); }
    private static void handleAddRecord() { System.out.println("Add Record selected (Skeleton)"); }
    private static void handleCreateBinaryTree() { System.out.println("Binary Tree selected (Skeleton)"); }
}
