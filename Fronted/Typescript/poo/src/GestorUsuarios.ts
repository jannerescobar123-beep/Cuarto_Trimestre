interface Usuario {
    id: number;
    nombre: string;
    correo: string;
    activo: boolean;
}
 class UsuarioService{
    usuarios :Usuario[]= [];

    crear(usuario:Usuario):void{
        this.usuarios.push(usuario)
    }
    desactivar(id:number):void{
        const usuario = this.usuarios.find(u => u.id === id);
        if(usuario){
            usuario.activo= false;
        }
    }
    ListarActivos():Usuario[]{
        return this.usuarios.filter(u => u.activo);
    }
 }
 const usuario1: Usuario = {
    id: 1,
    nombre: "Juan",
    correo: "juan@gmail.com",
    activo: true
};

const usuario2: Usuario = {
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