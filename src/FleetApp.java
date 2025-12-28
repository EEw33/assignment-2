import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FleetApp {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("Fleet Management System");
            System.out.println("1) Print all vehicles");
            System.out.println("2) Add new car");
            System.out.println("3) Add new bus");
            System.out.println("4) Show total yearly insurance fees");
            System.out.println("5) Show vehicles older than N years");
            System.out.println("6) Perform service for all vehicles");
            System.out.println("7) Quit");
            System.out.print("Choose an option: ");

            int choice = readIntSafe();

            switch (choice) {
                case 1 -> printAllVehicles();
                case 2 -> addNewCar();
                case 3 -> addNewBus();
                case 4 -> showTotalInsuranceFees();
                case 5 -> showVehiclesOlderThanN();
                case 6 -> performServiceForAll();
                case 7 -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("Goodbye!");
    }

    private void printAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet");
            return;
        }
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    private void addNewCar() {
        try {
            System.out.print("Model: ");
            String model = readLineNonEmpty();

            System.out.print("Year: ");
            int year = readIntSafe();

            System.out.print("Base price: ");
            double basePrice = readDoubleSafe();

            System.out.print("Number of doors: ");
            int doors = readIntSafe();

            Vehicle car = new Car(model, year, basePrice, doors);
            vehicles.add(car);

            System.out.println("Added: " + car);

            Servicable s = new Car("DemoCar", 2020, 10000, 4);
            s.performService();
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void addNewBus() {
        try {
            System.out.print("Model: ");
            String model = readLineNonEmpty();

            System.out.print("Year: ");
            int year = readIntSafe();

            System.out.print("Base price: ");
            double basePrice = readDoubleSafe();

            System.out.print("Passenger capacity: ");
            int cap = readIntSafe();

            Vehicle bus = new Bus(model, year, basePrice, cap);
            vehicles.add(bus);

            System.out.println("Added: " + bus);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void showTotalInsuranceFees() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet");
            return;
        }
        double total = 0.0;
        for (Vehicle v : vehicles) {
            total += v.calculateInsuranceFee();
        }
        System.out.printf("Total yearly insurance fees: %.2f%n", total);
    }

    private void showVehiclesOlderThanN() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet");
            return;
        }

        System.out.print("Current year: ");
        int currentYear = readIntSafe();

        System.out.print("N (years): ");
        int n = readIntSafe();

        boolean found = false;
        for (Vehicle v : vehicles) {
            if (v.getAge(currentYear) > n) {
                System.out.println(v);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No vehicles match the filter.");
        }
    }

    private void performServiceForAll() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet");
            return;
        }

        for (Vehicle v : vehicles) {
            if (v instanceof Servicable s) {
                s.performService();
                System.out.println("Service interval (km): " + s.getServiceIntervalKm());
            }
        }
    }

    private int readIntSafe() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.print("Enter a valid integer: ");
            }
        }
    }

    private double readDoubleSafe() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException ex) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    private String readLineNonEmpty() {
        while (true) {
            String line = scanner.nextLine();
            if (line != null && !line.trim().isEmpty()) {
                return line.trim();
            }
            System.out.print("Value must not be empty. Try again: ");
        }
    }
}
