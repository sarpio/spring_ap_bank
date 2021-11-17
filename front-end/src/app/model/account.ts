import {Customer} from "./customer";

export interface Account {
  id?: number;
  accountNumber: string;
  balance: number;
  customer: Customer;
  currency: string;
  foreign: boolean;
  customerName: string;
}
