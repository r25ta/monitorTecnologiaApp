package br.com.pamcary.ir.monitortecnologia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.pamcary.ir.monitortecnologia.dao.MonitorLoginDAO;
import br.com.pamcary.ir.monitortecnologia.dao.config.OracleDataSourceConfig;
import br.com.pamcary.ir.monitortecnologia.dao.mapper.MonitorLoginMapper;
import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorLoginModel;

@Repository
public class MonitorLoginDAOImpl extends JdbcDaoSupport implements MonitorLoginDAO {

	private static Logger LOGGER = LoggerFactory.getLogger(MonitorLoginDAOImpl.class);
	
	private final static String SQL_VALIDAR_USUARIO = "SELECT ctl_user, nom_user, cod_senha, num_nivac FROM OPERADOR WHERE nom_user = ? AND cod_senha = ?";

	
	@Autowired
	OracleDataSourceConfig oracleDataSource;

	
	@PostConstruct
	private void initialize() {
		setDataSource(oracleDataSource.oracleJndiDataSource());
	}

	@Override
	public List<MonitorLoginModel> getLoginSenha(String usuario, String senha) {
		LOGGER.debug("Executando metodo getLoginSenha " + usuario +"...");

		List<MonitorLoginModel> lstLogin = new ArrayList<>();
		
		try {
			LOGGER.debug("SQL ->" + SQL_VALIDAR_USUARIO);
		    lstLogin = getJdbcTemplate().query(SQL_VALIDAR_USUARIO, new Object[] {usuario, senha}, new MonitorLoginMapper());

			
		}catch(Exception e) {
			LOGGER.error("\n Erro ao validar usuário e senha! " + SQL_VALIDAR_USUARIO);
			LOGGER.error("\n Causa do Erro ->"+ e);
			e.printStackTrace();
		}
		
		return lstLogin;
	}


}
