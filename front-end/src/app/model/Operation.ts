import {Time} from "@angular/common";

export interface Operation {

  id?: number;
  accountId: number;
  foreignAccount: number;
  transactionDate: Date;
  value: number;
  type: string;
}