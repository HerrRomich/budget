import { Injectable } from '@angular/core';
import { AccountGroupDto } from './account-group.dto';
import { Observable, of } from 'rxjs';
import { AccountDto } from './account.dto';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {
  private accounts: AccountDto[] = [
    {
      id: 1,
      name: 'Кошелек Пупсика',
      tags: ['Избраное', 'Наличные деньги']
    },
    {
      id: 2,
      name: 'Кошелек Масички',
      tags: ['Избраное', 'Наличные деньги']
    },
    {
      id: 3,
      name: 'norisbank(Top-giro)',
      tags: ['Избраное', 'Безналичные деньги']
    },
    {
      id: 4,
      name: 'norisbank(Top-Zins)',
      tags: ['Безналичные деньги']
    }
  ];

  getAccounts(tags: string[]): Observable<AccountDto[]> {
    let result = this.accounts;
    if (tags && tags.length > 0) {
      result = result.filter(account =>
        account.tags.some(tag => tags.includes(tag))
      );
    }
    return of(result);
  }

  getAccountTags(): Observable<string[]> {
    return of(
      this.accounts
        .reduce((tags, account) => tags.concat(account.tags), [])
        .filter((tag, ind, tags) => tags.indexOf(tag) === ind)
    );
  }
}
