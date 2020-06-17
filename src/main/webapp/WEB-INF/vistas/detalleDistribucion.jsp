<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="relativePath" value="${pageContext.request.contextPath}" />

<t:layout>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-6">
				<div class="ibox">
					<div class="ibox-content">
						<h2>
							Distribucion <strong>#${distribucion.getId()}</strong>
						</h2>

						<dl class="dl-horizontal">
							<dt>Fecha de Creacion</dt>
							<dd>${distribucion.getFechaDistribucion()}</dd>
							<dt>Fecha de Entrega</dt>
							<dd>${distribucion.getFechaEntrega()}</dd>
							<dt>Tipo de Distribucion:</dt>
							<dd>
								<span class="text-navy"> ${distribucion.getTipoDistribucion().getNombre()}</span>
							</dd>
						</dl>
						<div class="clients-list">
							<div class="table-responsive">
								<table data-show-toggle="false" class="tablez table table-striped table-hover grilla-dataTable">
									<thead>
										<tr>
											<th>Establecimiento</th>
											<th>Distribucion</th>
											<th>Detalle</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${detalles}" var="detalle" varStatus="loop">
											<tr>
												<td><i class="fa fa-hospital-o"></i> ${detalle.key.getNombre()}</td>
												<td><c:set var="total" value="${0}" /> <c:forEach items="${detalle.value}" var="listElement">
														<c:set var="total" value="${total + listElement.getCantidad()}" />
													</c:forEach> <span class="label label-success">Total: ${total}</span></td>
												<td class="client-status"><a data-toggle="modal" data-target="#${loop.index}"> <i class="fa fa-eye"> </i>
												</a>
													<div class="modal inmodal" id="${loop.index}" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
														<div class="modal-dialog">
															<div class="modal-content animated flipInY">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">
																		<span aria-hidden="true">x</span><span class="sr-only">Close</span>
																	</button>
																	<i class="fa fa-hospital-o modal-icon"></i>
																	<h4 class="modal-title">${detalle.key.getNombre()}</h4>
																</div>
																<div class="modal-body">
																	<div class="row">
																		<div class="col-md-2">
																			<strong>Responsable:</strong>
																		</div>
																		<div class="col-md-4">${detalle.key.responsable.getNombre()},${detalle.key.responsable.getApellido()}</div>
																		<div class="col-md-2">
																			<strong>Ubicacion:</strong>
																		</div>
																		<div class="col-md-4">${detalle.key.getUbicacion()}</div>
																	</div>
																	<div class="row">
																		<div class="col-md-2">
																			<strong>Capacidad:</strong>
																		</div>
																		<div class="col-md-4">${detalle.key.getCapacidad()}</div>
																		<div class="col-md-2">
																			<strong>Ocupacion:</strong>
																		</div>
																		<div class="col-md-4">${detalle.key.getOcupacion()}</div>
																	</div>
																	<div class="row">
																		<div class="col-md-2">
																			<strong>Zona:</strong>
																		</div>
																		<div class="col-md-4">${detalle.key.zona.nombre}</div>
																		<div class="col-md-2">
																			<strong>Puntaje de zona:</strong>
																		</div>
																		<div class="col-md-4">${detalle.key.zona.puntaje}</div>
																	</div>
																	<div>
																		<hr>
																		<br>
																		<c:forEach items="${detalle.value}" var="listElement">
																			<span style="margin: 0px 0px 10px 0px; padding: 5px; display: inline-block; font-size: 13px;" class="label label-warning"> ${listElement.insumo.getNombre()}:
																				${listElement.insumo.getCantidad()} </span>
																		</c:forEach>
																	</div>
																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
																</div>
															</div>
														</div>
													</div></td>
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
	</div>
</t:layout>
<script src="${relativePath}/js/vistas/detalleDistribucion.js"></script>
