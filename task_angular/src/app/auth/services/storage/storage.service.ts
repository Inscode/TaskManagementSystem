import { Injectable } from '@angular/core';


const TOKEN = "token";
const USER = "user";

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  
  private static isWindowDefined(): boolean {
    return typeof window !== 'undefined' && window.localStorage !== null;
  }

  static saveToken(token: string): void {
    if (this.isWindowDefined()) {
      window.localStorage.removeItem(TOKEN);
      window.localStorage.setItem(TOKEN, token);
    }
  }

  static saveUser(user: any): void {
    if (this.isWindowDefined()) {
      window.localStorage.setItem(USER, JSON.stringify(user));
    }
  }

  static getToken(): string | null {
    if (this.isWindowDefined()) {
      return window.localStorage.getItem(TOKEN);
    }
    return null;
  }

  static getUser(): any | null {
    if (this.isWindowDefined()) {
      const user = window.localStorage.getItem(USER);
      return user ? JSON.parse(user) : null;
    }
    return null;
  }

  static getUserRole(): string {
    const user = this.getUser();
    return user?.role ?? '';
  }

  static isAdminLoggedIn(): boolean {
    return this.getToken() !== null && this.getUserRole() === "ADMIN";
  }

  static isEmployeeLoggedIn(): boolean {
    return this.getToken() !== null && this.getUserRole() === "EMPLOYEE";
  }

  static getUserId(): string {
    return this.getUser()?.id ?? '';
  }

  static logout(): void {
    if (this.isWindowDefined()) {
      window.localStorage.removeItem(TOKEN);
      window.localStorage.removeItem(USER);
    }
  }
}
