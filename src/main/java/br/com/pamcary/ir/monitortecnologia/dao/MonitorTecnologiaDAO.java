package br.com.pamcary.ir.monitortecnologia.dao;

import java.util.List;

import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorTecnologiaModel;

public interface MonitorTecnologiaDAO {
	
	public List<MonitorTecnologiaModel> getAllTecnologia();
	
	public List<MonitorTecnologiaModel> getByNomeTecnologia(String tecnologia);
	
}
