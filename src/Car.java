public class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String model, int year, double basePrice, int numberOfDoors) {
        super(model, year, basePrice);
        setNumberOfDoors(numberOfDoors);
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        if (numberOfDoors < 2 || numberOfDoors > 6) {
            throw new IllegalArgumentException("Number of doors must be between 2 and 6.");
        }
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public double calculateInsuranceFee() {
        int age = getAge(java.time.Year.now().getValue());
        double base = getBasePrice();

        double percent = 0.05;          // 5% of base price
        double ageSurcharge = 20.0 * age; // +20 per year
        double doorFactor = 10.0 * Math.max(0, numberOfDoors - 2); // slightly higher for more doors

        return (base * percent) + ageSurcharge + doorFactor;
    }

    @Override
    public void performService() {
        System.out.println("Car #" + getId() + " (" + getModel() + "): oil change, brake check, tire pressure check.");
    }

    @Override
    public int getServiceIntervalKm() {
        return 10000;
    }

    @Override
    public String toString() {
        return "Car{id=" + getId() +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", basePrice=" + getBasePrice() +
                ", doors=" + numberOfDoors +
                '}';
    }
}
