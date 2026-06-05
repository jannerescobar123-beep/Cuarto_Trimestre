import { Injectable } from '@angular/core';

import { Product } from '../../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productos: Product[] = [];

  constructor() { }

  obtenerProductos(): Product[] {

    return this.productos;

  }

  agregarProducto(producto: Product): void {

    this.productos.push(producto);

  }

  eliminarProducto(id: number): void {

    const indice = this.productos.findIndex(
      producto => producto.id === id
    );

    this.productos.splice(indice, 1);

  }

}