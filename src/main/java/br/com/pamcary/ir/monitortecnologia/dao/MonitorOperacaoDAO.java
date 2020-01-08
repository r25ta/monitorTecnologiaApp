package br.com.pamcary.ir.monitortecnologia.dao;

import java.util.List;

import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorOperacaoModel;

public interface MonitorOperacaoDAO {
	public List<MonitorOperacaoModel> getOperacaoViagemAtivaByID(int ctlProveTen);
	
	public List<MonitorOperacaoModel> getAllOperacaoViagemAtiva();
}
