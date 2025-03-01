import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/users';  // URL to GET user in the user microservice

  constructor(private httpClient: HttpClient) { }
// TO GET A LIST OF ALL USERS
  getUserList(): Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseUrl}`);
  }

  // TO ADD A USER TO THE DATABASE
  createUser(user: User): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}`, user)
  }

  // UPDATE USER RECORDS---call this method in the update user component
  getUserById(user_id:number): Observable<User>{
    return this.httpClient.get<User>(`${this.baseUrl}/${user_id}`)
  }
  updateUser(user_id:number, user:User):Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/${user_id}`, user)
  }

  // DELETE A USER
  deleteUser(user_id:number
    ):Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/${user_id}`)
  }
}
