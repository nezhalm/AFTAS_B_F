import {Koatch} from "../koatch/kaotch";


export class CreateQuiz {
  constructor(
    public id: number,
    public score: number,
    public showResponses: boolean,
    public showResult: boolean,
    public numberOfChances: number,
    public remarks: string,
    public duration: string | null,
    public trainer: Koatch,
    public quizQuestions: any[],
    public assignQuizzes: any[] | null
  ) {}
}
