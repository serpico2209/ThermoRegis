package org.eclipse.om2m.ipe.sample.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.om2m.commons.exceptions.BadRequestException;
import org.eclipse.om2m.ipe.sample.model.SampleModel.LampObserver;

public class ThermoModel {

	private static Map<String,Thermometre> THERMOMETRES = new HashMap<String, Thermometre>();
	private static List<ThermometreObserver> OBSERVERS = new ArrayList<ThermometreObserver>();
	
	private ThermoModel(){
	}

	public static void setThermoTemperature(final String thermoId, int value) {
		checkThermoIdValue(thermoId);
		THERMOMETRES.get(thermoId).setTemperature(value);
		notifyObservers(thermoId, value);
	}
	
	public static int getThermoTemperature(String thermoId) {
		checkThermoIdValue(thermoId);
		return THERMOMETRES.get(thermoId).getTemperature();
	}

	public static void checkThermoIdValue(String thermoId){
		if(thermoId == null || !THERMOMETRES.containsKey(thermoId)){
			throw new BadRequestException("Identifiant thermomètre inconnu");
		}
	}
	
	public static void addObserver(ThermometreObserver obs){
		if(!OBSERVERS.contains(obs)){
			OBSERVERS.add(obs);
		}
	}
	
	public static void deleteObserver(ThermometreObserver obs){
		if(OBSERVERS.contains(obs)){
			OBSERVERS.remove(obs);
		}
	}
	
	private static void notifyObservers(final String thermoId, final int temperature){
		new Thread(){
			@Override
			public void run() {
				for(ThermometreObserver obs: OBSERVERS){
					obs.onThermoTemperatureChange(thermoId, temperature);
				}
			}
		}.start();
	}
	
	public static interface ThermometreObserver{
		void onThermoTemperatureChange(String thermoId, int temperature);
	}

	public static void setModel(Map<String, Thermometre> thermometres) {
		THERMOMETRES = thermometres;
	}
}
