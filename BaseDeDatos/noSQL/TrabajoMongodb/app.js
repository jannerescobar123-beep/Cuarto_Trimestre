const { MongoClient } = require("mongodb");

const uri = "mongodb://localhost:27017";

async function insertarDocumento() {
  const client = new MongoClient(uri);

  try {
    await client.connect();
    console.log("Conexión exitosa a MongoDB");

    const db = client.db("biblioteca");
    const coleccion = db.collection("libros");

const resultado = await coleccion.insertMany([
  {
    _id: 1000,
    titulo: "Libro NoSQL 1",
    autor: "Janner"
  },
  {
    _id: 2000,
    titulo: "Libro NoSQL 2",
    autor: "Janner"
  },
  {
    _id: 3000,
    titulo: "Libro NoSQL 3",
    autor: "Janner"
  }
]);

console.log("Documentos insertados correctamente");
console.log(resultado.insertedIds);

  } catch (error) {
    console.error("Error al insertar:");
    console.error(error.message);
  } finally {
    await client.close();
  }
}

insertarDocumento();