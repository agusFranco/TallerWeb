<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>WebOnes · Insumos Médicos</title>

<link href="css/template/bootstrap.min.css" rel="stylesheet">
<link href="css/template/animate.css" rel="stylesheet">
<link href="css/template/style.css" rel="stylesheet">
<link href="css/fonts/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<div id="wrapper">
		<div id="page-nav-header">
			<!-- <jsp:invoke fragment="header"/> -->
			<nav class="navbar-default navbar-static-side" role="navigation">
				<div class="sidebar-collapse">
					<ul class="nav" id="side-menu">
						<li class="nav-header">
							<div class="dropdown profile-element">
								<a data-toggle="dropdown" class="dropdown-toggle" href="#">
									<span class="clear"> <span class="block m-t-xs">
											<strong class="font-bold">Bienvenido</strong><br />
											<strong class="font-bold">"Nombre del usuario"</strong>
									</span> <span class="text-muted text-xs block">Menú <b
											class="caret"></b></span>
								</span>
								</a>
								<ul class="dropdown-menu animated fadeInRight m-t-xs">
									<li><a href="#">Item</a></li>
								</ul>
							</div>
							<div class="logo-element">WO</div>
						</li>
						<li><a href="#"><i class="fa fa-th fa-2x"></i> <span
								class="nav-label">Página de inicio</span> </a></li>
						<li><a href="#"><i class="fa fa-map-marker fa-2x"></i> <span
								class="nav-label">Mapa de distribución</span> </a></li>
					</ul>
				</div>
			</nav>
		</div>
		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top white-bg" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="#"><i class="fa fa-bars"></i> </a>
						<form role="search" class="navbar-form-custom" method="post"
							action="">
							<div class="form-group">
								<input type="text" placeholder="Buscar..."
									class="form-control" name="top-search" id="top-search">
							</div>
						</form>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li><a href="#" id="loginLink">Cerrar Sesión</a></li>
					</ul>
				</nav>
			</div>
			<div id="body">
				<jsp:doBody />
			</div>
			<div id="page-footer">
				<div class="footer">
					<div class="pull-right">Insumos Medicos</div>
					<div>
						<strong>WebOnes</strong> UNLaM - 2020
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="js/template/jquery-3.1.1.min.js"></script>
<script src="js/template/bootstrap.min.js"></script>
<script src="js/template/plugins/metisMenu/metisMenu.min.js"></script>
<script src="js/template/plugins/pace/pace.min.js"></script>
<script src="js/template/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="js/template/app/inspinia.js"></script>
</html>