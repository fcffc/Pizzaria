package br.com.fcffc.pizzaria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.fcffc.pizzaria.modelo.servicos.ServicoPizza;

@Controller
@RequestMapping("/pizzarias")
public class PizzariaController {
	
	@Autowired private ServicoPizza servicoPizza;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("nomesPizzas", servicoPizza.listarNomesPizzasDisponiveis());
			
		return "cliente/busca_pizzarias";
	}

}
