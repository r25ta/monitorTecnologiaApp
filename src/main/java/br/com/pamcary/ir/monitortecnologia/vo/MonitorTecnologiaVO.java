package br.com.pamcary.ir.monitortecnologia.vo;

import java.io.Serializable;
import java.util.List;

import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorTecnologiaModel;

public class MonitorTecnologiaVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalGeral;
	private int totalDedicado;
	private int totalPvAtivo;
	private List<MonitorTecnologiaModel> tecnologias;
	
	
	public int getTotalGeral() {
		return totalGeral;
	}
	public void setTotalGeral(int totalGeral) {
		this.totalGeral = totalGeral;
	}
	public int getTotalDedicado() {
		return totalDedicado;
	}
	public void setTotalDedicado(int totalDedicado) {
		this.totalDedicado = totalDedicado;
	}
	public int getTotalPvAtivo() {
		return totalPvAtivo;
	}
	public void setTotalPvAtivo(int totalPvAtivo) {
		this.totalPvAtivo = totalPvAtivo;
	}
	public List<MonitorTecnologiaModel> getTecnologias() {
		return tecnologias;
	}
	public void setTecnologias(List<MonitorTecnologiaModel> tecnologias) {
		this.tecnologias = tecnologias;
	}
	
}
