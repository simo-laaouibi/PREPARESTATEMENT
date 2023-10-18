/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tar1;

/**
 *
 * @author SIMO LAAOUIBI
 */
public class DevData {
    private String devloppeur;
	private String jour;
	private int nbscripts;
	public DevData() {
		}
	public DevData(String devloppeur,String jour,int nbscripts) {
		this.devloppeur=devloppeur;
		this.jour=jour;
		this.nbscripts=nbscripts;
	}
	
	public String getDevloppeur() {
		return devloppeur;
	}
	public void setDevloppeur(String devloppeur) {
		this.devloppeur = devloppeur;
	}
	public String getJour() {
		return jour;
	}
	public void setJour(String jour) {
		this.jour = jour;
	}
	public int getNbscripts() {
		return nbscripts;
	}
	public void setNbscripts(int nbscripts) {
		this.nbscripts = nbscripts;
	}
	@Override
	public String toString() {
		return "DevData [devloppeur=" + devloppeur + ", jour=" + jour + ", nbscripts=" + nbscripts + "]";
		}
}
    
