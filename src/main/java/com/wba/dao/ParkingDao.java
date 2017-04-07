package main.java.com.wba.dao;

import java.util.List;

import main.java.com.wba.dto.Car;
import main.java.com.wba.dto.Parking;

import org.springframework.orm.hibernate3.HibernateTemplate;

public interface ParkingDao {

	public void setTemplate(HibernateTemplate template);

	public void saveParking(Parking parking);

	public void updateParking(Parking parking);

	public void deleteParking(Parking parking);

	public Parking getById(int id);

	public List<Parking> getParkings();

	public Parking getFreeParking();
	
	public Parking getCarParking();
	
	public void saveCar(Car car);

	public void updateCar(Car car);
	
	public void deleteCar(Integer carId);
	
	public Car getCarById(int id);
}
