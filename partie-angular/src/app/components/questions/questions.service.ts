
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Questions} from "./questions";

@Injectable({
  providedIn: 'root'
})
export class QuestionsService{

  private apiUrl = 'http://localhost:8080/api/questions'
  constructor(private http: HttpClient) { }


  getQuestions(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  addQuestion(question: Questions): Observable<any> {
    return this.http.post(this.apiUrl, question);
  }

  updateQuestion(question: Questions): Observable<any> {
    return this.http.put(`${this.apiUrl}/${question.id}`, question);
  }

  deleteQuestion(questionId: number): Observable<any> {
    const url = `${this.apiUrl}/${questionId}`;
    return this.http.delete(url);
  }

}
