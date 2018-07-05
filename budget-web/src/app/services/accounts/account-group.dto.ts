import { AccountDto } from './account.dto';

export interface AccountGroupDto {
  id: number;
  name: string;
  accounts: AccountDto[];
}
