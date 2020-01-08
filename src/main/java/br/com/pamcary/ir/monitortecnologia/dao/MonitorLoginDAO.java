package br.com.pamcary.ir.monitortecnologia.dao;

import java.util.List;

import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorLoginModel;

public interface MonitorLoginDAO {
	public List<MonitorLoginModel> getLoginSenha(String usuario, String senha);
	
}
