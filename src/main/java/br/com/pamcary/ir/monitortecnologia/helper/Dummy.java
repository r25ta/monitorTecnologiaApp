package br.com.pamcary.ir.monitortecnologia.helper;



import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Dummy {
	Connection getConnectionOracle() {
		Connection connection;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = null;
			connection = DriverManager.getConnection("jdbc:oracle:thin:@gemini.pamcary-interno.com.br:1521:orcl","aplmonitora_prd", "Pamcary250");
			if (connection != null) {
				System.out.println("Conectou!");
			} else {
				System.out.println("Não conectou!");
			} 
		} catch (Exception e) {
			return null;
		}
		return connection;
	}
	
	private static final String EMPTY_STRING = "";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Dummy d = new Dummy();
		
		Connection conn = d.getConnectionOracle();
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		long codDispoRstAnterior = 0;
		String dhrEventAnterior = null;
		long temp1 = 0; 
		long temp2 = 0;
		long temp3 =0;
		
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT ctl_event, cod_dispo_rst, TO_CHAR(dhr_event,'DD/MM/YYYY HH24:MI:SS') as dhr_event, NVL(num_faixa_tem,99999) as num_faixa_tem, NVL(num_faixa_seg,99999) as num_faixa_seg, NVL(num_faixa_ter,99999) as num_faixa_ter ");
			sql.append("					FROM EVENTO_GERENCIADORA_RISCO ");
			sql.append("						WHERE idt_tipo_eve = 'P' ");
			sql.append("							and cod_docum_pvd = '05520402000211'");
			sql.append("							and idt_regis = 2");
			sql.append("							and ( num_faixa_tem is not null ");
			sql.append("									or num_faixa_seg is not null");
			sql.append("									or num_faixa_ter is not null )");
			sql.append("					ORDER BY cod_dispo_rst, dhr_event ");
			
			System.out.println("SQL --> " + sql.toString());
			
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while (rs.next()) {

				if ( (temp1 == rs.getLong("num_faixa_tem") && temp1!=99999 )  || 
					 (temp2 == rs.getLong("num_faixa_seg") && temp2!=99999) || 
					 (temp3 == rs.getLong("num_faixa_ter") && temp3!=99999) ) {
					
					if((dhrEventAnterior!=null) && d.calcularDiferenca(rs.getString("dhr_event"), dhrEventAnterior) < 10 ){
						System.out.println("\n DELETAR " + rs.getLong("ctl_event") + " - " + codDispoRstAnterior + " - " + rs.getString("dhr_event"));
					}
				
					
				}

				if(codDispoRstAnterior==0 || d.calcularDiferenca(rs.getString("dhr_event"), dhrEventAnterior) >= 10 ) {
					if(codDispoRstAnterior!= rs.getLong("cod_dispo_rst") ) {
						codDispoRstAnterior = rs.getLong("cod_dispo_rst");
						dhrEventAnterior =  rs.getString("dhr_event");
						temp1 = rs.getLong("num_faixa_tem");
						temp2 = rs.getLong("num_faixa_seg");
						temp3 = rs.getLong("num_faixa_ter");
						
					}
					
				}

				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
	public void b() {
		// System.out.println(MessageFormat.format(UPDATE_ENDERECO_VINCULADO,
		// new Object[]{"Eduardo", "Rodrigues", "de", "Oliveira"}));

		System.out.println(cleanLeftZeros("0000009L"));
		System.out.println(cleanLeftZeros("8009G"));
		System.out.println(cleanLeftZeros("8000EDU"));
		System.out.println(cleanLeftZeros("080EDS090"));
		System.out.println(cleanLeftZeros("00T00080EDS090"));
		System.out.println(cleanLeftZeros("1"));
		System.out.println(cleanLeftZeros("0"));
		System.out.println(cleanLeftZeros("0-"));
		System.out.println(cleanLeftZeros(null));
	}

	private static final byte B_ZERO = "0".getBytes()[0];
	public static String cleanLeftZeros(String strToClean) {
		if (strToClean == null) {
			return EMPTY_STRING;
		}
		byte[] sToClean = strToClean.getBytes();
		byte[] sCleaned = null;

		for (int x = 0; x < strToClean.length(); x++) {
			if (sToClean[x] != B_ZERO) {
				int length = sToClean.length - x;
				sCleaned = new byte[length];
				System.arraycopy(sToClean, x, sCleaned, 0, length);
				break;
			}
		}
		return sCleaned == null ? EMPTY_STRING : new String(sCleaned);
	}

	
	private long calcularDiferenca(String data1, String data2) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime dataA = LocalDateTime.parse(data1,formatter);
		LocalDateTime dataB = LocalDateTime.parse(data2,formatter);
		
		Duration tempo = Duration.between(dataA, dataB);
		
		return tempo.toMinutes();
	}
	
	
	
}
