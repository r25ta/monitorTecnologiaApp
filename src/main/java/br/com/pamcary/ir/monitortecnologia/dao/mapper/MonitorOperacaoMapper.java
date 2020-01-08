package br.com.pamcary.ir.monitortecnologia.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorOperacaoModel;

public class MonitorOperacaoMapper implements RowMapper<MonitorOperacaoModel>{

	public MonitorOperacaoModel mapRow(ResultSet rs, int rowNum) throws SQLException{

		MonitorOperacaoModel oMonitorOperacaoModel = new MonitorOperacaoModel();
		
		oMonitorOperacaoModel.setCtlProveTen(rs.getInt("ctl_prove_ten"));
		
		oMonitorOperacaoModel.setCtlProveTen(rs.getInt("ctl_prove_ten"));
		oMonitorOperacaoModel.setDesProveTen(rs.getString("nom_fants"));
		oMonitorOperacaoModel.setCtlOperaTip(rs.getInt("ctl_opera_tip"));
		oMonitorOperacaoModel.setDesOperaTip(rs.getString("des_opera_tip"));
		oMonitorOperacaoModel.setTotalViagem(rs.getInt("total_viagem"));
		
		return oMonitorOperacaoModel;
	}
	
	

}
