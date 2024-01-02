
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Validations } from './validations';

@Injectable({
    providedIn: 'root'
})
export class ValidationsService{

    private apiUrl = 'http://localhost:8080/api/validations'
    constructor(private http: HttpClient) { }


    getValidations(): Observable<any> {
        return this.http.get<any>(this.apiUrl);
    }

    addValidation(validation: Validations): Observable<any> {
        return this.http.post(this.apiUrl, validation);
    }

    // updateValidation(validation: Validations): Observable<any> {
    //     return this.http.put(`${this.apiUrl}/${validation.id}`, validation);
    // }
    //
    // deleteValidation(subjectId: number): Observable<any> {
    //     const url = `http://localhost:8080/api/subjects/${subjectId}`;
    //     return this.http.delete(url);
    // }
}
