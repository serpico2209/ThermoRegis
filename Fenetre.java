package org.eclipse.om2m.ipe.sample.model;

public class Fenetre {

	public final static String LOCATION = "Home";
    public final static String TYPE = "FENETRE";
    private boolean state = false;
    private String fenetreId;
    
    public Fenetre(String fenetreId, boolean initState){
    	this.fenetreId = fenetreId;
    	this.state = initState;
    }

	public boolean getState() {
		return state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public String getFenetreId() {
		return fenetreId;
	}

	public void setFenetreId(String fenetreId) {
		this.fenetreId = fenetreId;
	}
}
