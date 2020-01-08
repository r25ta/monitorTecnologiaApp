package br.com.pamcary.ir.monitortecnologia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newrelic.api.agent.Trace;

import br.com.pamcary.ir.monitortecnologia.service.MonitorTecnologiaService;
import br.com.pamcary.ir.monitortecnologia.vo.MonitorTecnologiaVO;

@RestController
@RequestMapping("/api")
public class MonitorTecnologiaRestController {

	
	@Autowired
	private MonitorTecnologiaService monitorTecnologiaService;
	
	@Trace
	@RequestMapping(value = "/tecnologias/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllTecnologia(){

		MonitorTecnologiaVO oMonitorTecnologiaVO = null;
		oMonitorTecnologiaVO = monitorTecnologiaService.getMonitorTecnologiaService(); 
        if (oMonitorTecnologiaVO == null) {
        	return new ResponseEntity("Informação não encontrada!", HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<MonitorTecnologiaVO>(oMonitorTecnologiaVO, HttpStatus.OK);		
	}
	
	
	
	@RequestMapping(value = "/tecnologia/{tecnologia}", method = RequestMethod.GET)
	public ResponseEntity<?> getTecnologiaByName( @PathVariable("tecnologia") String tecnologia ){
		
		MonitorTecnologiaVO oMonitorTecnologiaVO = null;
		
		if(tecnologia==null && tecnologia.isEmpty()) {
			return new ResponseEntity("Tecnologia não informada!", HttpStatus.NOT_FOUND);
			
		}else {
			monitorTecnologiaService.setNomeTecnologia(tecnologia.toUpperCase());
			
			oMonitorTecnologiaVO = monitorTecnologiaService.getMonitorTecnologiaService(); 
			
			if(oMonitorTecnologiaVO==null) {
	        	return new ResponseEntity("Tecnologia " + tecnologia + " não encontrada!", HttpStatus.NOT_FOUND);
	        	
			}
		    return new ResponseEntity<MonitorTecnologiaVO>(oMonitorTecnologiaVO, HttpStatus.OK);		
			
		}
		
	}
}
