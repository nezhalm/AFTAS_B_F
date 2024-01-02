import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import {HttpErrorResponse} from "@angular/common/http";
import {Component, OnInit} from "@angular/core";
import {LevelService} from "../levels/levels.service";
import {SubjectsService} from "../subjects/subjects.service";
import {Levels} from "../levels/levels";
import {Responses} from "./responses";
import {ResponsesService} from "./responses.service";
import {Questions} from "../questions/questions";


@Component({
  selector: 'app-responses',
  templateUrl: './responses.component.html',
  styleUrls: ['./responses.component.css']
})
export class ResponsesComponent implements OnInit {

  responses: any = {};

  responsesData: Responses[] = [];

  responseForm!: FormGroup;

  operation: String = 'add';

  selectedResponse: Responses | null = null;

  showAddForm = false;




  constructor( private responseService: ResponsesService, private fb: FormBuilder) {
    this.createForm();
  }

  ngOnInit(): void {
  this.loadResponses()
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


  createForm() {
    this.responseForm = this.fb.group({
      content: ['', Validators.required],
    });
  }





  deleteResponse(responseId: number): void {
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
        this.responseService.deleteResponse(responseId).subscribe(
            (response: { message: string }) => {
              Swal.fire(
                  'Supprimé!',
                  response.message,
                  'success'
              );
              this.loadResponses();
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


  addResponse() {
    const newResponse = this.responseForm.value as Response;
    this.responseService.addResponse(newResponse).subscribe(
        res => {
          this.loadResponses();
          this.resetForm();
          Swal.fire('Success', 'Response added successfully!', 'success');
        },
        error => {
          Swal.fire('Error', 'Failed to add Response', 'error');
        }
    )
  }



  cancelAddOrEdit() {
    this.operation = 'add';
    this.resetForm();
  }

  resetForm() {
    this.showAddForm = false;
    this.responseForm.reset();
  }



  updateResponse() {
    if( this.selectedResponse) {
      console.log(this.selectedResponse)
      this.selectedResponse.content = this.responseForm.get('content')?.value;

      this.responseService.updateResponse(this.selectedResponse).subscribe(
          () => {
            this.loadResponses();
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


  editResponse(response: Responses) {
    this.operation = 'update';
    this.selectedResponse = response;
    console.log(response)
    this.responseForm.setValue({
      content: response.content,
    });
    this.showAddForm = true;
  }


}

