package org.eclipse.om2m.ipe.sample.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.om2m.commons.exceptions.BadRequestException;
import org.eclipse.om2m.ipe.sample.model.ThermoModel.ThermometreObserver;

public class RadModel {

	private static Map<String,Radiateur> RADIATEURS = new HashMap<String, Radiateur>();
	private static List<RadObserver> OBSERVERS = new ArrayList<RadObserver>();
	
	private RadModel(){
	}

	public static void setRadState(final String radiateurId, EtatRadiateur state) {
		checkRadIdValue(radiateurId);
		RADIATEURS.get(radiateurId).setState(state);
		notifyObservers(radiateurId, state);
	}
	
	public static EtatRadiateur getRadState(String radiateurId) {
		checkRadIdValue(radiateurId);
		return RADIATEURS.get(radiateurId).getState();
	}

	public static void checkRadIdValue(String radiateurId){
		if(radiateurId == null || !RADIATEURS.containsKey(radiateurId)){
			throw new BadRequestException("Identifiant radiateur inconnu");
		}
	}
	
	public static void addObserver(RadObserver obs){
		if(!OBSERVERS.contains(obs)){
			OBSERVERS.add(obs);
		}
	}
	
	public static void deleteObserver(RadObserver obs){
		if(OBSERVERS.contains(obs)){
			OBSERVERS.remove(obs);
		}
	}
	
	private static void notifyObservers(final String radId, final EtatRadiateur state){
		new Thread(){
			@Override
			public void run() {
				for(RadObserver obs: OBSERVERS){
					obs.onRadStateChange(radId, state);
				}
			}
		}.start();
	}
	
	public static interface RadObserver{
		void onRadStateChange(String radiateurId, EtatRadiateur state);
	}

	public static void setModel(Map<String, Radiateur> radiateurs) {
		RADIATEURS = radiateurs;
	}
}
