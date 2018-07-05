import { Injectable } from '@angular/core';
import { MatMenu } from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class MainMenuService {
  mainMenu: MatMenu;

  setMainMenu(mainMenu: MatMenu) {
    this.mainMenu = mainMenu;
  }

  getMainMenu(): MatMenu {
    return this.mainMenu;
  }
}
