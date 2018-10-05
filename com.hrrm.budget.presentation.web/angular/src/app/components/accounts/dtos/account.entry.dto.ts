export interface AccountEntry {
  accountId: number;
  entryDate: Date;
  transactionDate: Date;
  budgetPeriod: Date;
  amount: number;
  comments: string;
}

export interface AccountEntryCategory {
  name: string;
}

export interface IncomeItem extends AccountEntryCategory {}

export interface Counterparty {
  name: string;
}

export interface SimpleAccountEntry extends AccountEntry {
  category: AccountEntryCategory;
  counterparty: Counterparty;
}
