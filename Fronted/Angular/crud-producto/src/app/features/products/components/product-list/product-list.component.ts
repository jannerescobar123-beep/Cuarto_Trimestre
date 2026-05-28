import { Component } from '@angular/core';
import { Product } from '../../../../models/product.model';
import { ProductService } from '../../../../core/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.scss'
})
export class ProductListComponent {
productos: Product[]= [];
constructor(private productService: ProductService){
  this.productos =this.productService.obtenerProductos();
}
}
