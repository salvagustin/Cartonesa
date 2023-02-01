/* FUNCION PARA AFIRMAR LA ELIMINACION DE REGISTROS */
function eliminar(id, redirectInfo, description) {
	swal({
		title: "Estas Seguro de Eliminar el registro con id: "+id+" ?",
		text: "Una vez eliminado el resgistro "+description+" no sera posible recuperarlo",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((OK) => {
			if (OK) {
				$.ajax({
					url:"/eliminar"+redirectInfo+"/"+id,
					success: function(res){
						console.log(res);
					}
				});
				swal("Su registro "+description+" fue eliminado satisfactoriamente", {
					icon: "success",
				}).then((OK)=>{
					if(OK){
						location.href="/listar"+redirectInfo
					}
				});
			} else {
				swal("Su registro No fue Eliminado");
			}
		});
}

/* FUNCION QUE SELECCIONA EL CAMPO A BUSCAR */
function buscar() {	
	var selected = document.querySelector('input[type=radio][name=findBy]:checked');
	if(selected == null){
		selected = document.getElementsByName('findBy')[0];
	}
	var palabra = document.getElementsByName('buscar')[0].value;
	location.href="/findBy"+selected.value+"/"+palabra;
}	
function redirect(redirect){
	location.href="/"+redirect;
}

/* FUNCION PARA BUSCAR UNA ORDEN PARA CERRARLA */
function cerrarorden(){
	
	var idord = document.getElementsByName('ordenacerrar')[0].value;
	location.href="/findByIdordentrab"+"/"+idord;
	
	
	
	
}


/* FUNCION QUE CONSULTA LA HORA ACTUAL Y LE DA FORMATO */
showTime();
function showTime(){
myDate = new Date();
hours = myDate.getHours();
minutes = myDate.getMinutes();
seconds = myDate.getSeconds();
year = myDate.getFullYear();
month = myDate.getMonth() + 1;
day = myDate.getDate();

if (month < 10) month = "0" + month;
if (day < 10) day = "0" + day;
if (hours < 10) hours = "0" + hours;
if (minutes < 10) minutes = "0" + minutes;
if (seconds < 10) seconds = "0" + seconds;

$("#HoraActual").text(day + "/" + month + "/" + year + " " + hours+ ":" +minutes+ ":" +seconds);
setTimeout("showTime()", 1000);
}




/* FUNCION PARA ABRIR Y CERRAR VENTANA PARA CERRAR ORDENES DE TRABAJO */
function abrir(){
	document.getElementById("vent").style.display="block";	
}
function cerrar(){
	document.getElementById("vent").style.display="none";
	
}

