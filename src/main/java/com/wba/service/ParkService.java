package main.java.com.wba.service;

import java.util.Vector;

import main.java.com.wba.dto.Car;
import main.java.com.wba.dto.Parking;

public interface ParkService extends Runnable {

	public void park(Vector<Parking> sharedQ, Car car)
			throws InterruptedException;
}
