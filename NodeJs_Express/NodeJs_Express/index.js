const express = require('express');
const app = express();
const PORT = 3000;

app.use(express.json());

let usuarios = [
  { id: 1, nombre: 'Juan', email: 'juan@email.com' },
  { id: 2, nombre: 'María', email: 'maria@email.com' },
];

app.get('/', (req, res) => {
  res.send('API funcionando');
});

app.get('/api/usuarios', (req, res) => {
  res.json(usuarios);
});

app.get('/api/usuarios/:id', (req, res) => {
  const usuario = usuarios.find(u => u.id === parseInt(req.params.id));
  if (!usuario) return res.status(404).json({ error: 'Usuario no encontrado' });
  res.json(usuario);
});

app.post('/api/usuarios', (req, res) => {
  const nuevo = {
    id: usuarios.length + 1,
    nombre: req.body.nombre,
    email: req.body.email,
  };
  usuarios.push(nuevo);
  res.status(201).json(nuevo);
});

app.put('/api/usuarios/:id', (req, res) => {
  const usuario = usuarios.find(u => u.id === parseInt(req.params.id));
  if (!usuario) return res.status(404).json({ error: 'Usuario no encontrado' });
  usuario.nombre = req.body.nombre || usuario.nombre;
  usuario.email = req.body.email || usuario.email;
  res.json(usuario);
});

app.delete('/api/usuarios/:id', (req, res) => {
  const index = usuarios.findIndex(u => u.id === parseInt(req.params.id));
  if (index === -1) return res.status(404).json({ error: 'Usuario no encontrado' });
  usuarios.splice(index, 1);
  res.status(204).send();
});

app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
});
