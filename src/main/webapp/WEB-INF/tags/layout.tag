<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>DIMAR · Distribucion de Insumos Médicos</title>

<link href="css/template/bootstrap.min.css" rel="stylesheet">
<link href="css/template/datatables.min.css" rel="stylesheet">
<link href="css/template/animate.css" rel="stylesheet">
<link href="css/template/style.css" rel="stylesheet">
<link href="css/fonts/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">

<!-- Gráficos -->
<link href="css/chartist/chartist-custom.css" rel="stylesheet">
</head>
<body class="top-navigation">
	<div id="wrapper">
		<div id="page-wrapper">
			<div id="page-header" class="row border-bottom white-bg">
				<nav class="navbar navbar-static-top" role="navigation">
					<div class="navbar-header">
						<button aria-controls="navbar" aria-expanded="false"
							data-target="#navbar" data-toggle="collapse"
							class="navbar-toggle collapsed" type="button">
							<i class="fa fa-reorder"></i>
						</button>
						<a href="./home" class="navbar-brand">DIMAR</a>
					</div>
					<div class="navbar-collapse collapse" id="navbar">
						<ul class="nav navbar-nav">
							<li class="active"><a aria-expanded="false" role="button"
								href="./home"> Home </a></li>
							<li><a aria-expanded="false" role="button"
								href="./distribucion"> Distribucion </a></li>
								<li><a aria-expanded="false" role="button"
								href="./mapa"> Mapa </a></li>
						</ul>
						<ul class="nav navbar-top-links navbar-right">
							<li><a href="login.html"> <i class="fa fa-sign-out"></i>
									Log out
							</a></li>
						</ul>
					</div>
				</nav>
			</div>
			<div id="body">
				<jsp:doBody />
			</div>
			<br />
			<div id="page-footer">
				<div class="footer">
					<div class="pull-right">Distribucion de Insumos Medicos</div>
					<div>
						<strong>DIMAR</strong> - UNLaM - 2020
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
<script src="js/template/plugins/dataTables/datatables.min.js"></script>
<script src="js/template/app/inspinia.js"></script>

<!-- Gráficos -->
<script src="js/chartist/chartist.js"></script>
</html>