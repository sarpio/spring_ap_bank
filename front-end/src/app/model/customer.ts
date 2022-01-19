import {Account} from "./account";

export interface Customer {
  id?: number;
  name: string;
  accounts?: Account[];
}

