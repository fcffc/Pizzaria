package br.com.fcffc.pizzaria.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fcffc.pizzaria.model.repositorios.IngredienteRepositorio;
import br.com.fcffc.pizzaria.modelo.entidades.Ingrediente;

@Component
public class IngredientePropertyEditor extends PropertyEditorSupport {
	@Autowired private IngredienteRepositorio ingredienteRepositorio;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long idIngrediente = Long.parseLong(text);
		Ingrediente ingrediente = ingredienteRepositorio.findOne(idIngrediente);
		//Converte os id's e seta os ingredientes
		setValue(ingrediente);
	}

}
