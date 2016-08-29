package br.com.fcffc.pizzaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fcffc.pizzaria.excecoes.IngredienteInvalidoException;
import br.com.fcffc.pizzaria.model.repositorios.IngredienteRepositorio;
import br.com.fcffc.pizzaria.model.repositorios.PizzaRepositorio;
import br.com.fcffc.pizzaria.modelo.entidades.Ingrediente;
import br.com.fcffc.pizzaria.modelo.entidades.Pizza;
import br.com.fcffc.pizzaria.modelo.enumeracaoes.CategoriaDeIngrediente;
import br.com.fcffc.pizzaria.modelo.enumeracaoes.CategoriaDePizza;
import br.com.fcffc.pizzaria.propertyeditors.IngredientePropertyEditor;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	@Autowired private IngredientePropertyEditor ingredientePropertyEditor;
	
	@Autowired private PizzaRepositorio pizzaRepositorio;
	@Autowired private IngredienteRepositorio ingredienteRepositorio;
	
	@RequestMapping(method=RequestMethod.GET)
	public String listarPizzas(Model model){
		model.addAttribute("pizzas", pizzaRepositorio.findAll());
		model.addAttribute("categorias", CategoriaDePizza.values());
		model.addAttribute("ingredientes", ingredienteRepositorio.findAll());
		return "pizza/listagem";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String salvarPizza(Model model, @Valid @ModelAttribute 
			Pizza pizza, BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {
			// Executa uma exceção (mensagem) na tela do usuário p/ leitura
			throw new IngredienteInvalidoException();

		} else {
			pizzaRepositorio.save(pizza);			

		}
		model.addAttribute("pizzas", pizzaRepositorio.findAll());
		return "pizza/tabela-pizzas";
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{pizzaId}")
	public ResponseEntity<String> deletarPizza(@PathVariable Long pizzaId){
		try{
			pizzaRepositorio.delete(pizzaId);
			return new ResponseEntity<String>(HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{pizzaId}")
	public ResponseEntity<Pizza> buscarPizza(@PathVariable Long pizzaId){
		Pizza pizza = pizzaRepositorio.findOne(pizzaId);
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@RequestMapping("/quantas")
	@ResponseBody
	public String quantasPizzas(){
		return "Atualmete há "+pizzaRepositorio.count()+" cadastradas.";
	}
	
	// Objetos de Ingredientes e não ID's
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.registerCustomEditor(Ingrediente.class, ingredientePropertyEditor);
	}
}
