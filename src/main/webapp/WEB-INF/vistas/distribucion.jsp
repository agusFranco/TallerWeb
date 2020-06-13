<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
.footable-details th {
	font-weight: normal;
	font-size: 13px;
	line-height: 50px;
}
</style>

<t:layout>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-8">
				<div class="ibox">
					<div class="ibox-content">
						<h2>
							Distribucion - <span
								style="text-transform: lowercase; font-style: italic;">${not empty param.strategy ? param.strategy : "equitativo con cambio de asignacion"}</span>
						</h2>
						<p>
							Asignacion de insumos a establecimientos.<br>Selecciona el
							metodo de Distribucion de la derecha.
						</p>
						<div class="clients-list">
							<div class="table-responsive">
								<table data-show-toggle="false"
									class="tablez table table-striped table-hover grilla-dataTable">
									<thead>
										<tr>
											<th>Prioridad</th>
											<th>Establecimiento</th>
											<th>Distribucion</th>
											<th>Detalle</th>
											<th data-breakpoints="all" data-title="Insumos:"></th>
											<th data-breakpoints="all" data-title="Parametros:"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${MapaDistribuido}" var="MapElement">
											<tr>
												<td><c:choose>
														<c:when
															test="${param.strategy == 'EQUITATIVO' || empty param.strategy}">
															<p class="text-success">${establecMayorOcupacion.nombre==MapElement.key.getNombre() ? "Insumos Extra" : "~"}</p>
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
												<td class="client-status"><a<%-- href="./detalle?id=${MapElement.key.getId()}" --%>>
														<i class="fa fa-external-link"> </i>
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
															class="btn btn-primary"> <strong>Ocupacion:
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

							<c:set var="s_strategy" value="${param.strategy}" />
							<form:form action="./confirmarDistribucion" method="GET">
								<input type="hidden" name="strategy" value="${s_strategy}">
								<button style="margin-top: 20px; font-size: 15px;" type="submit"
									class="btn btn-success btn-sm btn-block">
									<i class="fa fa-check"></i> Solicitar Distribucion
								</button>

							</form:form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<c:choose>
					<c:when
						test="${param.strategy == 'EQUITATIVO' || empty param.strategy}">
						<div class="ibox float-e-margins col-md-12">
							<div class="ibox-title">
								<h5>Insumos sobrantes en distribucion equitativa</h5>
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
									<strong>Cambiar establecimiento de asignacion</strong>
								</div>
								<form:form action="cambiarInsumos" method="POST"
									modelAttribute="establecimiento">
									<form:select path="id" name="id" class="form-control"
										id="establecimiento"> --%>
										<c:forEach items="${MapaDistribuido}" var="est">
											<form:option value="${est.key.id}">${est.key.nombre}</form:option>
										</c:forEach>
									</form:select>
									<button class="btn btn-lg btn-info btn-block font-weight-bold"
										Type="Submit" style="font-size: 15px;">Confirmar
										movimiento de asignacion</button>
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
										<strong> Distribucion por ocupacion </strong>
										<p>Los establecimientos definen su prioridad en base a su
											porcentaje de ocupacion. Estos se agrupan en 3 grupos
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
										<strong> Distribucion por capacidad total </strong>
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
										<strong> Distribucion por zona </strong>
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
										<strong> Distribucion combinada </strong>
										<p>Los establecimientos definen su prioridad en base a la
											ocupacion sobre la capacidad , la capacidad total y la
											puntuacion de la zona. Luego los establecimientos se dividen
											en 5 grupos de igual cantidad y los restantes se suman al
											&#250;ltimo grupo.
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
										<strong> Distribucion equitativa </strong>
										<p>Los insumos se distribuyen equitativamente entre todos
											los establecimientos sin la determinacion de un ondice de
											riesgo para cada uno de ellos.</p>
										<i>Al establecimiento con mayor cantidad de infectados se
											le otorgaron los insumos sobrantes, los cuales no pudieron
											ser distribuidos equitativamente entre la totalidad de los
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


