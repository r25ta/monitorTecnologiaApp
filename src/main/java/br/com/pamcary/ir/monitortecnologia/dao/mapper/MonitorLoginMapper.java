package br.com.pamcary.ir.monitortecnologia.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorLoginModel;


public class MonitorLoginMapper implements RowMapper<MonitorLoginModel> {

	@Override
	public MonitorLoginModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		MonitorLoginModel oMonitorLoginModel = new MonitorLoginModel();
		
		oMonitorLoginModel.setCtlUser(rs.getInt("ctl_user"));
		oMonitorLoginModel.setNomUser(rs.getString("nom_user"));
		oMonitorLoginModel.setCodSenha(rs.getString("cod_senha"));
		oMonitorLoginModel.setNumNivac(rs.getInt("num_nivac"));
		
		return oMonitorLoginModel;
	}

}
