package CA_2;

/**
 * Parent Class for all departments.
 */
public abstract class Department {
    protected String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() { return departmentName; }

    @Override
    public String toString() {
        return "Department: " + departmentName;
    }
}
