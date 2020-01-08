package br.com.pamcary.ir.monitortecnologia.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.pamcary.ir.monitortecnologia.dao.MonitorLoginDAO;
import br.com.pamcary.ir.monitortecnologia.dao.model.MonitorLoginModel;
import br.com.pamcary.ir.monitortecnologia.helper.Util;

@Controller
public class MonitorLoginController {
	private static Logger LOGGER = LoggerFactory.getLogger(MonitorLoginController.class);
	private long initProcess =0;
	private long endProcess=0;
	
	@Autowired
	private MonitorLoginDAO oMonitorLoginDAO;
	
	@GetMapping("/login")
	public ModelAndView monitorLogin() {
		
		initProcess = System.currentTimeMillis();
		LOGGER.info("Iniciou execução do controller monitorLogin...");
		
		ModelAndView modelAndView = new ModelAndView();

		try {
		
			String usuario = "OPERADOR";
			String senha = "DINEP";
			
			List<MonitorLoginModel> lstLogin = oMonitorLoginDAO.getLoginSenha(usuario, senha);
				
			if(lstLogin.size() > 0) {
				LOGGER.info("Usuário Valido " + usuario + senha );		

				modelAndView.addObject("monitorLogin", lstLogin);
				modelAndView.setViewName("/monitorlogin");
				
				
				endProcess = Util.getInstance().calcularTempoExecucaoEmSegundos(initProcess, System.currentTimeMillis());
				LOGGER.info("Finalizou execução do controller monitorLogin -> " + endProcess + " segundos!" );

				return modelAndView;
			}else {
				LOGGER.info("Erro ao carregar a página!");
				modelAndView.setViewName("monitorloginerro");
				return modelAndView;

			}

			
		}catch(Exception e) {
			LOGGER.error("Erro de execução no metodo monitorLogin!"); 
			LOGGER.error("Causa do Erro ->"+ e);
			e.printStackTrace();
			modelAndView.setViewName("monitorloginerro");
			return modelAndView;
	
		}
	}
}
		
		
		
