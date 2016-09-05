package br.com.fcffc.pizzaria.modelo.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fcffc.pizzaria.model.repositorios.IngredienteRepositorio;
import br.com.fcffc.pizzaria.modelo.entidades.Ingrediente;
import br.com.fcffc.pizzaria.modelo.entidades.Pizzaria;

@Service
public class ServicoIngrediente {

	@Autowired
	private ServicoPizzaria servicoPizzaria;

	@Autowired
	private IngredienteRepositorio repositorio;

	public void salvar(Ingrediente ingrediente) {
		ingrediente.setProprietario(servicoPizzaria.getPizzariaLogada());
		repositorio.save(ingrediente);
	}

	public Iterable<Ingrediente> listar() {
		Pizzaria proprietario = servicoPizzaria.getPizzariaLogada();
		return repositorio.findAllByProprietario(proprietario);

	}

	public Ingrediente buscar(long id) {
		Pizzaria proprietario = servicoPizzaria.getPizzariaLogada();
		return repositorio.findByIdAndProprietario(id, proprietario);

	}

	public void remover(long id) {
		Ingrediente ingrediente = this.buscar(id);
		if (ingrediente != null)
			repositorio.delete(ingrediente);
	}
}
