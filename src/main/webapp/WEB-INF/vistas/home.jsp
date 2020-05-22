<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
						<h1 class="no-margins">${cantidadInsumos}</h1>

						<small>Total disponibles </small>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12 col-lg-6">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>Establecimientos</h5>
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
										<th>Capacidad</th>
										<th>Ocupacion</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty listaEstablecimientos}">
										<c:forEach items="${listaEstablecimientos}"
											var="itemEstablecimiento">
											<tr>
												<td>${itemEstablecimiento.getId()}</td>
												<td>${itemEstablecimiento.getNombre()}</td>
												<td>${itemEstablecimiento.getCapacidad()}</td>
												<td>${itemEstablecimiento.getOcupacion()}</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-12 col-lg-6">
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
									</label> <label class="btn btn-sm btn-white active"> <input
										type="radio" id="option2" name="options">
										Ocupación sobre Capacidad
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
										<th>Prioridad (%)</th>
										<th>Zona</th>
										<th>Respiradores</th>
										<th>Medicamentos</th>
										<th>Delantales</th>
										<th>Guantes</th>
										<th>Camas</th>
										<th>Jeringas</th>
										<th>Tapa bocas</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${MapaDistribuido}" var="listElement">
										<tr>
											<td>${listElement.key.getId()}</td>
											<td>${listElement.key.getNombre()}</td>
											<td>${listElement.key.getCapacidad()}</td>
											<td>${listElement.key.getPrioridad()} %</td>
											<td>${listElement.key.getZona()}</td>
											<c:forEach items="${listElement.value}" var="mapElement">
												<!--<td>${mapElement.key}:</td>-->
												<td>${mapElement.value}</td>
											</c:forEach>
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
