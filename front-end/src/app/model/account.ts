import {Customer} from "./customer";

export interface Account {
  id?: number;
  accountNumber: string;
  customer: Customer;
  currency: string;
  balance: number;
  foreign: boolean;
}
