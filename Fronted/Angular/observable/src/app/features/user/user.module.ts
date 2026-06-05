import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserCardComponent } from './components/user-card/user-card.component';
import { UserListComponent } from './pages/user-list/user-list.component';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    UserCardComponent,
    UserListComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  exports:[UserListComponent],
})
export class UserModule {

 }
