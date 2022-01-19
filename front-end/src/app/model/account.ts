import {Operation} from "./operation";

export interface Account {
  id?: number;
  accountNumber: number;
  customerId: number;
  isForeign: number;
  currency: string;
  balance: number;
  operations?: Operation;
}
