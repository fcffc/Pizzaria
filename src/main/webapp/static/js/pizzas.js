$(document).ready(function() {
	aplicarListeners();
});

var aplicarListeners = function(){
	$('#btn-salvar').on('click', function() {
		var url = 'pizzas';
		var dadosIngrediente = $('#form-pizza').serialize();

		$.post(url, dadosIngrediente)
		.done(function(pagina) {
			$('#secao-pizzas').html(pagina);
			aplicarListeners();
				
			})
		.fail(function() {
			alert('Falha ao salvar.');
		})
		.always(function(){
			$('#modal-pizza').modal('hide');
		});
	});
};