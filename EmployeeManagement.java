import java.io.*;
import java.util.*;

class Employee {
    int id;
    String name;
    String designation;
    double salary;

    Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
    }
}

public class EmployeeManagement {
    static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) throws IOException {
        // Support a non-interactive demo mode for automated testing
        if (args != null && args.length > 0 && "--demo".equals(args[0])) {
            // add a demo employee and display
            addEmployeeDirect(new Employee(999, "Demo User", "Tester", 12345.67));
            displayEmployees();
            return;
        }

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String line;
            if (sc.hasNextLine()) {
                line = sc.nextLine().trim();
            } else {
                System.out.println("No input detected. Exiting.");
                break;
            }

            if (line.isEmpty()) {
                System.out.println("Please enter a number (1-3).\n");
                continue;
            }

            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number (1-3).\n");
                continue;
            }

            switch (choice) {
                case 1:
                    addEmployee(sc);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
        sc.close();
    }

    public static void addEmployee(Scanner sc) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            System.out.print("Enter Employee ID: ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid integer for ID.");
                sc.next();
            }
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Designation: ");
            String designation = sc.nextLine();

            System.out.print("Enter Salary: ");
            while (!sc.hasNextDouble()) {
                System.out.println("Please enter a valid number for salary.");
                sc.next();
            }
            double salary = sc.nextDouble();
            sc.nextLine();

            Employee emp = new Employee(id, name, designation, salary);
            writer.write(emp.toString());
            writer.newLine();

            System.out.println("Employee added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper used by demo mode to add an employee without interactive input
    public static void addEmployeeDirect(Employee emp) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(emp.toString());
            writer.newLine();
            System.out.println("Demo: Employee added: " + emp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Employee Records ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No employee records found.");
        }
    }
}
