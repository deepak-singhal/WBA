package main.java.com.wba.dao;

import java.util.ArrayList;
import java.util.List;

import main.java.com.wba.dto.Car;
import main.java.com.wba.dto.Parking;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class ParkingDaoImpl implements ParkingDao{

	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public void saveParking(Parking parking) {
		template.save(parking);
	}

	public void updateParking(Parking parking) {
		template.update(parking);
	}

	public void deleteParking(Parking parking) {
		template.delete(parking);
	}

	public Parking getById(int id) {
		Parking parking = (Parking) template.get(Parking.class, id);
		return parking;
	}

	public List<Parking> getParkings() {
		List<Parking> list = new ArrayList<Parking>();
		list = template.loadAll(Parking.class);
		return list;
	}
	
	public Parking getFreeParking() {
		List<Parking> list = new ArrayList<Parking>();
		list = (List<Parking>)template.find("FROM Parking WHERE carId IS NULL");
		return list.get(0);
	}
	
	public Parking getCarParking(){
		List<Parking> list = new ArrayList<Parking>();
		list = (List<Parking>)template.find("FROM Parking WHERE carId IS NOT NULL");
		return list.get(0);
	}
	
	public void saveCar(Car car) {
		template.save(car);
	}

	public void updateCar(Car car) {
		template.update(car);
	}
	
	public void deleteCar(Integer carId){
		Car car=template.get(Car.class, carId);
		template.delete(car);
	}
	
	public Car getCarById(int id){
		Car car = (Car) template.get(Car.class, id);
		return car;
	}
}
