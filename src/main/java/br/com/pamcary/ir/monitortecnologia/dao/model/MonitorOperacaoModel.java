package br.com.pamcary.ir.monitortecnologia.dao.model;

import java.io.Serializable;

public class MonitorOperacaoModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ctlProveTen;
	private String desProveTen;
	private int ctlOperaTip;
	private String desOperaTip;
	private int totalViagem;
	
	
		
	/**
	 * @return the ctlProveTen
	 */
	public int getCtlProveTen() {
		return ctlProveTen;
	}



	/**
	 * @param ctlProveTen the ctlProveTen to set
	 */
	public void setCtlProveTen(int ctlProveTen) {
		this.ctlProveTen = ctlProveTen;
	}



	/**
	 * @return the desProveTen
	 */
	public String getDesProveTen() {
		return desProveTen;
	}



	/**
	 * @param desProveTen the desProveTen to set
	 */
	public void setDesProveTen(String desProveTen) {
		this.desProveTen = desProveTen;
	}



	/**
	 * @return the ctlOperaTip
	 */
	public int getCtlOperaTip() {
		return ctlOperaTip;
	}



	/**
	 * @param ctlOperaTip the ctlOperaTip to set
	 */
	public void setCtlOperaTip(int ctlOperaTip) {
		this.ctlOperaTip = ctlOperaTip;
	}



	/**
	 * @return the desOperaTip
	 */
	public String getDesOperaTip() {
		return desOperaTip;
	}



	/**
	 * @param desOperaTip the desOperaTip to set
	 */
	public void setDesOperaTip(String desOperaTip) {
		this.desOperaTip = desOperaTip;
	}



	/**
	 * @return the totalViagem
	 */
	public int getTotalViagem() {
		return totalViagem;
	}



	/**
	 * @param totalViagem the totalViagem to set
	 */
	public void setTotalViagem(int totalViagem) {
		this.totalViagem = totalViagem;
	}



	public MonitorOperacaoModel() {
		// TODO Auto-generated constructor stub
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OperacaoModel [ctlProveTen=" + ctlProveTen + ", desProveTen=" + desProveTen + ", ctlOperaTip="
				+ ctlOperaTip + ", desOperaTip=" + desOperaTip + ", totalViagem=" + totalViagem + "]";
	}

	
	
}
