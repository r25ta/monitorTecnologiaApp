package br.com.pamcary.ir.monitortecnologia.helper;

import java.util.Timer;

public class CronJobExample {

	 public static void main(String[] args) {
	  Timer timer = new Timer();
	  TaskToPerform task = new TaskToPerform();
	  timer.scheduleAtFixedRate(task, 0, 1000); // time in miliseconds
	 }

}