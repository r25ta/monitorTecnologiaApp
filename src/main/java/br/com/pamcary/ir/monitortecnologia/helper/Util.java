package br.com.pamcary.ir.monitortecnologia.helper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Util {


	private static Util instance = null;

	protected Util() {
		
	}
	
	public static Util getInstance(){
		if(instance==null){
			instance = new Util();
		}
		return instance;
		
	}
	
	
	
	public long calcularTempoExecucaoEmSegundos(long tempoIncial, long tempoFinal){
		
		if(tempoFinal>tempoIncial){
			return Math.round(tempoFinal-tempoIncial)/1000;
			
		}		
		return 0;
	}
	
	public String fomatarDataHoraPadraoInfolog(String dataForaDoPadrao) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime dataFormatada = LocalDateTime.parse(dataForaDoPadrao); 
		
		return dataFormatada.format(format);
		
	}

	public String fomatarHoraPadraoInfolog(String horaForaDoPadrao) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime horaFormatada = LocalTime.parse(horaForaDoPadrao); 
	
		return horaFormatada.format(format);		
		
	}
	
	
	public String adicionarToleranciaNaData(String data, String tolerancia ) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime dataFormatada = null;
		
		if( (data!=null) && (!data.trim().equals(""))){
			dataFormatada = LocalDateTime.parse(data,format);
			
		}else {
			return null;
			
		}

		if((tolerancia==null) ||(tolerancia.trim().equals(""))){
			tolerancia="00:00:00";
			
		}
		
		LocalTime horaFormatada = LocalTime.parse(tolerancia);
		
		return dataFormatada.plusHours(horaFormatada.getHour()).plusMinutes(horaFormatada.getMinute()).plusSeconds(horaFormatada.getSecond()).format(format);
	}
	
	
	public boolean comparaDataUltProcETolerComDataSistema(String dataProcComToler,String dataServidor) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime data1, data2;
		
		data1 = LocalDateTime.parse(dataProcComToler,format);
		data2 = LocalDateTime.parse(dataServidor,format);
		
		
		return data1.isAfter(data2);
	}
	
	public static void main(String args[]) {
		String data = "04/01/2019 14:00:01";
		String tolerancia = "00:15:01";
		
		String dataPlusTolerancia = Util.getInstance().adicionarToleranciaNaData(data, tolerancia);
		
		System.out.println("\n data entrada " + data);
		System.out.println("\n tolerancia   " + tolerancia);
		System.out.println("\n data + tolerancia  " + dataPlusTolerancia);
		System.out.println("\n Atualização + Tolerancia > Servidor " + Util.getInstance().comparaDataUltProcETolerComDataSistema(dataPlusTolerancia, "04/01/2019 18:16:01"));
		
		
		
	}
	
}