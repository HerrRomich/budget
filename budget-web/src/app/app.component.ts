import { Component } from '@angular/core';
import { MainMenuService } from './services/main-menu.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Budget';

  constructor(public mainMenuService: MainMenuService) {
    this.mainMenuService.getMainMenu();
  }
}
