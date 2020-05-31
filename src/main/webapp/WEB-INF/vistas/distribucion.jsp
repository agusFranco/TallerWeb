<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
						<h2>Distribución</h2>
						<p>
							Asignación de insumos a establecimientos.<br>Selecciona el
							metodo de distribución de la derecha.
						</p>
						<div class="clients-list">
							<div class="tab-content">
								<div id="tab-1" class="tab-pane active">
									<div class="slimScrollDiv"
										style="position: relative; overflow: hidden; width: auto; height: 100%;">
										<div class="full-height-scroll"
											style="overflow: hidden; width: auto; height: 100%;">
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
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${MapaDistribuido}" var="MapElement">
															<tr>
																<td><fmt:formatNumber type="number"
																		maxFractionDigits="2"
																		value="${MapElement.key.getPrioridad()}" /></td>
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
																			${listElement.getNombre()}:
																			${listElement.getCantidad()} </span>
																	</c:forEach></td>
															</tr>
														</c:forEach>

													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
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
								<h5>Insumos sobrantes en repartija equitativa</h5>
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
									<c:forEach items="${MapaDistribuido}" var="MapElement">
										<c:if test="${MapElement.key.id eq 1}">
										<p style="font-size: 20px;">${MapElement.key.nombre}</p>
    								</c:if>
									</c:forEach>
								</div>

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
										<strong> Distribución por Ocupacion </strong>
										<p>Los insumos se distribuyen en base a la prioridad
											establecidad por el cálculo del porcentaje de ocupación de
											los establecimientos.</p>
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
											establecidad por la capicidad total del establecimiento.</p>
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
										<p>Los Establecimiento con prioridades entre un 10% - 20%
											recibiran un ...</p>
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
										<p>Los insumos se distribuyen en base a la prioridad
											establecidad.</p>
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
											los establecimientos.</p>
										<a href="./distribuirInsumos?strategy=EQUITATIVO"
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


