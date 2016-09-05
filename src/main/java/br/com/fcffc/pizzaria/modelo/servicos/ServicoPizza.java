package br.com.fcffc.pizzaria.modelo.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fcffc.pizzaria.model.repositorios.PizzaRepositorio;
import br.com.fcffc.pizzaria.modelo.entidades.Pizza;
import br.com.fcffc.pizzaria.modelo.entidades.Pizzaria;

@Service
public class ServicoPizza {

	@Autowired
	private ServicoPizzaria servicoPizzaria;

	@Autowired
	private PizzaRepositorio repositorio;

	public void salvar(Pizza pizza) {
		pizza.setProprietario(servicoPizzaria.getPizzariaLogada());
		repositorio.save(pizza);
	}

	public Iterable<Pizza> listar() {
		Pizzaria proprietario = servicoPizzaria.getPizzariaLogada();
		return repositorio.findAllByProprietario(proprietario);

	}

	public Pizza buscar(long id) {
		Pizzaria proprietario = servicoPizzaria.getPizzariaLogada();
		return repositorio.findByIdAndProprietario(id, proprietario);

	}

	public void remover(long id) {
		Pizza pizza = this.buscar(id);
		if (pizza != null)
			repositorio.delete(pizza);
	}

	//Retorna uma lista oderndada de nomes de pizzas
	public List<String> listarNomesPizzasDisponiveis() {
		List<Pizza> pizzas = repositorio.findAll();		
		
		List<String> nomesPizzas = pizzas.stream().map((pizza)->{
			return pizza.getNome();
		}).sorted().collect(Collectors.toList());
		
		return nomesPizzas;
	}
}
