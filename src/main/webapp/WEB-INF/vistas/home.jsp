<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:layout>
	<div class="wrapper wrapper-content animated fadeInRight mb-5">
		<div class="row">
			<div class="col-sm-4" style="padding-left: 0;">
				<div class="col-lg-6">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>Establecimientos</h5>
						</div>
						<div class="ibox-content">
							<h1 class="no-margins">${cantidadEstablecimientos}</h1>
							<div class="stat-percent font-bold text-navy">En total</div>
						</div>
					</div>
				</div>
				<div class="col-lg-6" >
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>Insumos</h5>
						</div>
						<div class="ibox-content">
							<h1 class="no-margins">${cantTotalInsumos}</h1>
							<div class="stat-percent font-bold text-navy">En total</div>
						</div>
					</div>
				</div>
				<div class="col-lg-12">
					<a class="btn btn-lg btn-primary" style="width: 100%;height: 75px;line-height: 50px;"
						href="./distribuirInsumos?strategy=OCUPACION">Distribuir insumos</a>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="panel ">
					<div class="panel-heading">
						<h3 class="panel-title">Total de Insumos por Tipo</h3>
					</div>
					<div class="panel-body">
						<div id="demo-bar-chart" class="ct-chart"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>Establecimientos</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>
							<div class="col-sm-12"></div>
						</div>
						<div class="btn-group btn-group-toggle">
							<p>Calcular índice de prioridad por</p>
							<a class="btn btn-sm btn-white"
								href="./calcularPrioridad?strategy=OCUPACION"> Relación
								Ocupación - Capacidad</a> <a class="btn btn-sm btn-white"
								href="./calcularPrioridad?strategy=CAPACIDAD"> Capacidad
								Total</a> <a class="btn btn-sm btn-white"
								href="./calcularPrioridad?strategy=ZONA"> Zona </a> <a
								class="btn btn-sm btn-white"
								href="./calcularPrioridad?strategy=COMBINADO"> Combinado </a>
						</div>
					</div>


					<div class="ibox-content">
						<div class="table-responsive">
							<table class="table table-striped table-hover grilla-dataTable">
								<thead>
									<tr>
										<th>#</th>
										<th>Nombre</th>
										<th>Capacidad</th>
										<th>Ocupacion</th>
										<th>Zona</th>
										<th>Prioridad</th>
									</tr>
								</thead>
								<tbody>
									<!-- Si las listas con la Prioridad: NO SON VACIAS => Mostrame esa lista sino la default -->
									<c:choose>
										<c:when test="${not empty establConPrioridad}">
											<c:forEach items="${establConPrioridad}" var="estItem">
												<tr>
													<td>${estItem.getId()}</td>
													<td>${estItem.getNombre()}</td>
													<td>${estItem.getCapacidad()}</td>
													<td>${estItem.getOcupacion()}</td>
													<td>${estItem.getZona().getNombre()}</td>
													<td class="text-center"><fmt:formatNumber
															type="number" maxFractionDigits="2"
															value="${estItem.getPrioridad()}" /> %</td>
												</tr>
											</c:forEach>
										</c:when>
										<c:when test="${not empty listaEstablecimientos}">
											<c:forEach items="${listaEstablecimientos}" var="estItem">
												<tr>
													<td>${estItem.getId()}</td>
													<td>${estItem.getNombre()}</td>
													<td>${estItem.getCapacidad()}</td>
													<td>${estItem.getOcupacion()}</td>
													<td>${estItem.getZona().getNombre()}</td>
													<td class="text-center">-</td>
												</tr>
											</c:forEach>
										</c:when>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>Insumos</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="table-responsive">
							<table class="table table-striped table-hover grilla-dataTable">
								<thead>
									<tr>
										<th>#</th>
										<th>Nombre</th>
										<th>Tipo</th>
										<th>Cantidad</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty listaInsumos}">
										<c:forEach items="${listaInsumos}" var="item">
											<tr>
												<td>${item.getId()}</td>
												<td>${item.getNombre()}</td>
												<td>${item.getTipo()}</td>
												<td>${item.getCantidad()}</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
</t:layout>

<script src="js/vistas/home.js"></script>


<script>
	$(function() {
		var options;
		var data = {
			labels : [ 
						<c:forEach items="${listaInsumos}" var="item">
							'${item.getNombre()}'
							<c:if test="${!item.getNombre()}">,</c:if>
						</c:forEach>],
			series : [
						[ 
						<c:forEach items="${listaInsumos}" var="item">
							${item.getCantidad()},
						</c:forEach> ], ]
					};
		// bar chart
		options = {
			height : "150px",
			axisX : {
				showGrid : false
			},
		};

		new Chartist.Bar('#demo-bar-chart', data, options);		

	});
</script>
