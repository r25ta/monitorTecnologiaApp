package br.com.pamcary.ir.monitortecnologia.helper;

import java.util.Date;
import java.util.TimerTask;

public class TaskToPerform extends TimerTask {
	
	
	
	
	@Override
	 public void run() {
	  // business logic according to your application and needs
	  System.out.println(new Date() + " INICIOU...");
	  long x = new Date().getTime();
	  	  
	  for (int i = 0; i < 1000000; i++) {
		  System.out.println("[ "+ x +" ] processando " + i);  
		  
		  
	  }
	  
	  System.out.println(new Date() + " FINALIZOU...");
	 }
}
