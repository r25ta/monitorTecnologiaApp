package br.com.pamcary.ir.monitortecnologia.helper;

import com.newrelic.api.agent.Trace;

public class MonitorTecnologiaTransformer {
	

	private static MonitorTecnologiaTransformer instance = null;
	
	protected MonitorTecnologiaTransformer(){
		
	}
	
	public static MonitorTecnologiaTransformer getInstance(){
		if(instance==null){
			instance = new MonitorTecnologiaTransformer();
			
		}		
		return instance;
	}
	
	/*
	public String converArrayListToJson(List<MonitorTecnologiaModel> listMonitorTecnologias){
		
		String strJson = null;
		
		if((listMonitorTecnologias!=null) && (listMonitorTecnologias.size()>0)){
			Gson oGson = new Gson();
			strJson = oGson.toJson(listMonitorTecnologias);
		}
		
		return strJson;
	}
	*/
	
	@Trace
	public String transformarMinutosEmHorasMinutos(int horas){
				
		int transfHHH = 0;
		int transfMM = 0;
		String transfHHHMM = null;

		transfHHH = Math.abs(horas/60);
		transfMM = Math.abs(horas%60);

		if(horas<0){
			transfHHHMM = String.format("-%02d:%02d", transfHHH, transfMM);
			
		}else{			
			transfHHHMM = String.format("%02d:%02d", transfHHH, transfMM);
		}
		
		return transfHHHMM;
	}
}
