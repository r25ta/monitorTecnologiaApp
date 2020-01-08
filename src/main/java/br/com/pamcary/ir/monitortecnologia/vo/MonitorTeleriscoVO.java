package br.com.pamcary.ir.monitortecnologia.vo;

public class MonitorTeleriscoVO {
	private long ctlServiSub;
	private String desServiSub;
	private String dhrToler; 
	private String dhrUltimoAcesso;
	private String dhrExpiracao;
	private String dhrAtualServidor;
	private boolean status;
	
	
		
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getDhrAtualServidor() {
		return dhrAtualServidor;
	}
	public void setDhrAtualServidor(String dhrAtualServidor) {
		this.dhrAtualServidor = dhrAtualServidor;
	}
	public String getdhrExpiracao() {
		return dhrExpiracao;
	}
	public void setdhrExpiracao(String dhrExpiracao) {
		this.dhrExpiracao = dhrExpiracao;
	}
	public String getDhrUltimoAcesso() {
		return dhrUltimoAcesso;
	}
	public void setDhrUltimoAcesso(String dhrUltimoAcesso) {
		this.dhrUltimoAcesso = dhrUltimoAcesso;
	}
	
	public long getCtlServiSub() {
		return ctlServiSub;
	}
	public void setCtlServiSub(long ctlServiSub) {
		this.ctlServiSub = ctlServiSub;
	}
	public String getDesServiSub() {
		return desServiSub;
	}
	public void setDesServiSub(String desServiSub) {
		this.desServiSub = desServiSub;
	}
	public String getDhrToler() {
		return dhrToler;
	}
	public void setDhrToler(String dhrToler) {
		this.dhrToler = dhrToler;
	}
	
	@Override
	public String toString() {
		return "   \n***********************************************************************"
				+ "\n Objeto MonitorTeleriscoVO "
				+ "\n [ctlServiSub =" + ctlServiSub  
			    + "\n desServiSub =" + desServiSub  
			    + "\n dhrToler =" + dhrToler 
			    + "\n dhrUltimoAcesso =" + dhrUltimoAcesso 
			    + "\n dhrExpiracao =" + getdhrExpiracao() 
			    + "\n dhrAtualServidor =" + dhrAtualServidor 
			    + "\n status = " + status +"]"
			    + "\n ***********************************************************************";
	}
	
}
