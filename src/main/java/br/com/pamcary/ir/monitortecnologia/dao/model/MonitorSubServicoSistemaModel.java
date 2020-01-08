package br.com.pamcary.ir.monitortecnologia.dao.model;

import java.io.Serializable;


public class MonitorSubServicoSistemaModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private long ctlServiSub;
	private String dhrUltimPro;
	private String dhrToler;
	private String desServiSub;
	
	public String getDhrUltimPro() {
		return dhrUltimPro;
	}
	public void setDhrUltimPro(String dhrUltimPro) {
		this.dhrUltimPro = dhrUltimPro;
	}
	public long getCtlServiSub() {
		return ctlServiSub;
	}
	public void setCtlServiSub(long ctlServiSub) {
		this.ctlServiSub = ctlServiSub;
	}

	public String getDhrToler() {
		return dhrToler;
	}

	public void setDhrToler(String dhrToler) {
		this.dhrToler = dhrToler;
	}

	public String getDesServiSub() {
		return desServiSub;
	}

	public void setDesServiSub(String desServiSub) {
		this.desServiSub = desServiSub;
	}
	
}
