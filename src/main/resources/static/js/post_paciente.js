window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo paciente
    const formulario = document.querySelector('#add_new_paciente');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
       //creamos un JSON que tendrá los datos del nuevo paciente
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
			fecha_ingreso: document.querySelector('#fecha_ingreso').value,

        };
        
        const url = '/pacientes/crear';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        console.log("1-fetch: "+formData)
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
				console.log("1-fetch resp: "+data)
				// cuando resuelve el fetch anterior creando al paciente, creamos el domicilio con el id que devuelve
				const formData_2 = {
					calle: document.querySelector('#calle').value,
					numero: document.querySelector('#numero').value,
					localidad: document.querySelector('#localidad').value,
					provincia: document.querySelector('#provincia').value,
					paciente: {id: data.id}
				};
				const url_2 = '/domicilios/crear';
				const settings_2 = {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
					},
					body: JSON.stringify(formData_2)
				}
				console.log("2-fetch: "+formData_2)
				fetch(url_2, settings_2)
					.then(response_2 => response_2.json())
					.then(data_2 => {
						console.log("2-fetch resp: "+data_2)
						let successAlert = '<div class="alert alert-success alert-dismissible">' +
							'<button type="button" class="close" data-dismiss="alert">&times;</button>' +
							'<strong></strong> Paciente agregado </div>'

						document.querySelector('#response').innerHTML = successAlert;
						document.querySelector('#response').style.display = "block";
                 

						})
						.catch(error => {
								let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
												 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
												 '<strong> Error intente nuevamente</strong> </div>'
								  document.querySelector('#response').innerHTML = errorAlert;
								  document.querySelector('#response').style.display = "block";
						})

					 //Si no hay ningun error se muestra un mensaje diciendo que el paciente se agrego bien
					 let successAlert = '<div class="alert alert-success alert-dismissible">' +
						 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
						 '<strong></strong> Paciente agregado </div>'

					 document.querySelector('#response').innerHTML = successAlert;
					 document.querySelector('#response').style.display = "block";
					

            })
            .catch(error => {
				//Si hay algun error se muestra un mensaje diciendo que el paciente
				//no se pudo guardar y se intente nuevamente
				let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
								 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
								 '<strong> Error intente nuevamente</strong> </div>'

				  document.querySelector('#response').innerHTML = errorAlert;
				  document.querySelector('#response').style.display = "block";
				 //se dejan todos los campos vacíos por si se quiere ingresar otro paciente
				 resetUploadForm();})
    });


    function resetUploadForm(){
		//cambiar por funcion nativa de formulario
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#dni').value = "";
		document.querySelector('#fecha_ingreso').value = "";
		document.querySelector('#calle').value = "";
		document.querySelector('#numero').value = "";
		document.querySelector('#localidad').value = "";
		document.querySelector('#provincia').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/pacientes.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});