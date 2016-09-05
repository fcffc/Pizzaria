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
			<td style="width: 10%">#</td>

		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pizzarias}" var="pizzaria">
			<tr data-id="${pizzaria.id}">
				<td>${pizzaria.id}</td>
				
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">Pizzarias encontradas: <span id="quantidade-pizzaria">${pizzarias.size()}</span></td>
		</tr>		
	</tfoot>
</table>