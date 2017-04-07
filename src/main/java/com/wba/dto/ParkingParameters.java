package main.java.com.wba.dto;

public enum ParkingParameters {
	MAX_SIZE  (10) ;


    private int value;

    private ParkingParameters(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}
