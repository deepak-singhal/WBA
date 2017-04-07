package main.java.com.wba.dto;

public class Car {
	private int id;
	private String model;
	private String colour;
	private long regId;

	public Car(int id, String model, String colour, long regId) {
		super();
		this.id = id;
		this.model = model;
		this.colour = colour;
		this.regId = regId;
	}

	public Car() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public long getRegId() {
		return regId;
	}

	public void setRegId(long regId) {
		this.regId = regId;
	}

}
