const url = "http://localhost:8080/servicio";

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
                <td>${producto.precio}</td>
                <td>${producto.cantidad}</td>
                <td>${producto.estado}</td>
                
                <td>
                
                    <button class="btn-editar"
                        onclick="editarProducto(${producto.id})">
                        Editar
                    </button>
                    
                    <button class="btn-eliminar"
                        onclick="eliminarProducto(${producto.id})">
                        Eliminar
                    </button>
                    
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

    try {

        // SI EXISTE ID -> ACTUALIZA
        if (producto.id) {

            await fetch(`${url}/actualizar`, {

                method: "PUT",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(producto)
            });

        }

        // SI NO EXISTE -> GUARDA
        else {

            await fetch(`${url}/guardar`, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(producto)
            });
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

// EDITAR PRODUCTO
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

    } catch (error) {

        console.error(
            "Error al editar producto",
            error
        );
    }
}

// ELIMINAR PRODUCTO
async function eliminarProducto(id) {

    try {

        await fetch(`${url}/eliminar/${id}`, {

            method: "DELETE"
        });

        listarProductos();

    } catch (error) {

        console.error(
            "Error al eliminar producto",
            error
        );
    }
}

// LIMPIAR FORMULARIO
function limpiarFormulario() {

    document.getElementById("id").value = "";

    document.getElementById("codigo").value = "";

    document.getElementById("nombre").value = "";

    document.getElementById("descripcion").value = "";

    document.getElementById("precio").value = "";

    document.getElementById("cantidad").value = "";

    document.getElementById("estado").value = "Activo";
}

// CARGAR PRODUCTOS AL INICIAR
listarProductos();