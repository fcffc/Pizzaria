$(document).ready(function() {
	aplicarListeners();	

});

var limparModal = function(){
	$('#id').val('');
	$('#nome').val('');
	$('#categoria').val('');
};

var aplicarListeners = function(){
	$('#modal-ingrediente').on('hide.bs.modal', limparModal);
	$('.btn-editar').on('click', function(){
		var id = $(this).parents('tr').data('id');
		var url = 'ingredientes/'+id;
		
		$.get(url)
			.done(function(ingrediente){
				$('#id').val(ingrediente.id);
				$('#nome').val(ingrediente.nome);
				$('#categoria').val(ingrediente.categoria);
				
				$('#modal-ingrediente').modal('show');
			});
	});
	
	$('.btn-deletar').on('click', function(){
		var id = $(this).parents('tr').data('id');
		
		$.ajax({
			url: "ingredientes/"+id,
			type: 'DELETE',
			success: function(result){
				$('tr[data-id="'+id+'"]').remove();
				var ingredientes = parseInt($('#quantidade-ingredientes').text());
				$('#quantidade-ingredientes').text(ingredientes - 1);
			}
		});
	});
	
	$('#btn-salvar').on('click', function() {
		var url = 'ingredientes';
		var dadosIngrediente = $('#form-ingrediente').serialize();

		$.post(url, dadosIngrediente)
		.done(function(pagina) {
			$('#secao-ingredientes').html(pagina);
			aplicarListeners();
				
			})
		.fail(function() {
			alert('Falha ao salvar.');
		})
		.always(function(){
			$('#modal-ingrediente').modal('hide');
		});
	});
}

