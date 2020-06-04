<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
.footable-details th {
	font-weight: normal;
	font-size: 13px;
	line-height: 50px;
}
</style>

<t:layout>

	<%-- 	<form:form action="/cambiarInsumos" method="POST"
		modelAttribute="establecimiento">
		<form:input path="nombre" id="idEstablec" type="text"
			class="form-control" placeholder="Ingrese id establecimiento" />

		<button class="btn btn-lg btn-info" Type="Submit">
			<strong>Enviar</strong>
		</button>
	</form:form> --%>


	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-8">
				<div class="ibox">
					<div class="ibox-content">
						<h2>Distribución</h2>
						<p>
							Asignaci&#243;n de insumos a establecimientos.<br>Selecciona
							el metodo de Distribución de la derecha.
						</p>
						<div class="clients-list">
							<div class="table-responsive">
								<table data-show-toggle="false"
									class="tablez table table-striped table-hover grilla-dataTable">
									<thead>
										<tr>
											<th>Prioridad</th>
											<th>Establecimiento</th>
											<th>Distribución</th>
											<th>Detalle</th>
											<th data-breakpoints="all" data-title="Insumos:"></th>
											<th data-breakpoints="all" data-title="Parámetros:"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${MapaDistribuido}" var="MapElement">
											<tr>
												<td><c:choose>
														<c:when test="${param.strategy == 'EQUITATIVO'}">
															<p>-</p>
														</c:when>
														<c:otherwise>
															<fmt:formatNumber type="number" maxFractionDigits="2"
																value="${MapElement.key.getPrioridad()}" />
														</c:otherwise>
													</c:choose></td>
												<td><i class="fa fa-hospital-o"></i>
													${MapElement.key.getNombre()}</td>

												<c:set var="total" value="${0}" />
												<c:forEach items="${MapElement.value}" var="listElement">
													<c:set var="total"
														value="${total + listElement.getCantidad()}" />
												</c:forEach>
												<td><span class="label label-success"
													style="font-size: 13px">Total: ${total}</span></td>
												<td class="client-status"><a
													href="./detalle?id=${MapElement.key.getId()}"> <i
														class="fa fa-external-link"> </i>
												</a></td>
												<td><c:forEach items="${MapElement.value}"
														var="listElement">
														<span
															style="margin: 0px 0px 10px 0px; padding: 5px; display: inline-block; font-size: 13px;"
															class="label label-info">
															${listElement.getNombre()}: ${listElement.getCantidad()}
														</span>
													</c:forEach></td>
												<td>
													<div class="d-flex align-items-center">
														<span
															style="margin: 0px 0px 5px 0px; padding: 2px; display: inline-block; font-size: 13px;"
															class="btn btn-primary"> <strong>Capacidad
																Total: </strong> ${MapElement.key.capacidad}
														</span> <span
															style="margin: 0px 0px 5px 0px; padding: 2px; display: inline-block; font-size: 13px;"
															class="btn btn-primary"> <strong>Ocupación:
														</strong> ${MapElement.key.ocupacion}
														</span> <span
															style="margin: 0px 0px 5px 0px; padding: 2px; display: inline-block; font-size: 13px;"
															class="btn btn-primary"> <strong>Zona: </strong>
															${MapElement.key.zona.nombre}
														</span> <span
															style="margin: 0px 0px 5px 0px; padding: 2px; display: inline-block; font-size: 13px;"
															class="btn btn-primary"> <strong>Puntaje
																de la zona: </strong> ${MapElement.key.zona.puntaje}
														</span>
													</div>
												</td>

											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
							<button style="margin-top: 20px;" type="button"
								class="btn btn-success btn-sm btn-block">
								<i class="fa fa-check"></i> Confirmar Distribución
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<c:choose>
					<c:when test="${param.strategy == 'EQUITATIVO'}">
						<div class="ibox float-e-margins col-md-12">
							<div class="ibox-title">
								<h5>Insumos sobrantes en distribución equitativa</h5>
							</div>
							<div class="ibox-content">
								<h1 class="no-margins text-center">${insumosSobrantes}</h1>
								<div class="stat-percent font-bold text-navy">En total</div>
							</div>
							<div class="ibox float-e-margins col-md-12">
								<div class="ibox-title">
									<h5>Asignados al Establecimiento</h5>
								</div>
								<div class="ibox-content">
									<p style="font-size: 20px;">${establecMayorOcupacion.nombre}</p>
								</div>
								<div class="ibox-title">
									<strong>Cambiar establecimiento de asignación</strong>
								</div>
								<form:form action="cambiarInsumos" method="POST"
									modelAttribute="establecimiento">
									<form:select path="id" class="form-control"
										id="establecimiento"> --%>
										<%-- <form:option value="{establecMayorOcupacion.id}" selected="selected">${establecMayorOcupacion.nombre}</form:option> --%>
										<c:forEach items="${MapaDistribuido}" var="est">
											<form:option value="{est.key.id}">${est.key.nombre}</form:option>
										</c:forEach>
									</form:select>

									<form:input path="nombre" type="text" id="nombre"
										class="form-control" placeholder="nombre" value="asdasdasda" />
									<button class="btn btn-lg btn-info btn-block font-weight-bold"
										Type="Submit" style="font-size: 15px;">Confirmar
										movimiento de asignación</button>
								</form:form>
							</div>

						</div>

					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
				<div class="ibox ">
					<div class="ibox-content">
						<div class="tab-content">
							<div id="contact-1" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<strong> Distribución por ocupacion </strong>
										<p>Los establecimientos definen su prioridad en base a su
											porcentaje de ocupación. Estos se agrupan en 3 grupos
											dependiendo su indice de riesgo.</p>
										<ul>
											<li>El grupo 1 recibe el 60% de cada tipo de insumo. <br />
												<i>El establecimiento con mayor prioridad dentro de este
													grupo recibe el 60% de cada tipo de insumo + insumos
													extras.</i></li>
											<li>El grupo 2 recibe el 30% de cada tipo de insumo.</li>
											<li>El grupo 3 recibe el 10% de cada tipo de insumo.</li>
										</ul>
										<a href="./distribuirInsumos?strategy=OCUPACION"
											class="btn btn-primary btn-sm btn-block"> <i
											class="fa fa-random"></i> Distribuir insumos
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="ibox ">
					<div class="ibox-content">
						<div class="tab-content">
							<div id="contact-1" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<strong> Distribución por capacidad total </strong>
										<p>Los insumos se distribuyen en base a la prioridad
											establecida por la capacidad total del establecimiento.</p>
										<a href="./distribuirInsumos?strategy=CAPACIDAD"
											class="btn btn-primary btn-sm btn-block"> <i
											class="fa fa-random"></i> Distribuir insumos
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="ibox ">
					<div class="ibox-content">
						<div class="tab-content">
							<div id="contact-1" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<strong> Distribución por zona </strong>
										<p>Los insumos se distribuyen en base a la prioridad
											establecida por el puntaje de su zona</p>
										<p>Zona con puntaje:</p>
										<ul>
											<li>Entre 80 y 100 pts - Recibe el 40% de cada tipo de
												insumo.</li>
											<li>Entre 50 y 79 pts - Recibe el 30% de cada tipo de
												insumo.</li>
											<li>Entre 20 y 49 pts - Recibe el 20% de cada tipo de
												insumo.</li>
											<li>Entre 00 y 19 pts - Recibe el 10% de cada tipo de
												insumo.</li>
										</ul>

										<a href="./distribuirInsumos?strategy=ZONA"
											class="btn btn-primary btn-sm btn-block"> <i
											class="fa fa-random"></i> Distribuir insumos
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="ibox ">
					<div class="ibox-content">
						<div class="tab-content">
							<div id="contact-1" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<strong> Distribución combinada </strong>
										<p>Los establecimientos definen su prioridad en base a la
											ocupaci&#243;n sobre la capacidad , la capacidad total y la
											puntuaci&#243;n de la zona. Luego los establecimientos se
											dividen en 5 grupos de igual cantidad y los restantes se
											suman al &#250;ltimo grupo.
										<ul>
											<li>El grupo 1 recibe el 40% de cada tipo de insumo.</li>
											<li>El grupo 2 recibe el 28% de cada tipo de insumo.</li>
											<li>El grupo 3 recibe el 17% de cada tipo de insumo.</li>
											<li>El grupo 4 recibe el 10% de cada tipo de insumo.</li>
											<li>El grupo 5 recibe el 05% de cada tipo de insumo.</li>
										</ul>
										</p>
										<a href="./distribuirInsumos?strategy=COMBINADO"
											class="btn btn-primary btn-sm btn-block"> <i
											class="fa fa-random"></i> Distribuir insumos
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="ibox ">
					<div class="ibox-content">
						<div class="tab-content">
							<div id="contact-1" class="tab-pane active">
								<div class="row">
									<div class="col-md-12">
										<strong> Distribución equitativa </strong>
										<p>Los insumos se distribuyen equitativamente entre todos
											los establecimientos sin la determinación de un índice de
											riesgo para cada uno de ellos.</p>
										<i>Al establecimiento con mayor cantidad de infectados se
											le otorgarán los insumos sobrantes, los cuales no pudieron
											ser distribuídos equitativamente entre la totalidad de los
											establecimientos. </i> <a
											href="./distribuirInsumos?strategy=EQUITATIVO"
											class="btn btn-primary btn-sm btn-block"> <i
											class="fa fa-random"></i> Distribuir insumos
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</t:layout>

<script src="js/vistas/home.js"></script>

<script type="text/javascript">
	jQuery(function($) {
		$('.tablez').footable();
	});
</script>


