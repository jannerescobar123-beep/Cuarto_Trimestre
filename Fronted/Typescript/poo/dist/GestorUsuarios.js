"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class UsuarioService {
    usuarios = [];
    crear(usuario) {
        this.usuarios.push(usuario);
    }
    desactivar(id) {
        const usuario = this.usuarios.find(u => u.id === id);
        if (usuario) {
            usuario.activo = false;
        }
    }
    ListarActivos() {
        return this.usuarios.filter(u => u.activo);
    }
}
const usuario1 = {
    id: 1,
    nombre: "Juan",
    correo: "juan@gmail.com",
    activo: true
};
const usuario2 = {
    id: 2,
    nombre: "Ana",
    correo: "ana@gmail.com",
    activo: true
};
const service = new UsuarioService();
service.crear(usuario1);
service.crear(usuario2);
console.log("Usuarios activos:");
console.log(service.ListarActivos());
service.desactivar(1);
console.log("Usuarios activos después de desactivar:");
console.log(service.ListarActivos());
