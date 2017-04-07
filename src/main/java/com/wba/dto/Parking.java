package main.java.com.wba.dto;

public class Parking {
	private Integer id;
	private Integer level;
	private Integer carId;

	public Parking(Integer id, Integer level, Integer carId) {
		super();
		this.id = id;
		this.level = level;
		this.carId = carId;
	}

	public Parking() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}
}
