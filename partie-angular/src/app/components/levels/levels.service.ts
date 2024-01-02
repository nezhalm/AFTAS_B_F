// import { Injectable } from '@angular/core';
// import {catchError, Observable} from "rxjs";
// import {HttpClient} from "@angular/common/http";
// import {Levels} from "./levels";
//
// @Injectable({
//   providedIn: 'root',
// })
// export class LevelsService {
//   private apiUrl = 'http://localhost:8080/api/levels'; // Remplacez avec l'URL de votre API
//
//   constructor(private http: HttpClient) {}
//
//   getLevels(): Observable<any> {
//     return this.http.get<any>('http://localhost:8080/api/levels');
//   }
//
//   // public addLevel(level:Levels):Observable<any>{
//   //   return this.http.post<Levels>(`${this.apiServerUrl}\levels`,level);
//   // // }
//   // addLevel(newLevel: Levels): Observable<{ message: string }> {
//   //   return this.http.post<{ message: string }>(this.apiUrl, newLevel);
//   // }
//
//   addLevel(newLevel: Levels): Observable<{ message: string }> {
//     return this.http.post<{ message: string }>(this.apiUrl, newLevel).pipe(
//       catchError((error: any) => {
//         console.error('Erreur lors de la requête POST:', error);
//         throw error;  // Vous pouvez personnaliser la gestion des erreurs ici
//       })
//     );
//   }
//
//
//   // public updateLevels(level:Levels):Observable<Levels>{
//   //   return this.http.put<Levels>(`${this.apiServerUrl}\levels`,level);
//   // }
//
//
//   deleteLevel(levelId: number): Observable<any> {
//     const url = `http://localhost:8080/api/levels/${levelId}`;
//     return this.http.delete(url);
//   }
// }
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Levels} from "./levels";

@Injectable({
  providedIn: 'root'
})
export class LevelService {

  private apiUrl = 'http://localhost:8080/api/levels'
  constructor(private http: HttpClient) { }


    getLevels(): Observable<any> {
    return this.http.get<any>('http://localhost:8080/api/levels');
    }

  addLevel(level: Levels): Observable<any> {
    return this.http.post(this.apiUrl, level);
  }

  updateLevel(level: Levels): Observable<any> {
    return this.http.put(`${this.apiUrl}/${level.id}`, level);
  }

  deleteLevel(levelId: number): Observable<any> {
    const url = `http://localhost:8080/api/levels/${levelId}`;
    return this.http.delete(url);
  }
}
