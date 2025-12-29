public class Bus extends Vehicle implements Servicable{
    private int passengerCapacity;
    Bus(String model, int year, double basePrice, int passengerCapacity) {
        super(model, year, basePrice);
        this.passengerCapacity = passengerCapacity;
    }
    public int getPassengerCapacity() {
        return passengerCapacity;
    }
    public void setPassengerCapacity(int passengerCapacity) {
        if (passengerCapacity < 0) {
            throw new IllegalArgumentException("Passenger Capacity cannot be less than 0");
        } else {
            this.passengerCapacity = passengerCapacity;
        }
    }
    @Override
    public double calculateInsuranceFee() {
        return getBasePrice() + passengerCapacity * 100 - getAge(2025);
    }
    @Override
    public void performService() {
        System.out.println("Bus id: " + getId() + " performed system service");
    }
    @Override
    public int getServiceIntervalKm() {
        return 15000;
    }
    @Override
    public String toString() {
        return "Bus [" + super.toString() + ", passengerCapacity=" + passengerCapacity + "]";
    }
    public static void  main(String[] args) {
        Bus bus = new Bus("Golden Dragon", 2022, 30000, 4);
    }
}