class Producto {
    id: number;
    nombre: string;
    precio: number;
    stock: number;

    constructor(id: number, nombre: string, precio: number, stock: number) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
    this.stock = stock;
}
}
class Inventario {
    productos : Producto[]=[];

    guardarProducto(producto:Producto):void{
        this.productos.push(producto);
    }
    buscarPorNombre(nombre:string):Producto |undefined {
        return  this.productos.find(producto => producto.nombre === nombre);
    }
     listarTodos(): Producto[] {
        return this.productos;
    }
    calcularValorTotal (): number{
        let total = 0;

    for (const producto of this.productos) {
        total += producto.precio * producto.stock;
    }

    return total;
    }
}
const inventario = new Inventario();

const producto1 = new Producto(1, "Teclado", 50, 10);
const producto2 = new Producto(2, "Mouse", 30, 5);
const producto3 = new Producto(3, "Monitor", 200, 2);

inventario.guardarProducto(producto1);
inventario.guardarProducto(producto2);
inventario.guardarProducto(producto3);

console.log("Lista de productos:");
console.log(inventario.listarTodos());

console.log("Buscar producto:");
console.log(inventario.buscarPorNombre("Mouse"));

console.log("Valor total:");
console.log(inventario.calcularValorTotal());