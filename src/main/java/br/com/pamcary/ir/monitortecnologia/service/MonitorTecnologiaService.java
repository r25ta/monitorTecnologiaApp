package br.com.pamcary.ir.monitortecnologia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrelic.api.agent.Trace;

import br.com.pamcary.ir.monitortecnologia.dao.MonitorTecnologiaDAO;
import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorTecnologiaModel;
import br.com.pamcary.ir.monitortecnologia.vo.MonitorTecnologiaVO;

@Service
public class MonitorTecnologiaService {

	@Autowired
	private MonitorTecnologiaDAO monitorTecnologiaDao;

	private String nomeTecnologia=null;
	private MonitorTecnologiaVO oMonitorTecnologiaVO = null;
	private int totalGeral;
	private int totalPvAtivo;
	private int totalDedicado;
	private static Logger LOGGER = LoggerFactory.getLogger(MonitorTecnologiaService.class);
	
	@Trace
	public MonitorTecnologiaVO getMonitorTecnologiaService(){
		totalGeral=0;
		totalPvAtivo=0;
		totalDedicado=0;
		
		List<MonitorTecnologiaModel> tecnologias = new ArrayList<MonitorTecnologiaModel>();
		
		if((this.getNomeTecnologia()!=null)&&(!this.getNomeTecnologia().trim().isEmpty())) {
			LOGGER.info(">> Iniciou busca de informacoes da tecnologia " + this.getNomeTecnologia());
			tecnologias = monitorTecnologiaDao.getByNomeTecnologia(this.getNomeTecnologia().toUpperCase());
			LOGGER.info(">> Finalizou busca de informacoes da tecnologia " + this.getNomeTecnologia());
			
		}else {
			LOGGER.info(">> Iniciou buscar de informacoes para todas tecnologias ");
			tecnologias = monitorTecnologiaDao.getAllTecnologia();
			LOGGER.info(">> Finalizou buscar de informacoes para todas tecnologias ");
			
		}		
		
		if(!tecnologias.isEmpty()){
			oMonitorTecnologiaVO = new MonitorTecnologiaVO();
			oMonitorTecnologiaVO.setTecnologias(tecnologias);
			
			/*MONTAGEM DOS INDICADORES*/
			LOGGER.info(">> Iniciou montagem dos indicadores...");
				
			for(MonitorTecnologiaModel mt : tecnologias) {
				totalGeral = totalGeral + mt.getTotal();
				totalDedicado = totalDedicado + mt.getQtdeDedicado();
				totalPvAtivo = totalPvAtivo + mt.getQtdeVeiculo();					
				
			}
			/*ARMAZENAMENTO DOS INDICADORES*/			
			oMonitorTecnologiaVO.setTotalGeral(totalGeral);
			oMonitorTecnologiaVO.setTotalDedicado(totalDedicado);
			oMonitorTecnologiaVO.setTotalPvAtivo(totalPvAtivo);
			
			LOGGER.info(">> Finalizou montagem dos indicadores...");
		}		
		
		return oMonitorTecnologiaVO;
		
		
	}
	
	public String getNomeTecnologia() {
		return nomeTecnologia;
	}

	public void setNomeTecnologia(String nomeTecnologia) {
		this.nomeTecnologia = nomeTecnologia;
	}

}
