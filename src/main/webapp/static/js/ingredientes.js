$(document).ready(function() {
	$('#btn-salvar').on('click', function() {
		var url = 'ingredientes';
		var dadosIngrediente = $('#form-ingrediente').serialize();

		$.post(url, dadosIngrediente)
		.done(function(pagina) {
			$('#secao-ingredientes').html(pagina);
				
			})
		.fail(function() {
			alert('Falha ao salvar.');
		})
		.always(function(){
			$('#modal-ingrediente').modal('hide');
		});
	});
	
	$('.btn-deletar').on('click', function(){
		
	})
});