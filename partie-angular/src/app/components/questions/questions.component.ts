import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import {HttpErrorResponse} from "@angular/common/http";
import {Component, OnInit} from "@angular/core";
import {Questions} from "./questions";
import {QuestionsService} from "./questions.service";
import {LevelService} from "../levels/levels.service";
import {SubjectsService} from "../subjects/subjects.service";
import {Levels} from "../levels/levels";
import {ResponsesService} from "../responses/responses.service";
import {Validations} from "../validations/validations";
import {ValidationsService} from "../validations/validations.service";


@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {
  responses: any = {};
  questions: any = {};
  levels: any = {};
  subjects: any = {};
  questionsData: Questions[] = [];
  questionForm!: FormGroup;
  FormValidation!: FormGroup;

    operation: String = 'add';
  selectedQuestion: Questions | null = null;
  showAddForm = false;
  showValidForm = false;


    constructor(private validationService : ValidationsService,private responseService : ResponsesService,private subjectService : SubjectsService,private levelService : LevelService, private questionService: QuestionsService, private fb: FormBuilder) {
    this.createForm();
    this.createFormValidation();

    }

  ngOnInit(): void {
    this.loadQuestions();
    this.loadLevels();
    this.loadSubjects();
      this.loadResponses();

  }
    loadResponses() {
        this.responseService.getResponses().subscribe(
            (response: any) => {
                console.log('API Response:', response);
                this.responses = response;
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
            }
        );
    }

  loadLevels() {
    this.levelService.getLevels().subscribe(
        (response: any) => {
          console.log('API Response:', response);
          this.levels = response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
    );
  }

  loadSubjects() {
    this.subjectService.getSubjects().subscribe(
        (response: any) => {
          console.log('API Response:', response);
          this.subjects = response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
    );
  }
  createForm() {
    this.questionForm = this.fb.group({
                                        content: ['', Validators.required],
                                        points: ['', Validators.required],
                                        levelId: ['', Validators.required],
                                        subjectId: ['', Validators.required],
                                        numberOfResponses: ['', Validators.required],
                                        numberOfCorrectResponses: ['', Validators.required],
    });
  }

    createFormValidation() {
        this.FormValidation = this.fb.group({
            questionId: ['', Validators.required],
            responseId: ['', Validators.required],
            points: ['', Validators.required],
        });
    }

  loadQuestions() {
    this.questionService.getQuestions().subscribe(
      (response: any) => {
        console.log('API Response:', response);
        this.questions = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }



  deleteQuestion(questionId: number): void {
    Swal.fire({
      title: 'Êtes-vous sûr?',
      text: 'Vous ne pourrez pas revenir en arrière!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Oui, supprimer!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.questionService.deleteQuestion(questionId).subscribe(
          (response: { message: string }) => {
            Swal.fire(
              'Supprimé!',
              response.message,
              'success'
            );
            this.loadQuestions();
          },
          (error: HttpErrorResponse) => {
            Swal.fire(
              'Erreur!',
              error.message,
              'error'
            );
          }
        );
      }
    });
  }


  // addQuestion() {
  //   const newQuestion = this.questionForm.value as Questions;
  //   this.questionService.addQuestion(newQuestion).subscribe(
  //     res => {
  //       this.loadQuestions();
  //       this.resetForm();
  //       Swal.fire('Success', 'Question added successfully!', 'success');
  //     },
  //     error => {
  //       Swal.fire('Error', 'Failed to add Question', 'error');
  //     }
  //   )
  // }

  addQuestion() {
    const newQuestion = this.questionForm.value as Questions;
    console.log('New Question:', newQuestion);  // Ajoutez ceci pour déboguer
    this.questionService.addQuestion(newQuestion).subscribe(
        res => {
          this.loadQuestions();
          this.resetForm();
          Swal.fire('Success', 'Question added successfully!', 'success');
        },
        error => {
          console.error('Error:', error);  // Ajoutez ceci pour déboguer
          Swal.fire('Error', 'Failed to add Question', 'error');
        }
    );
  }

    addValidation() {
        const validation = this.FormValidation.value as Validations;
        console.log('New validation:', validation);  // Ajoutez ceci pour déboguer
        this.validationService.addValidation(validation).subscribe(
            res => {
                this.loadQuestions();
                this.resetForm();
                Swal.fire('Success', 'validation added successfully!', 'success');
            },
            error => {
                console.error('Error:', error);  // Ajoutez ceci pour déboguer
                Swal.fire('Error', 'Failed to add validation', 'error');
            }
        );
    }

  cancelAddOrEdit() {
    this.operation = 'add';
    this.resetForm();
  }

  resetForm() {
    this.showAddForm = false;
    this.showValidForm = false;
    this.questionForm.reset();
    this.FormValidation.reset();
  }



  updateQuestion() {
    if( this.selectedQuestion) {
      console.log(this.selectedQuestion)
      this.selectedQuestion.content = this.questionForm.get('content')?.value;
      this.selectedQuestion.levelId = this.questionForm.get('levelId')?.value;
      this.selectedQuestion.points = this.questionForm.get('points')?.value;
      this.selectedQuestion.subjectId = this.questionForm.get('subjectId')?.value;
      this.selectedQuestion.numberOfResponses = this.questionForm.get('numberOfResponses')?.value;
      this.selectedQuestion.numberOfCorrectResponses = this.questionForm.get('numberOfCorrectResponses')?.value;

      this.questionService.updateQuestion(this.selectedQuestion).subscribe(
        () => {
          this.loadQuestions();
          Swal.fire('Success', 'Level updated successfully!', 'success');
          this.resetForm();
        },
        () => {
          Swal.fire('Error', 'Failed to update level', 'error');
          this.resetForm()
        }
      );
    } else {
      Swal.fire('Error', 'Selected answer is undefined, cannot update', 'error');
    }
  }


    editQuestion(question: Questions) {
        this.operation = 'update';
        this.selectedQuestion = question;
        console.log(question);

        this.questionForm.setValue({
            content: question.content,
            levelId: question.levelId,  // Utilisez directement levelId
            points: question.points,
            subjectId: question.subjectId,
            numberOfResponses: question.numberOfResponses,
            numberOfCorrectResponses: question.numberOfCorrectResponses,
        });

        this.showAddForm = true;
    }


}

