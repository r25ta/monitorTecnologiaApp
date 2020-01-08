package br.com.pamcary.ir.monitortecnologia.helper;


import java.util.Arrays;
public class Reminder {
    public static void main(String[] args) {
    	  int[] array = {23,43,56,97,32};
    	  Arrays.stream(array).reduce((x,y) -> x+y).ifPresent(s -> System.out.println(s));
    	  Arrays.stream(array).reduce(Integer::sum).ifPresent(s -> System.out.println(s));
    	  Arrays.stream(array).reduce(StatisticsUtility::addIntData).ifPresent(s -> System.out.println(s));
    }
    
    
    
  
}  