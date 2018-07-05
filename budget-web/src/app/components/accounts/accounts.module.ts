import { NgModule } from '@angular/core';
import { AccountsComponent } from './accounts.component';
import {
  MatToolbarModule,
  MatTabsModule,
  MatIconModule,
  MatButtonModule,
  MatMenuModule
} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [AccountsComponent],
  imports: [
    MatToolbarModule,
    MatTabsModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    FlexLayoutModule,
    FormsModule,
    CommonModule
  ]
})
export class AccountsModule {}
