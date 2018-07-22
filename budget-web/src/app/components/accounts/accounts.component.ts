import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatMenu } from '@angular/material';
import { AccountDto } from '../../queries/accounts/account.dto';
import { Apollo } from 'apollo-angular';
import gql from 'graphql-tag';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { AccountsQuery } from '../../queries/accounts/accounts.query';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];

@Component({
  selector: 'app-accounts',
  templateUrl: 'accounts.component.html',
  styleUrls: ['accounts.component.scss']
})
export class AccountsComponent {
  accountsObservable: Observable<AccountDto[]>;
  tagsObservable: Observable<string[]>;
  selected: FormControl = new FormControl(0);
  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource = ELEMENT_DATA;

  constructor(private apollo: Apollo) {
    this.accountsObservable = this.apollo
      .query<AccountsQuery>({
        query: gql`
          query {
            accounts {
              id
              name
              tags
            }
          }
        `
      })
      .pipe(
        map(({ data }) => {
          return data.accounts;
        })
      );
    this.tagsObservable = this.accountsObservable.pipe(
      map(accounts =>
        accounts
          .reduce((tags, account) => tags.concat(account.tags), [])
          .filter((tag, ind, tags) => tags.indexOf(tag) === ind)
      )
    );
  }
}
