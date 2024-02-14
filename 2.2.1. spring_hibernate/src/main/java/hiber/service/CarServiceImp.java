package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarServiceImp implements CarService {

    @Autowired
    private CarDao carDao;

    @Transactional(readOnly = true)
    @Override
    public String getCarOwner(String model, int series) {
        try {
            return String.format("Car %s %d owner:\n", model, series)
                    + carDao.getCarOwner(model, series);
        } catch (Exception e) {
            return "Car hasn't found";
        }
    }
}
