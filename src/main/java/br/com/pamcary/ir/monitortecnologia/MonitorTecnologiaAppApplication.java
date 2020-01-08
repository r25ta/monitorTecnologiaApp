package br.com.pamcary.ir.monitortecnologia;

import org.springframework.boot.SpringApplication;
import com.newrelic.api.agent.Trace;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:application.properties")
public class MonitorTecnologiaAppApplication  extends SpringBootServletInitializer {

	/**
    * Usado quando for JAR
    */
    
	public static void main(String[] args) {
		SpringApplication.run(MonitorTecnologiaAppApplication.class, args);
	}
	

   /**
    * Usado quando for WAR
    */
   @Override
   @Trace
   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
       return builder.sources(MonitorTecnologiaAppApplication.class);
   }	
	
}
