<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Modal Bootstrap -->
<div class="modal fade" id="modal-ingrediente" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<form id="form-ingrediente" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Ingredientes</h4>
				</div>
				<div class="modal-body">
					<label for="nome">Nome</label> <input id="nome" name="nome"
						class="form-control"> 
					<label for="categoria">Categoria</label>
					<select id="categoria" name="categoria" class="form-control">
						<option>Selecione uma categoria.</option>
						<c:forEach items="${categorias}" var="categoria">
							<option value="${categoria}">${categoria}</option>
						</c:forEach>
					</select>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					<button id="btn-salvar" type="button" class="btn btn-primary">Salvar</button>
				</div>
			</form>
		</div>
	</div>
</div>