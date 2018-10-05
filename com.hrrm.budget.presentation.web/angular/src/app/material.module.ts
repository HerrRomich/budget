import { NgModule } from '@angular/core';
import {
  MatToolbarModule,
  MatTabsModule,
  MatIconModule,
  MatButtonModule,
  MatMenuModule,
  MatButtonToggleModule,
  MatSidenavModule,
  MatInputModule,
  MatTableModule
} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

const MATERIAL_MODULES = [
  MatToolbarModule,
  MatTabsModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatIconModule,
  MatMenuModule,
  MatSidenavModule,
  MatInputModule,
  MatTableModule,
  FlexLayoutModule
];

@NgModule({
  imports: MATERIAL_MODULES,
  exports: MATERIAL_MODULES
})
export class MaterialModule {}
