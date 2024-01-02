import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LevelsComponent } from './components/levels/levels.component';
import { StudentsComponent } from './components/students/students.component';
import { QuestionsComponent } from './components/questions/questions.component';
import { ResponsesComponent } from './components/responses/responses.component';
import { ValidationsComponent } from './components/validations/validations.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { HttpClientModule } from '@angular/common/http';
import {NavbarComponent} from "./components/navbar/navbar.component";
import { HeaderComponent } from './components/header/header.component';
import { SideNavComponent } from './components/side-nav/side-nav.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { SubjectsComponent } from './components/subjects/subjects.component';
import {CreateQuizComponent} from "./components/createquiz/create-quiz.component";
import { MediaComponent } from './components/media/media.component';
import { KoatchComponent } from './components/koatch/koatch.component';
import { FormQuizComponent } from './components/form-quiz/form-quiz.component';

@NgModule({
  declarations: [
    AppComponent,
    LevelsComponent,
    StudentsComponent,
    QuestionsComponent,
    ResponsesComponent,
    ValidationsComponent,
    NavbarComponent,
    HeaderComponent,
    SideNavComponent,
    HomeComponent,
    FooterComponent,
    SubjectsComponent,
    CreateQuizComponent,
    MediaComponent,
    KoatchComponent,
    FormQuizComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
