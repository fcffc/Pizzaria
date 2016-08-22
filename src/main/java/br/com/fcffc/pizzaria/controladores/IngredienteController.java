package br.com.fcffc.pizzaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fcffc.pizzaria.model.repositorios.IngredienteRepositorio;
import br.com.fcffc.pizzaria.modelo.entidades.Ingrediente;
import br.com.fcffc.pizzaria.modelo.enumeracaoes.CategoriaDeIngrediente;

// /app/ingredientes (método GET) -> listarIngredientes
// /app/ingredientes (método POST) -> salvarIngredientes
@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {

	// Pega lista de atributos do banco de dados
	@Autowired
	private IngredienteRepositorio ingredienteRepositorio;

	// /WEB-INF/ingredientes/listagem.jsp
	@RequestMapping(method = RequestMethod.GET)
	public String listarIngredientes(Model model) {
		Iterable<Ingrediente> ingredientes = ingredienteRepositorio.findAll();

		model.addAttribute("titulo", "Listagem de Ingredientes");
		// Add um atributo de ingredientes q contem uma lista de ingredientes
		model.addAttribute("ingredientes", ingredientes);
		model.addAttribute("categorias", CategoriaDeIngrediente.values());
		return "ingrediente/listagem";
	}

	@RequestMapping(method = RequestMethod.POST)
	// Mapeia os atributos de um form para um objeto
	public String salvarIngrediente(@Valid @ModelAttribute Ingrediente ingrediente, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			// Adiciona um objeto (mensagem) na tela do usuário p/ leitura
			FieldError error = bindingResult.getFieldErrors().get(0);
			redirectAttributes.addFlashAttribute("mensagemErro",
					"Não foi possível salvar o ingrediente." + error.getField() + " " + error.getDefaultMessage());
		} else {
			ingredienteRepositorio.save(ingrediente);
			redirectAttributes.addFlashAttribute("mensagemInfo", "Ingrediente salvo com sucesso.");
		}
		// Redireciona para o método @RequestMapping(method = RequestMethod.GET)
		return "redirect:/app/ingredientes";
	}

}
