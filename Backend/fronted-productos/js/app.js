const url = "https://productos-production-7faf.up.railway.app/servicio";

// LISTAR PRODUCTOS
async function listarProductos() {

    try {

        const respuesta =
            await fetch(`${url}/productos`);

        const productos =
            await respuesta.json();

        let tabla = "";

        productos.forEach(producto => {

            tabla += `
            
            <tr>
            
                <td>${producto.id}</td>

                <td>${producto.codigo}</td>

                <td>${producto.nombre}</td>

                <td>${producto.descripcion}</td>

                <td>$ ${producto.precio}</td>

                <td>${producto.cantidad}</td>

                <td>${producto.estado}</td>
                
                <td>

                    <div class="acciones">
                
                        <button class="btn-editar"
                            onclick="editarProducto(${producto.id})">

                            Editar

                        </button>
                    
                        <button class="btn-eliminar"
                            onclick="eliminarProducto(${producto.id})">

                            Eliminar

                        </button>

                    </div>
                    
                </td>
                
            </tr>
            `;
        });

        document.getElementById("tablaProductos")
            .innerHTML = tabla;

    } catch (error) {

        console.error(
            "Error al listar productos",
            error
        );
    }
}

// GUARDAR PRODUCTO
async function guardarProducto() {

    const producto = {

        id:
            document.getElementById("id").value,

        codigo:
            document.getElementById("codigo").value,

        nombre:
            document.getElementById("nombre").value,

        descripcion:
            document.getElementById("descripcion").value,

        precio:
            document.getElementById("precio").value,

        cantidad:
            document.getElementById("cantidad").value,

        estado:
            document.getElementById("estado").value
    };

    // VALIDACION SIMPLE
    if (producto.nombre === "") {

        alert("El nombre es obligatorio");

        return;
    }

    const mensaje =
        document.getElementById("mensajeCodigo")
            .innerText;

    if (mensaje !== "") {

        alert("El código ya existe");

        return;
    }

    try {

        // ACT
        if (producto.id) {

            await fetch(`${url}/actualizar`, {

                method: "PUT",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(producto)
            });

            alert("Producto actualizado correctamente");
        }

        // GUARD
        else {

            await fetch(`${url}/guardar`, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(producto)
            });

            alert("Producto guardado correctamente");
        }

        limpiarFormulario();

        listarProductos();

    } catch (error) {

        console.error(
            "Error al guardar producto",
            error
        );
    }
}

// EDITAR P
async function editarProducto(id) {

    try {

        const respuesta =
            await fetch(`${url}/productos/${id}`);

        const producto =
            await respuesta.json();

        document.getElementById("id").value =
            producto.id;

        document.getElementById("codigo").value =
            producto.codigo;

        document.getElementById("nombre").value =
            producto.nombre;

        document.getElementById("descripcion").value =
            producto.descripcion;

        document.getElementById("precio").value =
            producto.precio;

        document.getElementById("cantidad").value =
            producto.cantidad;

        document.getElementById("estado").value =
            producto.estado;

        document.querySelector(".formulario button")
            .innerText = "Actualizar Producto";

    } catch (error) {

        console.error(
            "Error al editar producto",
            error
        );
    }
}

// ELIMINAR P
async function eliminarProducto(id) {

    const confirmar =
        confirm("¿Desea eliminar este producto?");

    if (!confirmar) {

        return;
    }

    try {

        await fetch(`${url}/eliminar/${id}`, {

            method: "DELETE"
        });

        alert("Producto eliminado correctamente");

        listarProductos();

    } catch (error) {

        console.error(
            "Error al eliminar producto",
            error
        );
    }
}

// LIMPIAR 
function limpiarFormulario() {

    document.getElementById("id").value = "";

    document.getElementById("codigo").value = "";

    document.getElementById("nombre").value = "";

    document.getElementById("descripcion").value = "";

    document.getElementById("precio").value = "";

    document.getElementById("cantidad").value = "";

    document.getElementById("estado").value = "Activo";

    document.querySelector(".formulario button")
        .innerText = "Guardar Producto";
}

// VALIDAR CODIGO
async function validarCodigo() {

    const codigo =
        document.getElementById("codigo").value;

    // SI ESTA VACIO
    if (codigo === "") {

        return;
    }

    try {

        const respuesta =
            await fetch(
                `${url}/validar-codigo?codigo=${codigo}`
            );

        const existe =
            await respuesta.json();

        const inputCodigo =
            document.getElementById("codigo");

        const mensaje =
            document.getElementById("mensajeCodigo");

        // SI EXISTE
        if (existe) {

            inputCodigo.classList.add("error-input");

            mensaje.innerText =
                "Este código ya existe";

        }

        // SI NO EXISTE
        else {

            inputCodigo.classList.remove("error-input");

            mensaje.innerText = "";
        }

    } catch (error) {

        console.error(
            "Error al validar código",
            error
        );
    }
}

// CARGAR 
listarProductos();

