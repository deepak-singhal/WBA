package main.java.com.wba.controller;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.java.com.wba.dto.Car;
import main.java.com.wba.dto.Parking;
import main.java.com.wba.service.ParkService;
import main.java.com.wba.service.ParkServiceImpl;
import main.java.com.wba.service.UnparkService;
import main.java.com.wba.service.UnparkServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ParkingController {

	static Vector<Parking> sharedQ = new Vector<Parking>();
	static Car car;
	ParkService parkService;
	UnparkService unparkService;
	static Logger logger = LogManager.getLogger(ParkingController.class);

	@RequestMapping(value = { "/", "/parking" })
	protected void parking() {
		parkService = new ParkServiceImpl(sharedQ);
		unparkService = new UnparkServiceImpl(sharedQ);

		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(parkService);
		executor.execute(unparkService);

		executor.shutdown();
	}
	
	@Scheduled(fixedRate = 600000)
    public void unparkAll()
    {
		try {
			unparkService = new UnparkServiceImpl();
			logger.info("Routine vacating all parking . . .");
			unparkService.unparkAll(sharedQ);
		} catch (InterruptedException e) {
			logger.error("Error while emptying the parking (Routine)");
		}
    }
}
