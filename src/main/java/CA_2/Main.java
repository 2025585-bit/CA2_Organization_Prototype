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
    private static void handleSearch() {
        if (sortedNames.isEmpty()) {
            System.out.println("List is empty. Please load data first.");
            return;
        }

        // Ensure list is sorted before binary search
        SortUtils.mergeSort(sortedNames, 0, sortedNames.size() - 1);

        System.out.print("Enter employee full name to search: ");
        String target = scanner.nextLine();

        int index = SearchUtils.binarySearch(sortedNames, target, 0, sortedNames.size() - 1);

        if (index != -1) {
            String foundName = sortedNames.get(index);
            System.out.println("Employee Found: " + foundName);
            // Search for the employee object details
            for (Employee e : employees) {
                if (e.getFullName().equalsIgnoreCase(foundName)) {
                    String managerType = (e instanceof Manager) ? ((Manager) e).getManagerType() : "N/A (Staff)";
                    System.out.println("Manager Type: " + managerType);
                    System.out.println("Job Title: " + e.getJobTitle());
                    // Since Department class doesn't store in employee directly in my current impl, 
                    // I'll just use the jobTitle info or I should have stored dept in Employee.
                    // I'll add a department field to Employee in a quick refactor or just use the data I have.
                    break;
                }
            }
        } else {
            System.out.println("Employee '" + target + "' not found.");
        }
    }
    private static void handleAddRecord() {
        System.out.println("\n--- Add New Employee Record ---");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.println("Select Manager Type:");
        System.out.println("1. Head Manager");
        System.out.println("2. Assistant Manager");
        System.out.println("3. Team Lead");
        System.out.println("4. Staff (Not a Manager)");
        int mChoice = getIntInput("Choice: ");

        System.out.println("Select Department:");
        System.out.println("1. IT Development");
        System.out.println("2. Sales");
        System.out.println("3. HR");
        int dChoice = getIntInput("Choice: ");

        String deptName;
        switch (dChoice) {
            case 1: deptName = "IT Development"; break;
            case 2: deptName = "Sales"; break;
            case 3: deptName = "HR"; break;
            default:
                System.out.println("Invalid Department selection.");
                return;
        }

        Employee employee;
        String jobTitle = "New Employee";
        String email = (firstName + "." + lastName + "@organization.com").toLowerCase();
        double salary = 30000.00;

        switch (mChoice) {
            case 1:
                employee = new HeadManager(firstName, lastName, email, salary, "Head Manager");
                break;
            case 2:
                employee = new AssistantManager(firstName, lastName, email, salary, "Assistant Manager");
                break;
            case 3:
                employee = new TeamLead(firstName, lastName, email, salary, "Team Lead");
                break;
            case 4:
                employee = new FullTimeEmployee(firstName, lastName, email, salary, "Staff");
                break;
            default:
                System.out.println("Invalid Manager Type selection.");
                return;
        }

        employees.add(employee);
        sortedNames.add(employee.getFullName());
        System.out.println("\n\"" + employee.getFullName() + "\" has been added as \"" + 
                           ((employee instanceof Manager) ? ((Manager) employee).getManagerType() : "Staff") + 
                           "\" to \"" + deptName + "\" successfully!");
    }
    private static void handleCreateBinaryTree() {
        if (employees.size() < 20) {
            System.out.println("Insufficient records (need at least 20, currently have " + employees.size() + ").");
            return;
        }

        hierarchy = new BinaryTree();
        // Insert first 20 records into the tree in level order
        for (int i = 0; i < Math.max(20, employees.size()); i++) {
            Employee e = employees.get(i);
            String managerType = (e instanceof Manager) ? ((Manager) e).getManagerType() : "Staff";
            // We'll use IT Development as default if not found (simple mapping for tree storage)
            hierarchy.insertLevelOrder(e.getFullName(), managerType, "Organization");
        }

        System.out.println("\n--- Employee Hierarchy (Level Order Traversal) ---");
        hierarchy.displayLevelOrder();

        System.out.println("\nTree Height: " + hierarchy.getHeight(hierarchy.getRoot()));
        System.out.println("Total Node Count: " + hierarchy.getNodeCount(hierarchy.getRoot()));
    }
}
