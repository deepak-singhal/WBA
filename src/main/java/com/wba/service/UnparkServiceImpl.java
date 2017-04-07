package main.java.com.wba.service;

import java.util.Iterator;
import java.util.Vector;

import main.java.com.wba.context.Factory;
import main.java.com.wba.dao.ParkingDao;
import main.java.com.wba.dto.Car;
import main.java.com.wba.dto.Parking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class UnparkServiceImpl implements UnparkService {
	Vector<Parking> sharedQ;
	ParkingDao dao;
	ApplicationContext context;

	Logger logger = LogManager.getLogger(UnparkService.class);

	public UnparkServiceImpl() {}

	public UnparkServiceImpl(Vector<Parking> sharedQ) {
		this.sharedQ = sharedQ;
		context = Factory.getContext();
		dao = (ParkingDao) context.getBean("parkingDao");
	}

	public void run() {
		while (true) {
			try {
				unpark(sharedQ);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				logger.error("Error while Unparking. Reason Interrupted: " + e);
			}
			catch (Exception e) {}
		}
	}

	public synchronized void unpark(Vector<Parking> sharedQ)
			throws InterruptedException {
		while (sharedQ.isEmpty()) {
			logger.info("Parking is Empty. Wait to fill some Parking slot.");
			sharedQ.wait();
		}
		Parking parking = dao.getCarParking();
		Car car = dao.getCarById(parking.getCarId());
		parking.setCarId(null);
		dao.updateParking(parking);
		
		sharedQ.remove(parking);
		
		logger.info("Car " + parking.getCarId()
				+ " has been unparked from " + parking.getId());
		sharedQ.notifyAll();
	}

	public void unparkAll(Vector<Parking> sharedQ) throws InterruptedException {
		logger.info("Since One hour lapsed, Hence emptying all the Parkings ...");
		Iterator<Parking> iterator = sharedQ.iterator();
		while(iterator.hasNext()){
			sharedQ.removeAllElements();
			Parking currentParking = iterator.next();
			if(currentParking.getCarId() != null){
				dao.deleteCar(currentParking.getCarId());
				currentParking.setCarId(null);
				dao.updateParking(currentParking);
			}
		}
		logger.info("All parkings cleared");
	}

}
