package CA_2;

/**
 * Enum for Terminal Menu options.
 */
public enum MenuOption {
    SORT(1, "Sort Names Alphabetically"),
    SEARCH(2, "Search for Employee"),
    ADD_RECORDS(3, "Add New Employee Record"),
    CREATE_BINARY_TREE(4, "Generate Employee Hierarchy Binary Tree"),
    EXIT(5, "Exit Program");

    private final int id;
    private final String description;

    MenuOption(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }

    public static MenuOption fromId(int id) {
        for (MenuOption option : values()) {
            if (option.id == id) {
                return option;
            }
        }
        return null;
    }
}
