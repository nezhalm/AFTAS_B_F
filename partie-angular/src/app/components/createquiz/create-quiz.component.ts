import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {CreateQuizService} from "./create-quiz.service";
import {CreateQuiz} from "./create-quiz";

@Component({
  selector: 'app-create-quiz',
  templateUrl: './create-quiz.component.html',
  styleUrls: ['./create-quiz.component.css']
})
export class CreateQuizComponent implements OnInit  {
  quizzes : CreateQuiz[] =[];
  constructor(private quizService : CreateQuizService) {}
  ngOnInit(): void {
    this.loadQuizs();
  }

  loadQuizs() {
    this.quizService.getQuizzes().subscribe(
      (response: any) => {
        console.log('API Response:', response);
        this.quizzes = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
