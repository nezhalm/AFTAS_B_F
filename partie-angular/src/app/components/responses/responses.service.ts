
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Responses} from "./responses";

@Injectable({
    providedIn: 'root'
})
export class ResponsesService{

    private apiUrl = 'http://localhost:8080/api/responses'
    constructor(private http: HttpClient) { }

    getResponses(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/api/responses');
    }

    addResponse(responses: Response): Observable<any> {
        return this.http.post(this.apiUrl, responses);
    }

    updateResponse(response: Responses): Observable<any> {
        return this.http.put(`${this.apiUrl}/${response.id}`, response);
    }

    deleteResponse(responseId: number): Observable<any> {
        const url = `http://localhost:8080/api/responses/${responseId}`;
        return this.http.delete(url);
    }
}
