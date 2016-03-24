package org.eclipse.om2m.ipe.sample.model;

public class Thermometre {

	public final static String LOCATION = "Home";
    public final static String TYPE = "THERMOMETRE";
    private int temperature;
    private String thermoId;
    
    public Thermometre(String thermometreId, int initTemperature){
    	this.thermoId = thermometreId;
    	this.temperature = initTemperature;
    }

	public int getTemperature() {
		return temperature;
	}
	
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	public String getThermometreId() {
		return thermoId;
	}

	public void setThermometreId(String thermometreId) {
		this.thermoId = thermometreId;
	}

}
