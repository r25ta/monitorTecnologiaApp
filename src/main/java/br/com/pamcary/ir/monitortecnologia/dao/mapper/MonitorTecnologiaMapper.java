package br.com.pamcary.ir.monitortecnologia.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.newrelic.api.agent.Trace;

import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorTecnologiaModel;
import br.com.pamcary.ir.monitortecnologia.helper.MonitorTecnologiaTransformer;

public class MonitorTecnologiaMapper implements RowMapper<MonitorTecnologiaModel> {

	
	@Trace
	public MonitorTecnologiaModel mapRow(ResultSet rs, int rowNum) throws SQLException{

		MonitorTecnologiaModel oMonitor = new MonitorTecnologiaModel();
		
		oMonitor.setProvedor(rs.getString("provedor"));
		oMonitor.setCtlProveTen(rs.getInt("ctl_prove_ten"));
		oMonitor.setDhrProcPamcary(rs.getTimestamp("pp"));			
		oMonitor.setDhrProcForcedor(rs.getTimestamp("po"));
		oMonitor.setDhrEvento(rs.getTimestamp("evento"));
		oMonitor.setDiff(rs.getInt("diff"));
		oMonitor.setDiffFormatado(MonitorTecnologiaTransformer.getInstance().transformarMinutosEmHorasMinutos(oMonitor.getDiff()));
		oMonitor.setQtdeLimpezaProcessar(rs.getInt("limpeza_processar"));
		oMonitor.setQtdeLimpezaProcessando(rs.getInt("limpeza_processando"));
		oMonitor.setQtdeAProcessar(rs.getInt("a_processar"));
		oMonitor.setQtdeProcessando(rs.getInt("processando"));
		oMonitor.setTotal(rs.getInt("total_tecnologia"));
		oMonitor.setQtdeVeiculo(rs.getInt("veiculos"));
		oMonitor.setQtdeDedicado(rs.getInt("dedicados"));
		
		return oMonitor;
	}
	
}

