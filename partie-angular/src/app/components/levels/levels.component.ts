
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';


import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import {Levels} from "./levels";
import {LevelService} from "./levels.service";
import {HttpErrorResponse} from "@angular/common/http";


@Component({
  selector: 'app-levels',
  templateUrl: './levels.component.html',
  styleUrls: ['./levels.component.css']
})
export class LevelsComponent implements OnInit {

  levels: any = {};

  newLevel: Levels = new Levels(1, 'test', 5,3 );


  levelsData: Levels[] = [];

  levelForm!: FormGroup;

  operation: String = 'add';

  selectedLevel: Levels | null = null;

  showAddForm = false;

  constructor(private levelService: LevelService, private fb: FormBuilder) {
    this.createForm();
  }

  ngOnInit(): void {
    this.loadLevels();
    // this.levelsInitialized.emit(true);
  }

  createForm() {
    this.levelForm = this.fb.group({
      description: ['', Validators.required],
      minPoints: '',
      maxPoints: ''
    });
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

    deleteLevel(levelId: number): void {
    // Utilisation de SweetAlert pour demander confirmation
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
        // L'utilisateur a cliqué sur "Oui, supprimer!"
        this.levelService.deleteLevel(levelId).subscribe(
          (response: { message: string }) => {
            Swal.fire(
              'Supprimé!',
              response.message,
              'success'
            );
            // Rafraîchissez la liste après la suppression si nécessaire
            this.loadLevels();
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














  addLevel() {
    const newLevel = this.levelForm.value as Levels;
    this.levelService.addLevel(newLevel).subscribe(
      res => {
        this.loadLevels();
        this.resetForm();
        Swal.fire('Success', 'Level added successfully!', 'success');
      },
      error => {
        Swal.fire('Error', 'Failed to add answer', 'error');
      }
    )
  }



  cancelAddOrEdit() {
    this.operation = 'add';
    this.resetForm();
  }

  resetForm() {
    this.showAddForm = false;
    this.levelForm.reset();
  }



  updateLevel() {
    if( this.selectedLevel ) {
      console.log(this.selectedLevel)
      this.selectedLevel.description = this.levelForm.get('description')?.value;
      this.selectedLevel.maxPoints = this.levelForm.get('maxPoints')?.value;
      this.selectedLevel.minPoints = this.levelForm.get('minPoints')?.value;

      this.levelService.updateLevel(this.selectedLevel).subscribe(
        () => {
          this.loadLevels();
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


  editLevel(level: Levels) {
    this.operation = 'update';
    this.selectedLevel = level;
    this.levelForm.setValue({
      description: level.description,
      minPoints: level.minPoints,
      maxPoints: level.maxPoints,
    });
    this.showAddForm = true;
  }


}
















