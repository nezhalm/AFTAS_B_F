
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Subjects} from "./subjects";

@Injectable({
    providedIn: 'root'
})
export class SubjectsService{

    private apiUrl = 'http://localhost:8080/api/subjects'
    constructor(private http: HttpClient) { }


    getSubjects(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/api/subjects');
    }

    addSubject(subject: Subjects): Observable<any> {
        return this.http.post(this.apiUrl, subject);
    }

    updateSubject(subject: Subjects): Observable<any> {
        return this.http.put(`${this.apiUrl}/${subject.id}`, subject);
    }

    deleteSubject(subjectId: number): Observable<any> {
        const url = `http://localhost:8080/api/subjects/${subjectId}`;
        return this.http.delete(url);
    }
}
