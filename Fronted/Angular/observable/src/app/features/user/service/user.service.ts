import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../../../model/post.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  getAllPosts(): Observable<Post[]> {

    return this.http.get<Post[]>(
      'https://jsonplaceholder.typicode.com/posts'
    );

  }

}