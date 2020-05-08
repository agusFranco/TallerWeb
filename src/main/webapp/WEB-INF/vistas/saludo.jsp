<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<t:layout>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-lg-12">
				<div class="text-center m-t-lg">
					<h1>SALUDO</h1>
					<small>Hola ${nombre} ${apellido}</small>
				</div>
			</div>
		</div>
	</div>
</t:layout>

