<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:layout>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-md-3">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>Establecimientos</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins">${cantidadEstablecimientos}</h1>

						<small>Total </small>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<span class="label label-success pull-right">Disponibilidad</span>
						<h5>Insumos</h5>
					</div>
					<div class="ibox-content">
						<h1 class="no-margins">${cantTotalInsumos}</h1>

						<small>Total disponibles </small>
					</div>
				</div>
			</div>
		</div>






		<div class="row">
			<div class="col-sm-12 col-lg-7">
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
								href="./calcular-prioridad?prioridad=OCUPACION"> Relación
								Ocupación - Capacidad</a> <a class="btn btn-sm btn-white"
								href="./calcular-prioridad?prioridad=CAPACIDAD"> Capacidad
								Total</a> <a class="btn btn-sm btn-white"
								href="./calcular-prioridad?prioridad=ZONA"> Zona </a> <a
								class="btn btn-sm btn-white"
								href="./calcular-prioridad?prioridad=COMBINADO"> Combinado </a>
							<button class="btn btn-sm btn-white"
								onclick="calcularPrioridad()">Calcular Prioridad con
								Ajax</button>
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

			<div class="col-sm-12 col-lg-5">
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
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>Simulador de distribución de insumos a establecimientos</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-10 m-b-xs">
								<div data-toggle="buttons" class="btn-group">
									<p>Calcular Índice de riesgo por</p>
									<label class="btn btn-sm btn-white"> <input
										type="radio" id="option1" name="options"> Por Indice
									</label> <label class="btn btn-sm btn-white"> <input
										type="radio" id="option2" name="options"> Ocupación
										sobre Capacidad
									</label> <label class="btn btn-sm btn-white"> <input
										type="radio" id="option3" name="options"> Por Zona
									</label>
								</div>
							</div>
							<div class="col-sm-2">
								<form action="./home" method="POST">
									<button type="submit" class="btn btn-primary">
										<i class="fa fa-medkit"></i><strong> Iniciar
											simulacion</strong>
									</button>
								</form>
							</div>
						</div>
						<div class="table-responsive">
							<table class="table table-striped table-hover grilla-dataTable">
								<thead>
									<tr>
										<th>#</th>
										<th>Establecimiento</th>
										<th>Capacidad</th>
										<th>Ocupación</th>
										<th>Zona</th>
										<th>Prioridad</th>
										<th>Total Insumos Asignados</th>
										<!--	<th>Respiradores</th>
									 	<th>Medicamentos</th>
										<th>Jeringas</th>
										<th>Tapa bocas</th>
										<th>Delantales</th>
										<th>Camas</th>
										<th>Guantes</th> -->
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${MapaDistribuido}" var="MapElement">
										<tr>
											<td>${MapElement.key.getId()}</td>
											<td>${MapElement.key.getNombre()}</td>
											<td>${MapElement.key.capacidad}</td>
											<td>${MapElement.key.ocupacion}</td>
											<td>${MapElement.key.getZona().getNombre()}</td>
											<td>${MapElement.key.getPrioridad()}%</td>
											<td><a class="text-info font-weight-bold h4"
												href="detalles-asignaciones?idEstabl=${MapElement.key.getId()}">
													${MapElement.key.capacidad*1.4}-dummy (Clic/detalle)</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</t:layout>

<script src="js/vistas/home.js"></script>
