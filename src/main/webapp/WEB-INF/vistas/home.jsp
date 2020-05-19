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
						<div class="stat-percent font-bold text-success">
							98% <i class="fa fa-bolt"></i>
						</div>
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
						<div class="stat-percent font-bold text-success">
							98% <i class="fa fa-bolt"></i>
						</div>
						<small>Total disponibles </small>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>Simulador de distribución de insumos a hospitales</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="fa fa-wrench"></i>
							</a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="./cargar-establecimientos">Cargar
										Establecimientos</a></li>
								<li><a href="./cargar-insumos">Cargar Insumos</a></li>
							</ul>
							<a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-9 m-b-xs">
								<div data-toggle="buttons" class="btn-group">
									<a href="#" class="btn btn-primary btn-sm disabled"><strong>Mostrar
											detalles </strong></a> <label class="btn btn-sm btn-white active">
										<input type="radio" id="option1" name="options">
										Listar Cantidades
									</label> <label class="btn btn-sm btn-white "> <input
										type="radio" id="option2" name="options"> Listar
										Hospitales
									</label> <label class="btn btn-sm btn-white "> <input
										type="radio" id="option3" name="options"> Listar
										Insumos
									</label>
								</div>
							</div>
							<div class="col-sm-3">
								<button type="button" class="btn btn-primary btn-block">
									<strong> <a href="./distribuir-insumos">Distribuir
											insumos sobre Establecimientos</a></strong>
								</button>
							</div>
						</div>
						<div class="container-fluid d-flex">
							<div class="table-responsive row col-md-6">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>#</th>
											<th>Establecimientos</th>
											<th>Cantidad de Personas Infectadas</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<c:if test="${not empty listaEstablecimientos}">
												<c:forEach items="${listaEstablecimientos}"
													var="itemEstablecimiento">
													<tr>
														<td>${itemEstablecimiento.getId()}</td>
														<td>${itemEstablecimiento.getNombre()}</td>
														<td>${itemEstablecimiento.getIndice()}</td>
													</tr>
												</c:forEach>
											</c:if>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="table-responsive row col-md-6">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>#</th>
											<th>Insumos</th>
											<th>Cantidad de Unidades Disponibles</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<c:if test="${not empty listaInsumos}">
												<c:forEach items="${listaInsumos}" var="itemInsumo">
													<tr>
														<td>${itemInsumo.getId()}</td>
														<td>${itemInsumo.getNombre()}</td>
														<td>${itemInsumo.getCantidad()}</td>
													</tr>
												</c:forEach>
											</c:if>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>Distribución de cantidades de insumos a Establecimientos</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="fa fa-wrench"></i>
							</a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="#">Config option 1</a></li>
								<li><a href="#">Config option 2</a></li>
							</ul>
							<a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="row">
							<div class="col-sm-9 m-b-xs">
								<div data-toggle="buttons" class="btn-group">
									<a href="#" class="btn btn-primary btn-sm disabled"><strong>Ordenar
											por </strong></a> <label class="btn btn-sm btn-white active"> <input
										type="radio" id="option1" name="options">Cantidad de
										Personas Infectadas
									</label> <label class="btn btn-sm btn-white "> <input
										type="radio" id="option2" name="options"> Cantidad de
										Respiradores artificiales
									</label> <label class="btn btn-sm btn-white"> <input
										type="radio" id="option3" name="options"> Cantidad de
										Camas de Internación
									</label>
								</div>
							</div>
						</div>

						<div class="container-fluid d-flex">
							<div class="table-responsive row col-md-12">
								<table class="table table-striped">
									<thead>
										<tr>
											<th class="btn-primary">#</th>
											<th class="btn-primary">Establecimientos</th>
											<th class="btn-primary">Cantidad de Infectados</th>
											<th class="btn-primary">Respiradores Artificiales</th>
											<th class="btn-primary">Camas de Internación</th>
											<th class="btn-primary">Barbijos</th>
											<th class="btn-primary">Artículos de desinfección</th>
										</tr>
									</thead>

									<tbody>
										<tr>
											<td>1</td>
											<td>Hospital 1</td>
											<td>50</td>
											<td>x cantidad</td>
											<td>x cantidad</td>
											<td>x cantidad</td>
											<td>x cantidad</td>

										</tr>
										<tr>
											<td>2</td>
											<td>Hospital 2</td>
											<td>30</td>
											<td>x cantidad</td>
											<td>x cantidad</td>
											<td>x cantidad</td>
											<td>x cantidad</td>
										</tr>
										<tr>
											<td>3</td>
											<td>Hospital 3</td>
											<td>54</td>
											<td>x cantidad</td>
											<td>x cantidad</td>
											<td>x cantidad</td>
											<td>x cantidad</td>
										</tr>
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
