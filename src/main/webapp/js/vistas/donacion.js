var cargarDonacion = function() {
	const form = document.getElementById("formCargaDonacion");
	form.method = 'post';
	form.submit();
};

$('select.changeStatus').change(function(){
    $.ajax({
            type: 'POST',
            url: 'Change-status.php',
            data: {selectFieldValue: $('select.changeStatus').val(), projectId: $('input[name$="projectId"]').val()},
            dataType: 'html'
     });
});