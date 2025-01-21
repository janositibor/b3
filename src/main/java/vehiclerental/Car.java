package vehiclerental;

import java.time.LocalTime;

public class Car implements Rentable{
    private String id;
    private int minuteRate;
    private LocalTime rentingTime;

    public Car(String id, int minuteRate) {
        this.id = id;
        this.minuteRate = minuteRate;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) (minuteRate*minutes);
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public void rent(LocalTime time) {
        rentingTime=time;
    }

    @Override
    public void closeRent() {
        rentingTime=null;
    }
}
