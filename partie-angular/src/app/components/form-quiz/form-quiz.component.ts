import {Component, OnInit} from '@angular/core';
import {ValidationsService} from "../validations/validations.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-form-quiz',
  templateUrl: './form-quiz.component.html',
  styleUrls: ['./form-quiz.component.css']
})
export class FormQuizComponent implements OnInit {
  questions: any = {};

  constructor(private validationsService: ValidationsService) {}

  ngOnInit() {
    this.loadValidations();
  }

  loadValidations() {
    this.validationsService.getValidations().subscribe(
      (data: any) => {
        this.questions = data.validations.map((validation: any) => validation.question.content);
      },
      error => {
        console.error('Erreur lors de la récupération des validations', error);
      }
    );
  }

  onDragStart(event: DragEvent) {
    const target = event.target as HTMLElement;
    target.classList.add("dragging");
    event.dataTransfer?.setData('text/plain', ''); // Necessary for Firefox
  }

  onDragEnd(event: DragEvent) {
    const target = event.target as HTMLElement;
    target.classList.remove("dragging");
  }

  onDragOver(event: DragEvent) {
    event.preventDefault();
  }

  onDrop(event: DragEvent) {
    const target = event.target as HTMLElement;
    const dragging = document.querySelector(".dragging");

    if (dragging && target.classList.contains("column")) {
      const applyAfter = this.getNewPosition(target, event.clientY);

      if (applyAfter) {
        applyAfter.insertAdjacentElement("afterend", dragging);
      } else {
        target.appendChild(dragging);
      }
    }
  }

  private getNewPosition(column: HTMLElement, posY: number): Element | null {
    const cards = Array.from(column.querySelectorAll(".item:not(.dragging)"));
    let result: Element | null = null;

    for (let refer_card of cards) {
      const box = (refer_card as HTMLElement).getBoundingClientRect();
      const boxCenterY = box.y + box.height / 2;

      if (posY >= boxCenterY) result = refer_card;
    }

    return result;
  }
}
