import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tasks } from '../models/tasks.model';

const baseUrl = 'http://localhost:8080/api/tasks';

@Injectable({
  providedIn: 'root',
})
export class TasksService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Tasks[]> {
    return this.http.get<Tasks[]>(baseUrl);
  }

  get(id: any): Observable<Tasks> {
    return this.http.get<Tasks>(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByTitle(title: any): Observable<Tasks[]> {
    return this.http.get<Tasks[]>(`${baseUrl}?title=${title}`);
  }
}
