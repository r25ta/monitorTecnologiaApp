package br.com.pamcary.ir.monitortecnologia.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.newrelic.api.agent.Trace;

import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorSubServicoSistemaModel;

public class MonitorSubSistemaMapper implements RowMapper<MonitorSubServicoSistemaModel>{

		@Trace
		public MonitorSubServicoSistemaModel mapRow(ResultSet rs, int rowNum) throws SQLException{

			MonitorSubServicoSistemaModel oMonitor = new MonitorSubServicoSistemaModel();
			
			oMonitor.setCtlServiSub(rs.getLong("ctl_servi_sub"));
			oMonitor.setDesServiSub(rs.getString("des_servi_Sub"));			
			oMonitor.setDhrUltimPro(rs.getString("dhr_ultim_pro"));
			oMonitor.setDhrToler(rs.getString("dhr_toler"));
			
			
			return oMonitor;
			
		}
}
