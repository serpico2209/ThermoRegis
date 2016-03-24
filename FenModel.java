package org.eclipse.om2m.ipe.sample.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.om2m.commons.exceptions.BadRequestException;
import org.eclipse.om2m.ipe.sample.model.ThermoModel.ThermometreObserver;

public class FenModel {

	private static Map<String,Fenetre> FENETRES = new HashMap<String, Fenetre>();
	private static List<FenObserver> OBSERVERS = new ArrayList<FenObserver>();
	
	private FenModel(){
	}

	public static void setFenState(final String fenetreId, boolean state) {
		checkFenIdValue(fenetreId);
		FENETRES.get(fenetreId).setState(state);
		notifyObservers(fenetreId, state);
	}
	
	public static boolean getFenState(String fenetreId) {
		checkFenIdValue(fenetreId);
		return FENETRES.get(fenetreId).getState();
	}

	public static void checkFenIdValue(String fenId){
		if(fenId == null || !FENETRES.containsKey(fenId)){
			throw new BadRequestException("Identifiant fenêtre inconnu");
		}
	}
	
	public static void addObserver(FenObserver obs){
		if(!OBSERVERS.contains(obs)){
			OBSERVERS.add(obs);
		}
	}
	
	public static void deleteObserver(FenObserver obs){
		if(OBSERVERS.contains(obs)){
			OBSERVERS.remove(obs);
		}
	}
	
	private static void notifyObservers(final String fenetreId, final boolean state){
		new Thread(){
			@Override
			public void run() {
				for(FenObserver obs: OBSERVERS){
					obs.onFenStateChange(fenetreId, state);
				}
			}
		}.start();
	}
	
	public static interface FenObserver{
		void onFenStateChange(String fenetreId, boolean state);
	}

	public static void setModel(Map<String, Fenetre> fenetres) {
		FENETRES = fenetres;
	}

}
