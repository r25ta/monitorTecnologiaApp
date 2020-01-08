package br.com.pamcary.ir.monitortecnologia.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newrelic.api.agent.Trace;

import br.com.pamcary.ir.monitortecnologia.dao.MonitorSubServicoSistemaDAO;
import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorSubServicoSistemaModel;
import br.com.pamcary.ir.monitortecnologia.helper.Util;
import br.com.pamcary.ir.monitortecnologia.vo.MonitorTeleriscoVO;

@Service
public class MonitorTeleriscoService {

	private static Logger LOGGER = LoggerFactory.getLogger(MonitorTeleriscoService.class);
	
	@Autowired
	MonitorSubServicoSistemaDAO oMonitorSubServicoSistemaDAO;
	
	@Trace
	public MonitorTeleriscoVO getStatusTelerisco() {
		MonitorTeleriscoVO oMonitorTeleriscoVO = new MonitorTeleriscoVO();
		boolean status = false;
		
		
		List<MonitorSubServicoSistemaModel> lstSubServicoSistema = new ArrayList<MonitorSubServicoSistemaModel>();
		
		LOGGER.info(">> Iniciou consulta ao servico Telerisco...");
		lstSubServicoSistema = oMonitorSubServicoSistemaDAO.getSubServicoSistemaTelerisco();
		LOGGER.info(">> Finalizou consulta ao servico Telerisco...");
		
		if(!lstSubServicoSistema.isEmpty()) {
			LOGGER.info(">> Iniciou montagem do objeto de retorno...");
			
			for (MonitorSubServicoSistemaModel monitorSubServicoSistemaModel : lstSubServicoSistema) {
				
				oMonitorTeleriscoVO.setCtlServiSub(monitorSubServicoSistemaModel.getCtlServiSub());
				oMonitorTeleriscoVO.setDesServiSub(monitorSubServicoSistemaModel.getDesServiSub());
				oMonitorTeleriscoVO.setDhrToler(monitorSubServicoSistemaModel.getDhrToler());				 
				oMonitorTeleriscoVO.setDhrUltimoAcesso(monitorSubServicoSistemaModel.getDhrUltimPro());
				oMonitorTeleriscoVO.setdhrExpiracao(Util.getInstance().adicionarToleranciaNaData(oMonitorTeleriscoVO.getDhrUltimoAcesso(),oMonitorTeleriscoVO.getDhrToler()));
				
				
			}
			LOGGER.info(">> Finalizou montagem do objeto de retorno...");
			
			
		}

		/*CAPTURA SYSTADE DO SERVIDOR*/
		LocalDateTime dtAtual = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		
		oMonitorTeleriscoVO.setDhrAtualServidor(dtAtual.format(formato));
		if((oMonitorTeleriscoVO.getdhrExpiracao()!=null) &&(!oMonitorTeleriscoVO.getdhrExpiracao().trim().equals(""))){
			status = Util.getInstance().comparaDataUltProcETolerComDataSistema(oMonitorTeleriscoVO.getdhrExpiracao(), dtAtual.format(formato));
		}
		oMonitorTeleriscoVO.setStatus(status);
		
		return oMonitorTeleriscoVO;
	}
	
}
