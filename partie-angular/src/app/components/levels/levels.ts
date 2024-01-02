import {Questions} from "../questions/questions";

//  export class Levels {
//    constructor(
//     public id?: number,
//     public maxPoints?: number,
//      public minPoints?: number,
//      public description?: string,
//      public questionsList?: Questions[]
//
//    ) {}
// }

export class Levels {
  constructor(
    public id: number,
    public description: string,
    public maxPoints: number,
    public minPoints: number
  ) {}
}
