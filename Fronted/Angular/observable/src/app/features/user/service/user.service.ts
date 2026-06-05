import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  getAllUsers(): Observable<User[]> {
    {
      return this.http.get<User[]>("https://jsonplaceholder.typicode.com/users");
    }
  }
}
