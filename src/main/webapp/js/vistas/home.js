var calcularPrioridad = function() {
	const form = document.getElementById("calcularPrioridadForm");
	form.submit();
};

$(document).ready(function() {
	$('.grilla-dataTable').DataTable({
		pageLength : 10,
		responsive : true,
		dom : '<"html5buttons"B>lTfgitp',
		buttons : [],
		language : lenguaje
	});
});
