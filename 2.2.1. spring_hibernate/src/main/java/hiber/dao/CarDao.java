package hiber.dao;

import hiber.model.User;

public interface CarDao {
    User getCarOwner(String model, int series);
}
