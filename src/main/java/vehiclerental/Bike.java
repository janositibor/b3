package vehiclerental;

import java.time.LocalTime;

public class Bike implements Rentable{
    private static final int MINUTE_RATE=15;
    private String id;
    private LocalTime rentingTime;

    public Bike(String id) {
        this.id = id;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) (MINUTE_RATE*minutes);
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
