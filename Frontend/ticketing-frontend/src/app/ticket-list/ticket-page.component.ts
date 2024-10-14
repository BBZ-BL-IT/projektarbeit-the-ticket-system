import { Component, OnInit } from '@angular/core';
import { Ticket } from '../models/ticket';
import { ActivatedRoute, Params } from '@angular/router';
import { MatIcon } from '@angular/material/icon';
import { MatInput, MatInputModule } from '@angular/material/input';
import { Person } from '../models/person';
import { MatOption, MatSelect } from '@angular/material/select';
import { CommonModule, NgFor } from '@angular/common';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, NgModel, NgModelGroup } from '@angular/forms';

@Component({
  selector: 'app-ticket-page',
  standalone: true,
  imports: [MatIcon, MatInputModule, MatOption, MatSelect, NgFor, FormsModule],
  templateUrl: './ticket-page.component.html',
  styleUrl: './ticket-page.component.scss'
})
export class TicketPageComponent implements OnInit {

  public ticket?: Ticket;
  public ticketId?: string = undefined;
  public isCreate = false;

  public persons?: Person[] = [{id: "1", vorname: "Luca", nachname: "Gass"}, {id: "2", vorname: "Jamie", nachname: "Niederhauser"}, {id: "3", vorname: "Robin", nachname: "Bühler"}];

  constructor(
    public route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.params.subscribe(async (params: Params) => {
      const ticketId = params['id'];
      if (ticketId) {
          this.ticketId = ticketId;
          // await this.loadReservation();
      } else {
          this.isCreate = true;
      }
  });
  }

  public loadReservation() {

  }

  public getPersons() {
    
  }

}
