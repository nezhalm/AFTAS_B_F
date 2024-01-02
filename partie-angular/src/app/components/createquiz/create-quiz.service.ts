import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {CreateQuiz} from "./create-quiz";

@Injectable({
  providedIn: 'root'
})
export class CreateQuizService {

  private apiUrl = 'http://localhost:8080/api/quizzes'
  constructor(private http: HttpClient) { }


  getQuizzes(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  addQuizze(quiz: CreateQuiz): Observable<any> {
    return this.http.post(this.apiUrl, quiz);
  }


}
