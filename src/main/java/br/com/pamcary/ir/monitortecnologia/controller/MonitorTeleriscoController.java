package br.com.pamcary.ir.monitortecnologia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.newrelic.api.agent.Trace;

import br.com.pamcary.ir.monitortecnologia.helper.Util;
import br.com.pamcary.ir.monitortecnologia.service.MonitorTeleriscoService;
import br.com.pamcary.ir.monitortecnologia.vo.MonitorTeleriscoVO;

@Controller	
public class MonitorTeleriscoController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(MonitorTeleriscoController.class);
	private long initProcess =0;
	private long endProcess=0;
	
	
	@Autowired
	private MonitorTeleriscoService oMonitorTeleriscoService;
	@Trace
	@GetMapping("/telerisco")
	public ModelAndView monitoraTelerisco() {
		initProcess = System.currentTimeMillis();
		LOGGER.info("Iniciou execução do controller monitoraTelerisco...");
		
		ModelAndView modelAndView = new ModelAndView();
		try {
			
			MonitorTeleriscoVO retornoTelerisco = new MonitorTeleriscoVO();
			
			retornoTelerisco = oMonitorTeleriscoService.getStatusTelerisco();
			
			if(retornoTelerisco!=null) {
				LOGGER.info("Retorno Telerisco " + retornoTelerisco.toString());		

				modelAndView.addObject("monitorTelerisco", retornoTelerisco);
				modelAndView.setViewName("/monitortelerisco");
				
				
				endProcess = Util.getInstance().calcularTempoExecucaoEmSegundos(initProcess, System.currentTimeMillis());
				LOGGER.info("Finalizou execução do controller monitoraTelerisco -> " + endProcess + " segundos!" );

				return modelAndView;
				
			}else {
				LOGGER.info("Erro ao carregar a página!");
				modelAndView.setViewName("monitortercnologiaerro");
				return modelAndView;

			}
			
		}catch(Exception e) {
			LOGGER.error("Erro de execução no metodo monitoraTelerisco!"); 
			LOGGER.error("Causa do Erro ->"+ e);
			e.printStackTrace();
			modelAndView.setViewName("monitortercnologiaerro");
			return modelAndView;
			
		}
		
		
				 
	}
}
