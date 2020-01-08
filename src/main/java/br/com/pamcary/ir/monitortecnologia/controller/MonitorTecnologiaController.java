package br.com.pamcary.ir.monitortecnologia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newrelic.api.agent.Trace;

import br.com.pamcary.ir.monitortecnologia.helper.Util;
import br.com.pamcary.ir.monitortecnologia.service.MonitorTecnologiaService;
import br.com.pamcary.ir.monitortecnologia.vo.MonitorTecnologiaVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Controller
@RequestMapping("/")
public class MonitorTecnologiaController {
	
	private long initProcess =0;
	private long endProcess=0;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitorTecnologiaController.class);
	
	
	@Autowired
	private MonitorTecnologiaService monitorTecnologiaService;
	@Trace //NEWRELIC
	@SuppressWarnings("finally")
	@RequestMapping(value="/", params="tecnologia", method = RequestMethod.GET)
	public @ResponseBody ModelAndView getTecnologia(@RequestParam String tecnologia, ModelMap model) {
		ModelAndView oModelAndView = new ModelAndView();
		try{
			initProcess = System.currentTimeMillis();
			LOGGER.info("Iniciou execução do controller monitorTecnologiaByName " + tecnologia +"...");
			
			monitorTecnologiaService.setNomeTecnologia(tecnologia);
			MonitorTecnologiaVO oMonitorTecnologiaVO = monitorTecnologiaService.getMonitorTecnologiaService(); 
			
			if(oMonitorTecnologiaVO!=null) {
				oModelAndView.addObject("monitor", oMonitorTecnologiaVO);
				oModelAndView.addObject("tecnologia",monitorTecnologiaService.getNomeTecnologia());
				oModelAndView.setViewName("monitortecnologia");

				endProcess = Util.getInstance().calcularTempoExecucaoEmSegundos(initProcess, System.currentTimeMillis());
				LOGGER.info("Finalizou execução do controller monitorTecnologiaByName -> " + endProcess + " segundos!" );
				
			}else{
				LOGGER.info("Erro ao carregar a página!");
				oModelAndView.setViewName("monitortercnologiaerro");
				
			}

		}catch(Exception e){
			LOGGER.error("Erro de execução no metodo monitorTecnologiaByName -> " +tecnologia); 
			LOGGER.error("Causa do Erro ->"+ e);
			e.printStackTrace();
			oModelAndView.setViewName("monitortercnologiaerro");

			
		} finally {
			return oModelAndView;
			
		}
		
	}
	
	  @RequestMapping("/hi")
	  public @ResponseBody String hiThere(){
		  return "Teste de Conectividade!";
	  }	
	
}