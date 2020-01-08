package br.com.pamcary.ir.monitortecnologia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.newrelic.api.agent.Trace;

import br.com.pamcary.ir.monitortecnologia.dao.MonitorSubServicoSistemaDAO;
import br.com.pamcary.ir.monitortecnologia.dao.config.OracleDataSourceConfig;
import br.com.pamcary.ir.monitortecnologia.dao.mapper.MonitorSubSistemaMapper;
import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorSubServicoSistemaModel;

@Repository
public class MonitorSubServicoSistemaDAOImpl extends JdbcDaoSupport implements MonitorSubServicoSistemaDAO {

	private static Logger LOGGER = LoggerFactory.getLogger(MonitorSubServicoSistemaDAOImpl.class);
	
	private final static String SQL_SUB_SERVICO_SISTEMA_TELERISCO = " SELECT CTL_SERVI_SUB, DES_SERVI_SUB, TO_CHAR(DHR_ULTIM_PRO,'DD/MM/YYYY HH24:MI:SS') AS DHR_ULTIM_PRO, TO_CHAR(DHR_TOLER,'HH24:MI') AS DHR_TOLER " + 
																	 "	FROM SUB_SERVICO_SISTEMA " +
																	 " WHERE CTL_SERVI_SUB = 412";
	
	@Autowired
	OracleDataSourceConfig oracleDataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(oracleDataSource.oracleJndiDataSource());
	}
	
	@Trace
	public List<MonitorSubServicoSistemaModel> getSubServicoSistemaTelerisco() {
		LOGGER.debug("Executando metodo getSubServicoSistemaTelerisco...");

		List<MonitorSubServicoSistemaModel> lstSubServicoSistema = new ArrayList<MonitorSubServicoSistemaModel>();
		
		try {
			LOGGER.debug("SQL ->" + SQL_SUB_SERVICO_SISTEMA_TELERISCO);
			lstSubServicoSistema =	getJdbcTemplate().query(SQL_SUB_SERVICO_SISTEMA_TELERISCO
															 , new MonitorSubSistemaMapper());
			
		}catch(Exception e) {
			LOGGER.error("\n Erro ao consultar Sub Servico Sistema Telerisco! " + SQL_SUB_SERVICO_SISTEMA_TELERISCO);
			LOGGER.error("\n Causa do Erro ->"+ e);
			e.printStackTrace();
			
		}
		
		return lstSubServicoSistema;
	}
}