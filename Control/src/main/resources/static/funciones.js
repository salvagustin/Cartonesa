function eliminar(id, redirectInfo, description) {
	swal({
		title: "Estas Seguro de Eliminar el registro con id: "+id+" ?",
		text: "Una vez eliminado el resgistro "+description+" no sera posible recuperarlo",
		icon: "warning",
		buttons: true,
		dangerMode: false,
	})
		.then((OK) => {
			if (OK) {
			location.href="/eliminar"+redirectInfo+"/"+id;
			} else {
				swal("Su registro No fue Eliminado");
			}
		});
}

//FUNCION QUE VALIDA Y NOS REDIRECCIONA AL METODO CAMBIO DE ESTADO
function cambiarestado(){
	var valor = document.getElementsByName('ordenacerrar')[0].value;
	var newestado = document.getElementsByName('newestado')[0].value;
	
	if(valor.length < 10){
		if( valor == null || valor.length == 0 || /^\s+$/.test(valor) ) {
 	 	swal({
			title: "Campo vacio",
			text: "Ingrese un valor para buscar.",
			timer: 3000,
			showConfirmButton: false});
	}else{
		 location.href="/cambioestado/"+valor+"/"+newestado;
		
	}
	}else{
		swal({
			title: "Error de busqueda",
			text: "Se supero el limite de 10 caracteres.",
			timer: 3000,
			showConfirmButton: false});
			
	}
		
}

//FUNCION QUE VALIDA Y NOS REDIRECCIONA AL METODO DE EDITAR UN REGISTRO OT
function editarorden(){
	var valor = document.getElementsByName('ordenacerrar')[0].value;
	
	if(valor.length < 10){
		if( valor == null || valor.length == 0 || /^\s+$/.test(valor) ) {
 	 	swal({
			title: "Campo vacio",
			text: "Ingrese un valor para buscar.",
			timer: 3000,
			showConfirmButton: false});
	}else{
		 location.href="/editarordent/"+valor;
		
	}
	}else{
		swal({
			title: "Error de busqueda",
			text: "Se supero el limite de 10 caracteres.",
			timer: 3000,
			showConfirmButton: false});
			
	}
		
}


/* FUNCION QUE VALIDA Y SELECCIONA EL CAMPO A BUSCAR */
function validar() {	
	
	var selected = document.querySelector('input[type=radio][name=findBy]:checked');
	if(selected == null){
		selected = document.getElementsByName('findBy')[0];
	}
	var palabra = document.getElementsByName('buscar')[0].value;
	if( palabra == null || palabra.length == 0 || /^\s+$/.test(palabra) ) {
 		swal({
			title: "Campo vacio",
			text: "Ingrese un valor para buscar.",
			timer: 3000,
			showConfirmButton: false});
	}else{
		if(palabra.length<20){
			location.href="/findBy"+selected.value+"/"+palabra;
		}else{
				swal({
			title: "Error de busqueda",
			text: "Se supero el limite de 10 caracteres.",
			timer: 3000,
			showConfirmButton: false});
		}
		
	}
}	

function redirect(redirect){
	location.href="/"+redirect;
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




//FUNCION PARA BUSCAR REGISTROS OT PENDIENTES POR AREA
function buscarporarea(){
	
	var idarea = document.getElementsByName('areas')[0].value;
	location.href="/pndtArea/"+idarea;
}

//FUNCION PARA EXPORTAR REGISTROS OT PENDIENTES POR MAQUINA
function expPdntMaquina(){
	var idmaq = document.getElementsByName('maquinas')[0].value;
	location.href="/pndtMaquina/"+idmaq;
	
}


//FUNCION PARA EXPORTAR REGISTROS OT PENDIENTES POR AREA Y MAQUINA
function exptPdnt(){
	var idarea = document.getElementById("areas").value;
	var idmaq = document.getElementById('maquinas').value;
	
	if(idarea!=0){
		if (idmaq!=0){
			location.href="/exportPndntMaquina/"+idmaq;
		}else{
			location.href="/exportPndntArea/"+idarea;
		}
	}else{
		location.href="/exportPndnts";
	}
}


//FUNCION PARA EXPORTAR REGISTROS OT PROGRAMADOS POR AREA Y MAQUINA
function exptProgra(){
	var idarea = document.getElementById("areas").value;
	var idmaq = document.getElementById('maquinas').value;
	
	if(idarea!=0){
		if (idmaq!=0){
			location.href="/exportPrograMaquina/"+idmaq;
		}else{
			location.href="/exportPrograArea/"+idarea;
		}
	}else{
		location.href="/exportProgra";
	}
}

//FUNCION PARA BUSCAR REGISTROS OT PENDIENTES POR AREA Y MAQUINA
function buscarOTPndt(){
	var idarea = document.getElementById("areas").value;
	var idmaq = document.getElementById('maquinas').value;
	
	if(idarea!=0){
		if (idmaq!=0){
			location.href="/pndtMaquina/"+idmaq;
		}else{
			location.href="/pndtArea/"+idarea;
		}
	}else{
		location.href="/listarOTPndt";
	}
}
//FUNCION PARA BUSCAR REGISTROS OT PROGRAMADAS POR AREA Y MAQUINA
function buscarOTProgra(){
	var idarea = document.getElementById("areas").value;
	var idmaq = document.getElementById('maquinas').value;
	
	if(idarea!=0){
		if (idmaq!=0){
			location.href="/prograMaqina/"+idmaq;
		}else{
			location.href="/prograArea/"+idarea;
		}
	}else{
		location.href="/listarOTProgra";
	}
}



//FUNCION PARA EXPORTAR A PDF REGISTROS OT SELECCIONADOS
function exportarOT(){
	
var selected = [];

    for (var option of document.getElementById('ordent').options) {
				
        if (option.selected) {
            selected.push(option.value);  
        }
        if (selected.length === 0) { 
			swal({
			title: "Error de exportación",
			text: "Seleccione un registro del listado.",
			timer: 3000,
			showConfirmButton: false});
		}else{
       		location.href="/ExportarOT/"+selected;
    }}
    
}

