import {Levels} from "../levels/levels";
import {Subjects} from "../subjects/subjects";

export class Questions {
  constructor(
    public  id?: number,
    public  content?:string,
    public  points?:number,
    public  numberOfResponses?:number,
    public  numberOfCorrectResponses?:number,
    public  levelId?:number,
    public  subjectId?:number
  ) {}
}
