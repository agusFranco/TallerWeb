$(document).ready(function() {
	
	 console.log(responseObject);
	
	  $.ajax({
		  type : "GET",
		  contentType : "application/json",
		  url: "./getString", 
		  success: function(result) {
			  console.log(result);
		  },
		  error : function(e) {
			  console.log("ERROR: ", e);
		  }
	  });

	$('.grilla-dataTable').DataTable({
		pageLength : 10,
		responsive : true,
		dom : '<"html5buttons"B>lTfgitp',
		buttons : [],
		language : lenguaje
	});

	jQuery(function($) {
		$('.tablez').footable();
	});
	
	var establecimientos = [
	      [-34.6583809,-58.5238157],
	      [-35.1115177,-63.8804189],
	      [-36.2635267,-48.8411682],
	      [-40.0377772,-58.5294707]
	    ];
	
	var imgValues = {
			'0':'def',
			'1':'def',
			'2':'def',
			'3':'def',
	}
	
	
	var zonasValues = {
	          "AR-Z": 'Nivel1',
	          "AR-B": 'Nivel2',
	          "AR-M": 'Nivel3',
	          "AR-N": 'Nivel4'
	}
	
	$(function(){
		  new jvm.Map(
		   {
		    container: $('#map'),
		    map: 'ar_mill',
		    markers: establecimientos,
		    series: {
		      markers: [{
		        attribute: 'fill',
		        scale: ['#000000', '#000000'],
		        normalizeFunction: 'polynomial'
		      },{
		        attribute: 'image',
		        scale: {
		          def: './img/hospital2.png'
		        },
		        values: imgValues
		      }],
		      regions: [{
		        scale: {
		          Nivel1: '#ffffff',
		          Nivel2: '#ff7b5a',
		          Nivel3: '#ff5232',
		          Nivel4: '#ff0000'
		        },
		        attribute: 'fill',
		        values: zonasValues,
		        legend: {
		        	vertical:true,
		          horizontal: true,
		          title: 'Riesgo por zona'
		        }
		      }]
		    },
	      onMarkerTipShow: function(event, label, index){
	          label.html(
	            '<b>aa</b><br/>'+
	            '<b>Population: </b>dasda</br>'+
	            '<b>Unemployment rate: </b>asd'
	          );
	        },
        onRegionTipShow: function(event, label, code){
        	  label.html(
        	    '<b>'+label.html()+'</b></br>'+'<b>Unemployment rate: </b>'+data.states[val][code]+'%'
        	  );
        	}
		  });
		});
	
});

