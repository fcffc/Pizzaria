package br.com.fcffc.pizzaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fcffc.pizzaria.excecoes.IngredienteInvalidoException;
import br.com.fcffc.pizzaria.modelo.entidades.Ingrediente;
import br.com.fcffc.pizzaria.modelo.enumeracaoes.CategoriaDeIngrediente;
import br.com.fcffc.pizzaria.modelo.servicos.ServicoIngrediente;

// /app/ingredientes (método GET) -> listarIngredientes
// /app/ingredientes (método POST) -> salvarIngredientes
@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {

	// Pega lista de atributos do banco de dados

	@Autowired
	private ServicoIngrediente servicoIngrediente;

	// /WEB-INF/ingredientes/listagem.jsp
	@RequestMapping(method = RequestMethod.GET)
	public String listarIngredientes(Model model) {
		Iterable<Ingrediente> ingredientes = servicoIngrediente.listar();

		model.addAttribute("titulo", "Listagem de Ingredientes");
		// Add um atributo de ingredientes q contem uma lista de ingredientes
		model.addAttribute("ingredientes", ingredientes);
		model.addAttribute("categorias", CategoriaDeIngrediente.values());
		return "ingrediente/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	// Mapeia os atributos de um form para um objeto
	public String salvarIngrediente(@Valid @ModelAttribute Ingrediente ingrediente, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			// Executa uma exceção (mensagem) na tela do usuário p/ leitura
			throw new IngredienteInvalidoException();

		} else {
			servicoIngrediente.salvar(ingrediente);
		}

		model.addAttribute("ingredientes", servicoIngrediente.listar());
		model.addAttribute("categorias", CategoriaDeIngrediente.values());
		return "ingrediente/tabela-ingredientes";
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> deletarIngrediente(@PathVariable Long id) {
		try {
			servicoIngrediente.remover(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody // Retorna Json
	public Ingrediente buscarIngrediente(@PathVariable Long id) {
		Ingrediente ingrediente = servicoIngrediente.buscar(id);
		return ingrediente;
	}
}
