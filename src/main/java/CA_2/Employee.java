package CA_2;

/**
 * Parent Class for all employees in the organization.
 */
public abstract class Employee {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected double salary;
    protected String jobTitle;

    public Employee(String firstName, String lastName, String email, double salary, String jobTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.jobTitle = jobTitle;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public double getSalary() { return salary; }
    public String getJobTitle() { return jobTitle; }

    @Override
    public String toString() {
        return String.format("Employee: %s %s (%s) - %s", firstName, lastName, jobTitle, email);
    }
}
