// 1. Importar Express
const express = require("express");
 //Importa el módulo Express que instalaste.

// 2. Crear la aplicación
const app = express();
// Crea una instancia de la aplicación web

// 3. Definir el puerto
const PORT = 3000;
//Define una ruta: cuando alguien visita '/', ejecuta esta función


// 4. Crear una ruta GET en '/'
app.get("/", (req, res) => {
  res.send("¡Hola Mundo desde Express! ■");
});//Envía texto (o HTML) como respuesta al navegador.
//El objeto 'response' se usa para enviar la respuesta.
//El objeto 'request' contiene la información de la petición.

app.get('/acerca', (req, res) => {
res.send('Soy un servidor creado con Express.');
});

app.get('/usuario/:nombre', (req, res) => {
const nombre = req.params.nombre;
res.send(`Hola, ${nombre}!`);
});


// 5. Iniciar el servidor
app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
});  //Inicia el servidor y lo pone a escuchar en el puerto 3000.
