<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<t:layout>
	<div class="row">
		<div class="col-lg-6">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Historial de distribuciones</h5>
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						</a> <a class="close-link"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<table class="table table-hover no-margins">
						<thead>
							<tr>
								<th>Estado</th>
								<th>Fecha de Solicitud</th>
								<th>Fecha de Entrega</th>
								<th>Tipo-Distribucion</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${distribucionesDetalles}" var="item">
								<tr>
									<td><span class="label label-primary">Generado</span></td>
									<td>${item.fechaDistribucion}</td>
									<td><i class="fa fa-clock-o"></i>${item.fechaEntrega}</td>
									<td class="text-navy">${item.tipoDistribucion.nombre}<i
										class="fa fa-random"></i></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-lg-4 ">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Cantidad por Tipo de Distribuciones</h5>
				</div>
				<div class="ibox-content">
					<div id="ct-chart4" class="ct-perfect-fourth"></div>
				</div>
			</div>
		</div>
	</div>
</t:layout>

<script src="js/vistas/home.js"></script>

<script>
	$(document).ready(
			function() {
				// Stocked horizontal bar

				new Chartist.Bar('#ct-chart4', {
					labels : [  'Combinada','Ocupacion','Capacidad',
							'Zona','Equitativa'],
					series : [ [ 
					<c:forEach items="${cantidadPorTipo}" var="item">
						${item[1]},
					</c:forEach> ] ]
				
				}, {
					seriesBarDistance : 1,
					reverseData : true,
					horizontalBars : true,
					axisY : {
						offset : 70

					}
					
				});
			});
</script>



