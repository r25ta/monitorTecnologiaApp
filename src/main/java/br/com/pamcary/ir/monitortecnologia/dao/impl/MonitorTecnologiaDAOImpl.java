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

import br.com.pamcary.ir.monitortecnologia.dao.MonitorTecnologiaDAO;
import br.com.pamcary.ir.monitortecnologia.dao.config.OracleDataSourceConfig;
import br.com.pamcary.ir.monitortecnologia.dao.mapper.MonitorTecnologiaMapper;
import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorTecnologiaModel;

@Repository
public class MonitorTecnologiaDAOImpl extends JdbcDaoSupport implements MonitorTecnologiaDAO {
	
	private static Logger LOGGER = LoggerFactory.getLogger(MonitorTecnologiaDAOImpl.class);
	/**
	 * Query responsavel por retornar todas as informa��es sobre o processamento de das tecnologias.
	 * N�o retorna tecnologias desatualizadas acima de 30 dias.
	 * ORDENADO PELO DELAY
	 */
	
	private static String SQL_GET_ALL_TECNOLOGIA = "    SELECT T.NOM_FANTS as PROVEDOR, T.CTL_PROVE_TEN as CTL_PROVE_TEN, "
												+ " (SELECT DISTINCT MTP.DHR_ALTER " 
												+ " FROM monitora_terminal MTP, "
												+ " tipo_evento_terminal TETP, "
												+ " V_CRP_PROVEDOR_TECNOLOGIA PTP "
												+ " WHERE TETP.tip_event_ter = MTP.tip_event_ter "
								                + " AND PTP.ctl_prove_teN = MTP.ctl_prove_ten "
								                + " AND PTP.ctl_prove_teN = T.CTL_PROVE_TEN "
								                + " AND MTP.DHR_ALTER IN (SELECT MAX(DHR_ALTER) FROM MONITORA_TERMINAL WHERE CTL_PROVE_TEN = T.CTL_PROVE_TEN AND DHR_ALTER <= SYSDATE)) AS PP, "
								                + " (SELECT DISTINCT MTO.DHR_INCLU_CGA "
								                + " FROM monitora_terminal MTO, "
									               + " tipo_evento_terminal TETO, "
									               + " V_CRP_PROVEDOR_TECNOLOGIA PTO "
									               + " WHERE TETO.tip_event_ter = MTO.tip_event_ter "
									               + " AND PTO.ctl_prove_teN = MTO.ctl_prove_ten "
									               + " AND PTO.ctl_prove_teN = T.CTL_PROVE_TEN "
									               + " AND MTO.DHR_INCLU_CGA IN (SELECT MAX(DHR_INCLU_CGA) FROM MONITORA_TERMINAL WHERE CTL_PROVE_TEN = T.CTL_PROVE_TEN AND DHR_INCLU_CGA <= SYSDATE)) AS PO, "
									          + " (SELECT DISTINCT MTE.DHR_EVENT "
									          + " FROM monitora_terminal MTE, "
									               + " tipo_evento_terminal TETE, "
									               + " V_CRP_PROVEDOR_TECNOLOGIA PTE "
									          + " WHERE TETE.tip_event_ter = MTE.tip_event_ter "
									                + " AND  MTE.DHR_EVENT IS NOT NULL "
									                + " AND PTE.ctl_prove_teN = MTE.ctl_prove_ten "
									                + " AND PTE.ctl_prove_teN = T.CTL_PROVE_TEN "
									                + " AND MTE.DHR_EVENT IN (SELECT MAX(DHR_EVENT) FROM MONITORA_TERMINAL WHERE CTL_PROVE_TEN = T.CTL_PROVE_TEN AND DHR_EVENT <= SYSDATE)) AS EVENTO, "

													+ " (SELECT DISTINCT TRUNC(((SYSDATE - MT.dhr_event) * 60 * 24),0) " + " 	FROM monitora_terminal MT, "
													+ " 	     tipo_evento_terminal TET, " + " 	     V_CRP_PROVEDOR_TECNOLOGIA PT "
													+ " WHERE TET.tip_event_ter = MT.tip_event_ter " + "   AND PT.ctl_prove_teN = MT.ctl_prove_teN "
													+ " AND PT.ctl_prove_teN = T.CTL_PROVE_TEN "
													+ " AND DHR_EVENT IN (SELECT MAX(DHR_EVENT) FROM MONITORA_TERMINAL WHERE CTL_PROVE_TEN = T.CTL_PROVE_TEN AND DHR_EVENT <= SYSDATE)) AS DIFF, "
									          
									          + " (SELECT COUNT(1) "
									          + " FROM evento_gerenciadora_risco E2 "
									          + " WHERE E2.COD_DOCUM_PVD = T.COD_DOCUM_PRI AND E2.IDT_REGIS = 0) AS LIMPEZA_PROCESSAR, "
									          + " (SELECT COUNT(1) "
									          + " FROM evento_gerenciadora_risco E3 "
									          + " WHERE E3.COD_DOCUM_PVD = T.COD_DOCUM_PRI AND E3.IDT_REGIS = 1) AS LIMPEZA_PROCESSANDO, "
									          + " (SELECT COUNT(1) "
									          + " FROM evento_gerenciadora_risco E2 "
									          + " WHERE E2.COD_DOCUM_PVD = T.COD_DOCUM_PRI AND E2.IDT_REGIS = 2) AS A_PROCESSAR, "
									          + " (SELECT COUNT(1) "
									          + " FROM evento_gerenciadora_risco E3 "
									          + " WHERE E3.COD_DOCUM_PVD = T.COD_DOCUM_PRI AND E3.IDT_REGIS = 3) AS PROCESSANDO, "
									          + " (SELECT COUNT(1)  FROM evento_gerenciadora_risco E3 "  
									          + " 					WHERE E3.COD_DOCUM_PVD = T.COD_DOCUM_PRI AND E3.IDT_REGIS = 0) "  
									          + " + (SELECT COUNT(1)  FROM evento_gerenciadora_risco E3 "  
									          + " 					WHERE E3.COD_DOCUM_PVD = T.COD_DOCUM_PRI AND E3.IDT_REGIS = 1) "  
									          + " + (SELECT COUNT(1)  FROM evento_gerenciadora_risco E2 "  
									          + " 					WHERE E2.COD_DOCUM_PVD = T.COD_DOCUM_PRI AND E2.IDT_REGIS = 2) " 
									          + " + (SELECT COUNT(1)  FROM evento_gerenciadora_risco E3 "  
									          + " 					WHERE E3.COD_DOCUM_PVD = T.COD_DOCUM_PRI AND E3.IDT_REGIS = 3 ) AS TOTAL_TECNOLOGIA, "
									          									          
									          + " (SELECT COUNT(1) "
									          + " FROM APL_INFOLOGBI.V_DISPO_ATIVO_KRONA DAK "
									          + " WHERE DAK.TECNOLOGIA = T.NOM_FANTS "
									          + " AND DAK.NUM_PLACA NOT IN (SELECT V.COD_PLACA "
									                                + " FROM V_CRP_VEICULO V, RELAC_VEICU_VINCD R "
									                                + " WHERE R.CTL_VEICU = V.CTL_VEICU)) AS VEICULOS, "
									          
									          + " (SELECT COUNT(1) "
									          + " FROM APL_INFOLOGBI.V_VEICU_DEDIC_KRONA DAK "
									          + " WHERE DAK.TECNOLOGIA = T.NOM_FANTS ) AS " 
									          + " DEDICADOS "
								 
								+ " FROM V_CRP_PROVEDOR_TECNOLOGIA T  "
								+ " WHERE T.CTL_PROVE_TEN IS NOT NULL "
								+ " AND T.CTL_PROVE_TEN NOT IN (21071) "
								+ " AND (SELECT DISTINCT TO_CHAR(MTE.DHR_EVENT,'DD/MM/YYYY HH24:MI') "
								          + " FROM monitora_terminal MTE, "
								               + " tipo_evento_terminal TETE, "
								               + " V_CRP_PROVEDOR_TECNOLOGIA PTE "
								          + " WHERE TETE.tip_event_ter = MTE.tip_event_ter "
								                + " AND  MTE.DHR_EVENT IS NOT NULL "
								                + " AND PTE.ctl_prove_teN = MTE.ctl_prove_teN "
								                + " AND PTE.ctl_prove_teN = T.CTL_PROVE_TEN "
								                + " AND MTE.DHR_EVENT IN (SELECT MAX(DHR_EVENT) FROM MONITORA_TERMINAL WHERE CTL_PROVE_TEN = T.CTL_PROVE_TEN AND DHR_EVENT <= SYSDATE)) IS NOT NULL "
								                + " AND "
								                + " (SELECT DISTINCT TRUNC(((SYSDATE - MT.dhr_event) * 60 * 24),0) " + " 	FROM monitora_terminal MT, "
													+ " 	     tipo_evento_terminal TET, " + " 	     V_CRP_PROVEDOR_TECNOLOGIA PT "
													+ " WHERE TET.tip_event_ter = MT.tip_event_ter " + "   AND PT.ctl_prove_teN = MT.ctl_prove_teN "
													+ " AND PT.ctl_prove_teN = T.CTL_PROVE_TEN "
													+ " AND DHR_EVENT IN (SELECT MAX(DHR_EVENT) FROM MONITORA_TERMINAL WHERE CTL_PROVE_TEN = T.CTL_PROVE_TEN AND DHR_EVENT <= SYSDATE)) < 43200";
													

	
	private static String SQL_GET_BY_ID_TECNOLOGIA = " AND T.NOM_FANTS LIKE ? ";	
	
	private static String SQL_ORDER_BY = " ORDER BY DIFF DESC, T.NOM_FANTS ASC";
	
	
	
	
	@Autowired
	private OracleDataSourceConfig oracleDataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(oracleDataSource.oracleJndiDataSource());
	}

	@Trace
	public List<MonitorTecnologiaModel> getAllTecnologia() {
		LOGGER.debug("Executando metodo getAllTecnologia...");
		List<MonitorTecnologiaModel> lstTecnologias = new ArrayList<MonitorTecnologiaModel>();
		
		try{
			LOGGER.debug("SQL-> " + SQL_GET_ALL_TECNOLOGIA + SQL_ORDER_BY);
			lstTecnologias = getJdbcTemplate().query(SQL_GET_ALL_TECNOLOGIA + SQL_ORDER_BY, new MonitorTecnologiaMapper());
			
			getJdbcTemplate().getDataSource().getConnection().close();		
			
			
		}catch(Exception e){
			LOGGER.error("Erro na execução!"); 
			LOGGER.error(SQL_GET_ALL_TECNOLOGIA + SQL_ORDER_BY);
			LOGGER.error("Causa do Erro ->"+ e);
			e.printStackTrace();
			
		}
		return lstTecnologias;
	}
	
	
	@Trace
	public List<MonitorTecnologiaModel> getByNomeTecnologia(String tecnologia){
		LOGGER.debug("Executando metodo getByNomeTecnologia " + tecnologia +"...");

		List<MonitorTecnologiaModel> lstTecnologias = new ArrayList<MonitorTecnologiaModel>();

		try{
			LOGGER.debug("SQL ->" + SQL_GET_ALL_TECNOLOGIA + SQL_GET_BY_ID_TECNOLOGIA + SQL_ORDER_BY);
			lstTecnologias = getJdbcTemplate().query(SQL_GET_ALL_TECNOLOGIA + SQL_GET_BY_ID_TECNOLOGIA + SQL_ORDER_BY,new Object[] {"%"+ tecnologia +"%" }, new MonitorTecnologiaMapper());

			getJdbcTemplate().getDataSource().getConnection().close();
			
		}catch(Exception e){
			LOGGER.error("Erro na execução!"); 
			LOGGER.error(SQL_GET_ALL_TECNOLOGIA + SQL_GET_BY_ID_TECNOLOGIA + SQL_ORDER_BY);
			LOGGER.error("Causa do Erro ->"+ e);
			e.printStackTrace();
			
		}
		
		return lstTecnologias;
	}
	
}