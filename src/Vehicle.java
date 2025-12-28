public abstract class Vehicle implements Servicable {
    protected int id;
    protected static int idGen = 1;

    private String model;
    private int year;
    private double basePrice;

    protected Vehicle(String model, int year, double basePrice) {
        this.id = idGen++;
        setModel(model);
        setYear(year);
        setBasePrice(basePrice);
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model must not be null or empty.");
        }
        this.model = model.trim();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        int minYear = 1886; // first modern car
        int maxYear = 2100; // "reasonable range" future-safe
        if (year < minYear || year > maxYear) {
            throw new IllegalArgumentException("Year must be in a reasonable range.");
        }
        this.year = year;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Base price must be > 0.");
        }
        this.basePrice = basePrice;
    }

    public int getAge(int currentYear) {
        if (currentYear < year) {
            return 0; // or throw; keeping it tolerant for console input
        }
        return currentYear - year;
    }

    public abstract double calculateInsuranceFee();

    @Override
    public String toString() {
        return "Vehicle{id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", basePrice=" + basePrice +
                '}';
    }
}
