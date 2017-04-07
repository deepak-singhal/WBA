package main.java.com.wba.service;

import java.util.Vector;

import main.java.com.wba.context.Factory;
import main.java.com.wba.dao.ParkingDao;
import main.java.com.wba.dto.Car;
import main.java.com.wba.dto.Parking;
import main.java.com.wba.dto.ParkingParameters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class ParkServiceImpl implements ParkService {
	Vector<Parking> sharedQ;
	ApplicationContext context;
	ParkingDao dao;
	
	Logger logger = LogManager.getLogger(ParkServiceImpl.class);

	public ParkServiceImpl() {
	}

	public ParkServiceImpl(Vector<Parking> sharedQ) {
		this.sharedQ = sharedQ;
		context = Factory.getContext();
		dao = (ParkingDao) context.getBean("parkingDao");
	}

	public void run() {
		while (true) {
			for (int i = 1; i <= ParkingParameters.MAX_SIZE.getValue(); i++) {
				try {
					Car c = new Car(i, "Model" + i, "colour" + i, i);
					park(sharedQ, c);
					logger.info("Car Parked. Registration Id : "+c.getRegId());
				} catch (InterruptedException e) {
					logger.error("Parking Failure. Reason Interrupted : " + e);
				}catch(Exception e){}
			}
			try {
				Thread.sleep(600000);
			} catch (Exception e) {}
		}
	}

	public synchronized void park(Vector<Parking> sharedQ, Car car)
			throws InterruptedException {
		while (sharedQ.size() >= 10) {
			logger.info("Parking Full. Patrons (Cars) has to wait . . .");
			sharedQ.wait();
		}
		Parking freeParking = dao.getFreeParking();
		freeParking.setCarId(car.getId());
		sharedQ.add(freeParking);
		dao.saveCar(car);
		dao.updateParking(freeParking);
		logger.info("Car " + car.getRegId() + " parked at "+car.getId());
		sharedQ.notifyAll();
	}
}
