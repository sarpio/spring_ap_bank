import {Account} from "./Account";

export interface Customer {
  id?: number;
  name: string;
  accounts?: Account[];
}

