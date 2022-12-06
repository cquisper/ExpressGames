window.onload = function () {
    interaccionUX();
    addEventListeners();

};

function interaccionUX() {
    //Que se meustre el login
    mostrarSeccionActual();
    //resalta el formulario actual en donde esta el usuario
    cambiarSeccion();
}

//variables:
let formulario = 1;
//constantes
const barra_registro = document.querySelector(".barra_login");
const formulario_ingresar = document.querySelector("#ingresar_login");
const formulario_registro = document.querySelector("#registro_login")

//Boton de ingresar
const btn_ingresar = document.querySelector(".contenedor_login [value=ingresar]");
const btn_registro = document.querySelector(".form-registro [value=crear_cuenta]");



function addEventListeners() {
    btn_ingresar.addEventListener("click", ingresarCuenta);
    btn_registro.addEventListener("click", registrarCuenta);
}

function ingresarCuenta(e) {
    e.preventDefault();
    const usuario_email = document.querySelector("#usario_email_login").value;
    const contraseña = document.querySelector("#contraseña_login").value;


    if (usuario_email == "" || contraseña == "") {
        //* error => X y success => Palomita 👍
        mostrarMensaje("error", "Falta rellenar los campos restantes🤔", "Verifica que todos los campos esten completos o recarge la pagina")
        return;
    } else {
        //! Despoues de consultar si estan o no vacios

        //* consultar si existe usuario, luego dentro de la condigicon ver si la contraseña es correcta
    }
    mostrarMensaje("success", "Has ingresado con exito 👏", "Preparate para adentrarte al mundo del Gaming");
    //Colocar al final si todo sale bien, se encarga de limpiar el formulario
    setTimeout(() => {
        //e.target.parentNode.submit()
    }, 3000);
    //e.target.parentNode.reset();
    formulario_ingresar.submit();
}

function mostrarMensaje(tipo, titulo, descripcion) {
    swal({
        type: tipo,
        title: titulo,
        text: descripcion,
    });
    console.log("Me llamaron");
}

function registrarCuenta(e) {
    e.preventDefault();
    const nombre = document.querySelector("#nombre").value;
    const apellidos = document.querySelector("#apellidos").value;
    const dni = document.querySelector("#dni").value;

    //Validar un raio buton,
    const usuario = document.querySelector("#usuario").value;
    const email = document.querySelector("#e-mail").value;
    const password = document.querySelector("#password").value;
    const confirm_pasword = document.querySelector("#confirmar_password").value;

    //para saber si todos los campos estas completos
    if (nombre == "" || apellidos == "" || dni == ""|| usuario == "" || email == "" || password == "" || confirm_pasword == "") {
        mostrarMensaje("error", "Falta rellenar los campos restantes🤔", "Verifica que todos los campos esten completos o recarge la pagina");
        return;
    } else {
        //arreglo de errores
        //expreciones  regulares
        let dni_reg = /^\d{0,9}$/;
        let email_reg = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
        if ((parseInt(nombre.length) < 4)) {
            mostrarMensaje("error", "El nombre debe ser mayor de 3 letras ", "Asegurate de ingresar los datos correctamente");
            return;
        }
        if (apellidos.length < 4) {
            mostrarMensaje("error", "El apellido debe ser mayor de 3 letras ", "Asegurate de ingresar los datos correctamente");
            return;
        }
        if (dni.length > 0 && dni.length == 8) {
            if (dni_reg.test(dni)) {
                //! Aqui chucpar el valor y meterlo en un arreglo
                console.log("Soy un DNI valido ")
            } else {
                mostrarMensaje("error", "El DNI contiene Letras", "Asegurate de ingresar los datos correctamente");
                return;
            }
        } else {
            mostrarMensaje("error", "El DNI debe contener 8 numeros", "Asegurate de ingresar los datos correctamente");
            return;
        }

        if (!email_reg.test(email)) {
            mostrarMensaje("error", "El email ingresado no es valido", "Asegurate de ingresar los datos correctamente");
            return;
        }


        if (usuario.length < 4) {
            mostrarMensaje("error", "El campo de Usuario debe ser mayor de 3 letras ", "Asegurate de ingresar los datos correctamente");
            return;

        }
        //! Verifica el campo de la contraseña y confirmacion seean mayores a 8 digitos y sean ifuales
        if (password.length <= 8) {
            mostrarMensaje("error", "Su contraseña debe contar con minimo 8 letras, caracteres o numeros", "Asegurate de ingresar los datos correctamente");
            return;

        } else {
            if (confirm_pasword.length > 8 && confirm_pasword != password) {
                mostrarMensaje("error", "Su contraseñas no son iguales", "Asegurate de ingresar los datos correctamente");
                return;
            } else {
                //! (Creo que debemso emeterlo todo en un arreglo )
            }
        }
        //* YA TODOS LOS DATOS ESTARAN VALIDADOS Y LISTOS PARA ENVIAR POR POST O GET * */
        //Cuando realmente  todo esta bien
        //! Si todos los campos estas rellenos :D muestra mensaje
        mostrarMensaje("success", "Te has registrado con exito 😋", "Seras nuevamente redirigido(a) para que inicies sesion");
        formulario_registro.submit();
    }
}

function mostrarSeccionActual() {
    const seccionActual = document.querySelector(`#paso-${formulario}`);
    seccionActual.classList.add("mostrar-formulario");
    //resalta el tipo de login -> button
    const tab = document.querySelector(`[data-paso='${formulario}']`);
    tab.classList.add("seleccionado");
}

function cambiarSeccion() {
    //no event lisenert en una seleccion multiple
    const enlaces = document.querySelectorAll(".barra_login button");
    //itera sobre todos elementos seleccionados y determinamos el formulariodo
    enlaces.forEach(enlace => {
        enlace.addEventListener("click", e => {
            e.preventDefault();
            //color negro string -> azul numero
            formulario = parseInt(e.target.dataset.paso);
            //eliminar mostrar-formulario del domulario anterior
            document.querySelector(".mostrar-formulario").classList.remove("mostrar-formulario");
            //eliminar la barrita que muestra seleccion
            document.querySelector(`.seleccionado`).classList.remove("seleccionado");
            const seccion = document.querySelector(`#paso-${formulario}`);
            seccion.classList.add("mostrar-formulario");
            mostrarSeccionActual();
        });
    });
}