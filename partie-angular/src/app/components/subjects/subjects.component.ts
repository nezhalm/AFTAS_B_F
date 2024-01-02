import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import {HttpErrorResponse} from "@angular/common/http";
import {Component, OnInit} from "@angular/core";
import {SubjectsService} from "./subjects.service";
import {Subjects} from "./subjects";



@Component({
    selector: 'app-subjects',
    templateUrl: './subjects.component.html',
    styleUrls: ['./subjects.component.css']
})
export class SubjectsComponent implements OnInit {

    subjects: any = {};

    subjectsData: Subjects[] = [];

    subjectForm!: FormGroup;

    operation: String = 'add';

    selectedSubject: Subjects | null = null;

    showAddForm = false;




    constructor( private subjectService: SubjectsService, private fb: FormBuilder) {
        this.createForm();
    }

    ngOnInit(): void {
        this.loadSubjects()
    }


    loadSubjects() {
        this.subjectService.getSubjects().subscribe(
            (subject: any) => {
                console.log('API Response:', subject);
                this.subjects = subject;
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
            }
        );
    }
    createForm() {
        this.subjectForm = this.fb.group({
            title: ['', Validators.required],
            parentId: ['', Validators.required],

        });
    }


    deleteSubject(responseId: number): void {
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
                this.subjectService.deleteSubject(responseId).subscribe(
                    (response: { message: string }) => {
                        Swal.fire(
                            'Supprimé!',
                            response.message,
                            'success'
                        );
                        this.loadSubjects();
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


    addSubject() {

        const newSubject = this.subjectForm.value as Subjects;
        this.subjectService.addSubject(newSubject).subscribe(
            res => {
                this.loadSubjects();
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
        this.subjectForm.reset();
    }



    updateSubject() {
        if( this.selectedSubject) {
            console.log(this.selectedSubject)
            this.selectedSubject.title = this.subjectForm.get('title')?.value;
            this.selectedSubject.parentId = this.subjectForm.get('parentId')?.value;
            this.subjectService.updateSubject(this.selectedSubject).subscribe(
                () => {
                    this.loadSubjects();
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


    editSubject(subject: Subjects) {
        this.operation = 'update';
        this.selectedSubject = subject;
        // console.log(subject)
        this.subjectForm.setValue({
            title: subject.title,
            parentId: subject.parentId,
        });
        this.showAddForm = true;
    }
}

