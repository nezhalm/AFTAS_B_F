import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Koatch} from "./kaotch";

@Injectable({
    providedIn: 'root'
})
export class KaotchService {

    private apiUrl = 'http://localhost:8080/api/trainers'
    constructor(private http: HttpClient) { }


    getKaotchs(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/api/quizzes');
    }

    addKaotch(level: Koatch): Observable<any> {
        return this.http.post(this.apiUrl, level);
    }

    updateKaotch(koatch: Koatch): Observable<any> {
        return this.http.put(`${this.apiUrl}/${koatch.id}`, koatch);
    }

    deleteKaotch(koatchId: number): Observable<any> {
        const url = `http://localhost:8080/api/trainers/${koatchId}`;
        return this.http.delete(url);
    }
}
