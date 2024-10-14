import { Component } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { Ticket } from '../models/ticket';
import { CommonModule, NgFor } from '@angular/common';
import { MatCard, MatCardModule } from '@angular/material/card';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-ticket-list',
  standalone: true,
  imports: [CommonModule, MatIcon, NgFor, MatCardModule, RouterLink],
  templateUrl: './ticket-list.component.html',
  styleUrl: './ticket-list.component.scss'
})
export class TicketListComponent {

  public tickets: Ticket[] = [{id: "1", title: "Ticket1", description: "sfgsdfgdsfg"}, {id: "2", title: "Ticket2", description: "dssfgsfg"}]

}