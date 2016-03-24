package org.eclipse.om2m.ipe.sample.model;

public class Radiateur {

    public final static String LOCATION = "Home";
    public final static String TYPE = "RADIATEUR";
    private EtatRadiateur state = EtatRadiateur.Eteint;
    private String radiateurId;
    
    public Radiateur(String radiateurId, EtatRadiateur initState){
    	this.radiateurId = radiateurId;
    	this.state = initState;
    }

	public EtatRadiateur getState() {
		return state;
	}
	
	public void setState(EtatRadiateur state) {
		this.state = state;
	}
	
	public String getRadiateurId() {
		return radiateurId;
	}

	public void setRadiateurId(String radiateurId) {
		this.radiateurId = radiateurId;
	}
}
