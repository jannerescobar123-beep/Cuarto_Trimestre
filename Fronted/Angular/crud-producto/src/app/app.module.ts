import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';

import { ProductFormComponent } from './features/products/components/product-form/product-form.component';

import { ProductListComponent } from './features/products/components/product-list/product-list.component';

import { ProductCardComponent } from './features/products/components/product-card/product-card.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductFormComponent,
    ProductListComponent,
    ProductCardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }