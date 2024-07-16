import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Mascota } from '../menu/Mascotas';

@Injectable({
  providedIn: 'root'
})
export class MascotaServicioService {

  private apiUrl = 'http://localhost:8080/mascotas/rest/mascotas'; 

  constructor(private http: HttpClient) {}

  getMascotas(): Observable<Mascota[]> {
    return this.http.get<Mascota[]>(this.apiUrl + '/list');
  }

  getMascota(id: number): Observable<Mascota> {
    return this.http.get<Mascota>(`${this.apiUrl}/${id}`);
  }

  addMascota(mascota: Mascota): Observable<Mascota> {
    return this.http.post<Mascota>(this.apiUrl, mascota);
  }

  updateMascota(mascota: Mascota): Observable<Mascota> {
    return this.http.put<Mascota>(`${this.apiUrl}`, mascota);
  }

  deleteMascota(id: number | undefined): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}?id=${id}`);
  }
}