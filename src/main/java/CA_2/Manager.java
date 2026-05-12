package CA_2;

/**
 * Parent Class for all managers. Inherits from Employee.
 */
public abstract class Manager extends Employee {
    protected String managerType;

    public Manager(String firstName, String lastName, String email, double salary, String jobTitle, String managerType) {
        super(firstName, lastName, email, salary, jobTitle);
        this.managerType = managerType;
    }

    public String getManagerType() { return managerType; }

    @Override
    public String toString() {
        return String.format("Manager [%s]: %s %s - %s", managerType, firstName, lastName, jobTitle);
    }
}
