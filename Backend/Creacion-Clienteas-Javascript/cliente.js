async function consumoApi() {

    try {

        const respuesta =
            await fetch(
                'http://localhost:8080/servicio/personas-list'
            );

        const datos =
            await respuesta.json();

        console.log(datos);

    } catch (error) {

        console.error(
            'ocurrio un error siuuuuuuuuuu',
            error
        );

    }

}

async function obtenerPersona() {

    console.log("Iniciando consulta de persona...");

    const documento =
        document.getElementById("documento").value;

    if (!documento) {

        alert("Por favor, ingresa un documento válido.");
        return;

    }

    const url =
        `http://localhost:8080/servicio/personas?id=${documento}`;

    console.log("URL solicitada:", url);

    try {

        const respuesta = await fetch(url);

        if (!respuesta.ok) {

            const mensajeError =
                await respuesta.text();

            throw new Error(
                `Error ${respuesta.status}: ${mensajeError}`
            );

        }
        // hola
        const persona =
            await respuesta.json();

        document.getElementById("nombre").value =
            persona.nombre || "";

        document.getElementById("telefono").value =
            persona.telefono || "";

        document.getElementById("edad").value =
            persona.edad || "";

        document.getElementById("profesion").value =
            persona.profesion || "";

        document.getElementById("password").value =
            persona.password || "";

        document.getElementById("tipo").value =
            persona.tipo || "";

        console.log("Persona encontrada:", persona);

    } catch (error) {

        console.error(
            "Error al obtener la persona:",
            error
        );

        alert(
            "Hubo un problema al buscar la persona: "
            + error.message
        );

    }

}
async function consultarProfesion() {
    console.log("Iniciando consulta de profesión...");
    const id = document.getElementById("documento").value;
    const profesion = document.getElementById("profesion").value;
    if (!id || !profesion) {
        alert("Por favor, ingresa un ID y una profesión válidos.");
        return;
    }
    const url = `http://localhost:8080/servicio/profesion?id=${id}&profesion=${encodeURIComponent(profesion)
        } `;
    console.log("URL solicitada:", url);
    try {
        const respuesta = await fetch(url);
        if (!respuesta.ok) {
            const mensajeError = await respuesta.text();
            throw new Error(`Error ${respuesta.status}: ${mensajeError} `);
        }
        const profesionDto = await respuesta.json();
        console.log("Profesión encontrada:", profesionDto);
        document.getElementById("nombre").value = profesionDto.nombre || "";
        document.getElementById("telefono").value = profesionDto.telefono || "";
        document.getElementById("edad").value = profesionDto.edad || "";
        document.getElementById("profesion").value = profesionDto.profesion || "";
        document.getElementById("password").value = profesionDto.password || "";
        document.getElementById("tipo").value = profesionDto.tipo || "";
    } catch (error) {
        console.error("Error al consultar la profesión:", error);
        alert("Hubo un problema al consultar la profesión: " + error.message);
    }
}
