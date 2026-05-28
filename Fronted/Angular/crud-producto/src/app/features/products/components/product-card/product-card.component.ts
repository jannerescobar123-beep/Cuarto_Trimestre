import { Component, Input } from '@angular/core';
import { Product } from '../../../../models/product.model';

import { ProductService } from '../../../../core/services/product.service';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.scss'
})
export class ProductCardComponent {
  @Input() 
  producto!: Product;

  constructor(
    private productService: ProductService
  ) { }

  eliminarProducto(): void {

    this.productService.eliminarProducto(
      this.producto.id
    );

  }
}
