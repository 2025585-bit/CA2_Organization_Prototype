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
        loadEmployeesFromFile();
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

    private static void loadEmployeesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 8) continue;

                String firstName = data[0].trim();
                String lastName = data[1].trim();
                String email = data[3].trim();
                double salary = Double.parseDouble(data[4].trim());
                String deptName = data[5].trim();
                String jobTitle = data[7].trim();

                Employee employee;
                if (jobTitle.contains("Head Manager")) {
                    employee = new HeadManager(firstName, lastName, email, salary, jobTitle);
                } else if (jobTitle.contains("Assistant Manager")) {
                    employee = new AssistantManager(firstName, lastName, email, salary, jobTitle);
                } else if (jobTitle.contains("Senior Manager") || jobTitle.contains("Manager")) {
                    employee = new TeamLead(firstName, lastName, email, salary, jobTitle); // Defaulting to TeamLead for generic Manager
                } else if (jobTitle.contains("Team Lead")) {
                    employee = new TeamLead(firstName, lastName, email, salary, jobTitle);
                } else {
                    employee = new FullTimeEmployee(firstName, lastName, email, salary, jobTitle);
                }
                employees.add(employee);
                sortedNames.add(employee.getFullName());
            }
            System.out.println("File read successfully: " + employees.size() + " records loaded.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Skeletons for now, implementation in following commits
    private static void handleSort() {
        if (sortedNames.isEmpty()) {
            System.out.println("No records to sort.");
            return;
        }
        SortUtils.mergeSort(sortedNames, 0, sortedNames.size() - 1);
        System.out.println("\n--- Sorted List (First 20) ---");
        for (int i = 0; i < Math.min(20, sortedNames.size()); i++) {
            System.out.println((i + 1) + ". " + sortedNames.get(i));
        }
    }
    private static void handleSearch() { System.out.println("Search selected (Skeleton)"); }
    private static void handleAddRecord() { System.out.println("Add Record selected (Skeleton)"); }
    private static void handleCreateBinaryTree() { System.out.println("Binary Tree selected (Skeleton)"); }
}
