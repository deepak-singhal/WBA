package main.java.com.wba.service;

import java.util.Vector;

import main.java.com.wba.dto.Parking;

public interface UnparkService extends Runnable{

	public void unpark(Vector<Parking> sharedQ)
			throws InterruptedException;
	
	public void unparkAll(Vector<Parking> sharedQ)
			throws InterruptedException;

}
