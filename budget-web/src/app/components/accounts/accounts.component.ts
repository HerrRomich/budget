import { Component, OnInit, ViewChild } from '@angular/core';
import { AccountsService } from '../../services/accounts/accounts.service';
import { FormControl } from '@angular/forms';
import { MainMenuService } from '../../services/main-menu.service';
import { MatMenu } from '@angular/material';
import { AccountDto } from '../../services/accounts/account.dto';

@Component({
  selector: 'app-accounts',
  templateUrl: 'accounts.component.html',
  styleUrls: ['accounts.component.scss']
})
export class AccountsComponent implements OnInit {

  accounts: AccountDto[];
  selected: FormControl = new FormControl(0);

  @ViewChild(MatMenu)
  matMenu: MatMenu;

  constructor(private accountService: AccountsService, private mainMenuService: MainMenuService) {}

  ngOnInit(): void {
    this.getAccounts();
    this.mainMenuService.setMainMenu(this.matMenu);
  }

  getAccounts() {
    this.accountService
      .getAccounts([])
      .subscribe(accounts => (this.accounts = accounts));
  }
}
