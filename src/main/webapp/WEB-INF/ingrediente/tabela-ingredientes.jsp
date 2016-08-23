<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<table
	class="table table-hover table-condensed table-striped table-bordered">
	<thead>
		<tr>
			<td>#</td>
			<td>Nome</td>
			<td>Categoria</td>
			<td>Deletar</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ingredientes}" var="ingrediente">
			<tr>
				<td>${ingrediente.id}</td>
				<td>${ingrediente.nome}</td>
				<td>${ingrediente.categoria}</td>
				<td><button type="button" class="btn btn-danger btn-deletar">Deletar</button></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="4">Ingredientes cadastrados: ${ingredientes.size()}</td>
		</tr>
		<tr>
			<td colspan="4">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#modal-ingrediente">Cadastrar Ingrediente</button>
			</td>
		</tr>
	</tfoot>
</table>