import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserCardComponent } from './components/user-card/user-card.component';
import { UserListComponent } from './page/user-list/user-list.component';



@NgModule({
  declarations: [
    UserCardComponent,
    UserListComponent
  ],
  imports: [
    CommonModule
  ]
})
export class UserModule { }
