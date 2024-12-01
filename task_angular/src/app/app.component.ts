import { Component } from '@angular/core';
import { StorageService } from './auth/services/storage/storage.service';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  isEmployeeLoggedIn: boolean = StorageService.isEmployeeLoggedIn();
  isAdminLoggedIn: boolean = StorageService.isAdminLoggedIn();

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Listen to router events and update login states
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isEmployeeLoggedIn = StorageService.isEmployeeLoggedIn();
        this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
      }
    });
  }

  logout(): void {
    StorageService.logout();
    this.router.navigateByUrl("/login");
  }
}
