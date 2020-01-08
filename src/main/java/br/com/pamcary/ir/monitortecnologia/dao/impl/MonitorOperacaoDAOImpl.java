package br.com.pamcary.ir.monitortecnologia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.pamcary.ir.monitortecnologia.dao.MonitorOperacaoDAO;
import br.com.pamcary.ir.monitortecnologia.dao.config.OracleDataSourceConfig;
import br.com.pamcary.ir.monitortecnologia.dao.mapper.MonitorOperacaoMapper;
import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorOperacaoModel;


public class MonitorOperacaoDAOImpl extends JdbcDaoSupport implements MonitorOperacaoDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitorOperacaoDAOImpl.class);
	//private static Logger logger = LogManager.getLogger(MonitorOperacaoDAOImpl.class);
	
	private static String SQL_GET_ALL_OPERACAO_VIAGEM_ATIVA = " SELECT PROV.ctl_prove_ten, "
																+ " PROV.nom_fants, "
																+ " PV.ctl_opera_tip, "
																+ "	TP.des_opera_tip, "
																+ " COUNT(PV.ctl_plvia) total_viagem "  
																+ "	FROM V_CRP_PROVEDOR_TECNOLOGIA PROV, " 
																+ " INF_RELAC_PROVE_RASTR_DISPO RPRD," 
																+ "	INF_RELAC_VEICU_DISPO_PLANO RVDP," 
																+ "	PLANO_VIAGEM PV," 
																+ "	TIPO_OPERACAO TP" 
																+ "	WHERE PROV.ctl_prove_ten = RPRD.ctl_prove_ten" 
																+ "	AND RPRD.ctl_dispo_rst = RVDP.ctl_dispo_rst" 
																+ "	AND RPRD.tip_rastr = 1"
																+ "	AND PV.ctl_plvia = RVDP.ctl_plvia" 
																+ "	AND PV.ctl_opera_tip = TP.ctl_opera_tip" 
																+ "	AND PV.sit_plvia IN (0,1)";
	
	private static String SQL_GET_ALL_OPERACAO_VIAGEM_ATIVA_BY_TECNOLOGIA = " AND PROV.ctl_prove_ten = ? "; 
	
	private static String SQL_GET_OPERACAO_GROUP_BY =  "	GROUP BY PROV.ctl_prove_ten, PROV.nom_fants,PV.ctl_opera_tip, TP.des_opera_tip";

	private static String SQL_GET_OPERACAO_ORDER_BY = " ORDER BY total_viagem DESC";
	
	@Autowired
	OracleDataSourceConfig oracleDataSource;

	
	@PostConstruct
	private void initialize() {
		setDataSource(oracleDataSource.oracleJndiDataSource());
	}

	
	
	public List<MonitorOperacaoModel> getOperacaoViagemAtivaByID(int ctlProveTen) {
		List<MonitorOperacaoModel> lstOperacoes = new ArrayList<MonitorOperacaoModel>();
		try{
			LOGGER.debug("Executando metodo getOperacaoViagemAtivaByTecnologia " + ctlProveTen +"...");
			LOGGER.debug("SQL ->" + SQL_GET_ALL_OPERACAO_VIAGEM_ATIVA + SQL_GET_ALL_OPERACAO_VIAGEM_ATIVA_BY_TECNOLOGIA + SQL_GET_OPERACAO_GROUP_BY + SQL_GET_OPERACAO_ORDER_BY);
			lstOperacoes = getJdbcTemplate().query( SQL_GET_ALL_OPERACAO_VIAGEM_ATIVA + SQL_GET_ALL_OPERACAO_VIAGEM_ATIVA_BY_TECNOLOGIA + SQL_GET_OPERACAO_GROUP_BY + SQL_GET_OPERACAO_ORDER_BY,new Object[] {ctlProveTen}, new MonitorOperacaoMapper());
			
		}catch(Exception e){
			LOGGER.error("Erro de execu��o no metodo getOperacaoViagemAtivaByTecnologia -> " + ctlProveTen); 
			LOGGER.error(SQL_GET_ALL_OPERACAO_VIAGEM_ATIVA + SQL_GET_ALL_OPERACAO_VIAGEM_ATIVA_BY_TECNOLOGIA + SQL_GET_OPERACAO_GROUP_BY + SQL_GET_OPERACAO_ORDER_BY);
			LOGGER.error("Causa do Erro ->"+ e);
			e.printStackTrace();
			
		}
		return lstOperacoes;
	}

	public List<MonitorOperacaoModel> getAllOperacaoViagemAtiva() {
		List<MonitorOperacaoModel> lstOperacoes = new ArrayList<MonitorOperacaoModel>();

		try{
			LOGGER.debug("Executando metodo getAllOperacaoViagemAtiva...");
			LOGGER.debug("SQL ->" + SQL_GET_ALL_OPERACAO_VIAGEM_ATIVA + SQL_GET_OPERACAO_GROUP_BY + SQL_GET_OPERACAO_ORDER_BY);
			lstOperacoes = getJdbcTemplate().query( SQL_GET_ALL_OPERACAO_VIAGEM_ATIVA + SQL_GET_OPERACAO_GROUP_BY + SQL_GET_OPERACAO_ORDER_BY, new MonitorOperacaoMapper());
			
		}catch(Exception e){
			LOGGER.error("Erro de execu��o no metodo getAllOperacaoViagemAtiva!"); 
			LOGGER.error(SQL_GET_ALL_OPERACAO_VIAGEM_ATIVA + SQL_GET_OPERACAO_GROUP_BY + SQL_GET_OPERACAO_ORDER_BY);
			LOGGER.error("Causa do Erro ->"+ e);
			e.printStackTrace();
			
		}
		return lstOperacoes;
	}

}