$(document).ready(function() {
	
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
	
	
	$(function(){
		  new jvm.Map({
		    container: $('#map'),
		    map: 'ar_mill',
		    markers: [
		      [10, 10],
		    ],
		    series: {
		      markers: [{
		        attribute: 'fill',
		        scale: ['#C8EEFF', '#0071A4'],
		        normalizeFunction: 'polynomial',
		        values: [0, 10, 100, 1000],
		        legend: {
		          vertical: true
		        }
		      },{
		        attribute: 'image',
		        scale: {
		          bank: '/img/icon-bank.png',
		          factory: '/img/icon-factory.png'
		        },
		        values: {
		          '4': 'bank',
		          '5': 'factory'
		        },
		        legend: {
		          horizontal: true,
		          cssClass: 'jvectormap-legend-icons',
		          title: 'ricardo ford'
		        }
		      }],
		      regions: [{
		        scale: {
		          caos: '#ff0000',
		          green: '#00ff00',
		          blue: '#0000ff',
		          yellow: '#ffff00'
		        },
		        attribute: 'fill',
		        values: {
		          "AR-Z": 'caos',
		          "AR-B": 'blue',
		          "AR-M": 'green',
		          "AR-N": 'yellow'
		        },
		        legend: {
		          horizontal: true,
		          title: 'Color'
		        }
		      },{
		        scale: {
		          redGreen: '/img/bg-red-green.png',
		          yellowBlue: '/img/bg-yellow-blue.png'
		        },
		        values: {
		          "US-TX": 'redGreen',
		          "US-CA": 'yellowBlue'
		        },
		        attribute: 'fill',
		        legend: {
		          horizontal: true,
		          cssClass: 'jvectormap-legend-bg',
		          title: 'Pattern',
		          labelRender: function(v){
		            return {
		              redGreen: 'low',
		              yellowBlue: 'high'
		            }[v];
		          }
		        }
		      }]
		    }
		  });
		});
	
});

