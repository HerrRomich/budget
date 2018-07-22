import { NgModule } from '@angular/core';
import { AccountsComponent } from './accounts.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../material.module';

@NgModule({
  declarations: [AccountsComponent],
  imports: [
    MaterialModule,
    FormsModule,
    CommonModule
  ]
})
export class AccountsModule {}
