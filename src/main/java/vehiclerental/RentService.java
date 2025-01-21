package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {
    private static final int MAX_RENT_DURATION=180;
    private Set<Rentable> rentables=new HashSet<>();
    private Set<User> users=new HashSet<>();

    private Map<Rentable,User> actualRenting=new TreeMap<>();

    public RentService() {
    }

    public void registerUser(User user) {
    validateUser(user);
    users.add(user);
    }

    private void validateUser(User user) {
        if(users.stream().anyMatch(u->u.getUserName().equals(user.getUserName()))){
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
    }


    public void addRentable(Rentable vehicle) {
        rentables.add(vehicle);
    }
    public void rent(User user, Rentable vehicle, LocalTime time) {
        vehicle.rent(time);
        rentValidation(user, vehicle);
        actualRenting.put(vehicle,user);
    }

    private void rentValidation(User user, Rentable vehicle) {
        if(actualRenting.keySet().contains(vehicle) ||  user.getBalance()< vehicle.calculateSumPrice(MAX_RENT_DURATION)){
            throw new IllegalStateException();
        }
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Rentable> getRentables() {
        return rentables;
    }


    public Map<Rentable, User> getActualRenting() {
        return actualRenting;
    }

    public void closeRent(Rentable vehicle, int durationInMinutes) {
        User user = actualRenting.remove(vehicle);
        vehicle.closeRent();
        if(user!=null){
            user.minusBalance(vehicle.calculateSumPrice(durationInMinutes));
        }
    }
}
