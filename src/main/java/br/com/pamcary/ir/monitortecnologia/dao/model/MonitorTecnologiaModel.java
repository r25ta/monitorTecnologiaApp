package br.com.pamcary.ir.monitortecnologia.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

public class MonitorTecnologiaModel implements Serializable {	

	private static final long serialVersionUID = 1L;
	private String provedor;
	private int ctlProveTen;
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Timestamp dhrProcPamcary;
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Timestamp dhrProcForcedor;
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Timestamp dhrEvento;
	private int diff;
	private String diffFormatado;
	private int qtdeProcessado;
	private int qtdeProcessando;
	private int qtdeAProcessar;
	private int qtdeLimpezaProcessar;
	private int qtdeLimpezaProcessando;
	private int qtdeVeiculo;
	private int qtdeDedicado;
	private int total;
	
	public MonitorTecnologiaModel() {
	}



	public MonitorTecnologiaModel(	String provedor,
									int ctlProveTen,
									Timestamp dhrProcPamcary, 
									Timestamp dhrProcForcedor,
									Timestamp dhrEvento, 
									int diff,
									String diffFormatado,
									int qtdeProcessado, 
									int qtdeProcessando, 
									int qtdeAProcessar,
									int total,
									int qtdeLimpezaProcessar, 
									int qtdeLimpezaProcessando, 
									int qtdeVeiculo, 
									int qtdeDedicado) {
		super();
		this.provedor = provedor;
		this.ctlProveTen = ctlProveTen;
		this.dhrProcPamcary = dhrProcPamcary;
		this.dhrProcForcedor = dhrProcForcedor;
		this.dhrEvento = dhrEvento;
		this.diff = diff;
		this.diffFormatado = diffFormatado;
		this.qtdeProcessado = qtdeProcessado;
		this.qtdeProcessando = qtdeProcessando;
		this.qtdeAProcessar = qtdeAProcessar;
		this.total = total;
		this.qtdeLimpezaProcessar = qtdeLimpezaProcessar;
		this.qtdeLimpezaProcessando = qtdeLimpezaProcessando;
		this.qtdeVeiculo = qtdeVeiculo;
		this.qtdeDedicado = qtdeDedicado;
	}

	public String getProvedor() {
		return provedor;
	}
	public void setProvedor(String provedor) {
		this.provedor = provedor;
	}
	public Timestamp getDhrProcPamcary() {
		return dhrProcPamcary;
	}
	public void setDhrProcPamcary(Timestamp dhrProcPamcary) {
		this.dhrProcPamcary = dhrProcPamcary;
	}
	public Timestamp getDhrProcForcedor() {
		return dhrProcForcedor;
	}
	public void setDhrProcForcedor(Timestamp dhrProcForcedor) {
		this.dhrProcForcedor = dhrProcForcedor;
	}
	public Timestamp getDhrEvento() {
		return dhrEvento;
	}
	public void setDhrEvento(Timestamp dhrEvento) {
		this.dhrEvento = dhrEvento;
	}
	public int getDiff() {
		return diff;
	}
	public int getQtdeAProcessar() {
		return qtdeAProcessar;
	}
	public void setQtdeAProcessar(int qtdeAProcessar) {
		this.qtdeAProcessar = qtdeAProcessar;
	}
	public void setDiff(int diff) {
		this.diff = diff;
	}
	public int getQtdeProcessado() {
		return qtdeProcessado;
	}
	public void setQtdeProcessado(int qtdeProcessado) {
		this.qtdeProcessado = qtdeProcessado;
	}
	public int getQtdeProcessando() {
		return qtdeProcessando;
	}
	public void setQtdeProcessando(int qtdeProcessando) {
		this.qtdeProcessando = qtdeProcessando;
	}
	public int getQtdeVeiculo() {
		return qtdeVeiculo;
	}
	public void setQtdeVeiculo(int qtdeVeiculo) {
		this.qtdeVeiculo = qtdeVeiculo;
	}
	public int getQtdeDedicado() {
		return qtdeDedicado;
	}
	public void setQtdeDedicado(int qtdeDedicado) {
		this.qtdeDedicado = qtdeDedicado;
	}
	public int getQtdeLimpezaProcessar() {
		return qtdeLimpezaProcessar;
	}
	public void setQtdeLimpezaProcessar(int qtdeLimpezaProcessar) {
		this.qtdeLimpezaProcessar = qtdeLimpezaProcessar;
	}
	public int getQtdeLimpezaProcessando() {
		return qtdeLimpezaProcessando;
	}
	public void setQtdeLimpezaProcessando(int qtdeLimpezaProcessando) {
		this.qtdeLimpezaProcessando = qtdeLimpezaProcessando;
	}
	public String getDiffFormatado() {
		return diffFormatado;
	}
	public void setDiffFormatado(String diffFormatado) {		
		this.diffFormatado = diffFormatado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "MonitorTecnologiaModel [provedor=" + provedor + ", dhrProcPamcary=" + dhrProcPamcary
				+ ", dhrProcForcedor=" + dhrProcForcedor + ", dhrEvento=" + dhrEvento + ", diff=" + diff+ ", diffFormatado=" + diffFormatado
				+ ", qtdeProcessado=" + qtdeProcessado + ", qtdeProcessando=" + qtdeProcessando + ", qtdeAProcessar="
				+ qtdeAProcessar +  ", total=" + total +", qtdeLimpezaProcessar=" + qtdeLimpezaProcessar + ", qtdeLimpezaProcessando="
				+ qtdeLimpezaProcessando + ", qtdeVeiculo=" + qtdeVeiculo + ", qtdeDedicado=" + qtdeDedicado + "]";
	}



	public int getCtlProveTen() {
		return ctlProveTen;
	}



	public void setCtlProveTen(int ctlProveTen) {
		this.ctlProveTen = ctlProveTen;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}




	



	
	
	
}
