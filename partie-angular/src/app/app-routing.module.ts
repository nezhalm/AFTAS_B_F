import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LevelsComponent} from "./components/levels/levels.component";
import {HomeComponent} from "./components/home/home.component";
import {QuestionsComponent} from "./components/questions/questions.component";
import {ResponsesComponent} from "./components/responses/responses.component";
import {SubjectsComponent} from "./components/subjects/subjects.component";
import {CreateQuizComponent} from "./components/createquiz/create-quiz.component";
import {KoatchComponent} from "./components/koatch/koatch.component";
import {FormQuizComponent} from "./components/form-quiz/form-quiz.component";

const routes: Routes = [
  {path: 'levels', component: LevelsComponent},
  {path: '', component: HomeComponent},
  {path: 'questions', component: QuestionsComponent},
  {path: 'responses', component: ResponsesComponent},
  {path: 'subjects', component: SubjectsComponent},
  {path: 'quiz', component: CreateQuizComponent},
  {path: 'koatch', component: KoatchComponent},
  {path: 'formQuiz', component: FormQuizComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
