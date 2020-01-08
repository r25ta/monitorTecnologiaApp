package br.com.pamcary.ir.monitortecnologia.dao.model;

import java.io.Serializable;

public class MonitorLoginModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ctlUser;
	private String nomUser;
	private String codSenha;
	private int numNivac;
	
	

	public MonitorLoginModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MonitorLoginModel(int ctlUser, String nomUser, String codSenha, int numNivac) {
		super();
		this.ctlUser = ctlUser;
		this.nomUser = nomUser;
		this.codSenha = codSenha;
		this.numNivac = numNivac;
	}


	public int getCtlUser() {
		return ctlUser;
	}


	public void setCtlUser(int ctlUser) {
		this.ctlUser = ctlUser;
	}


	public int getNumNivac() {
		return numNivac;
	}


	public void setNumNivac(int numNivac) {
		this.numNivac = numNivac;
	}


	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	public String getCodSenha() {
		return codSenha;
	}
	public void setCodSenha(String codSenha) {
		this.codSenha = codSenha;
	}


	@Override
	public String toString() {
		return "MonitorLoginModel [ctlUser=" + ctlUser + ", nomUser=" + nomUser + ", codSenha=" + codSenha + ", numNivac="
				+ numNivac + "]";
	}
	
	
	
}