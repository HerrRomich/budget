import { NgModule } from '@angular/core';
import { AccountsComponent } from './accounts.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../material.module';
import { AccountTableComponent } from './account.table';

@NgModule({
  declarations: [AccountsComponent, AccountTableComponent],
  imports: [MaterialModule, FormsModule, CommonModule]
})
export class AccountsModule {}
