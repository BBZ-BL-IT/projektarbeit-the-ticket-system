import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TicketListComponent } from './ticket-list/ticket-list.component';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, TicketListComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'ticketing-frontend';
}
