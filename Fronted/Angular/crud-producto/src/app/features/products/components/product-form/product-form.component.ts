import { Component } from '@angular/core';

import { ProductService } from '../../../../core/services/product.service';

import { Product } from '../../../../models/product.model';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent {

  nombre: string = '';

  precio: number = 0;

  constructor(
    private productService: ProductService
  ) {}

  crearProducto(): void {

    const nuevoProducto: Product = {

      id: Date.now(), // id unico.

      nombre: this.nombre,

      precio: this.precio

    };

    this.productService.agregarProducto(
      nuevoProducto
    );

    this.nombre = '';

    this.precio = 0;

  }

}