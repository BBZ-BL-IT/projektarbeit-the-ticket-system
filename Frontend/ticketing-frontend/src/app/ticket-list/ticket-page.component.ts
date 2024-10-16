import { Component, OnInit } from '@angular/core';
import { Ticket } from '../models/ticket';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { MatIcon } from '@angular/material/icon';
import { MatInput, MatInputModule } from '@angular/material/input';
import { Person } from '../models/person';
import { MatOption, MatSelect } from '@angular/material/select';
import { CommonModule, NgFor } from '@angular/common';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, NgModel, NgModelGroup } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-ticket-page',
  standalone: true,
  imports: [MatIcon, MatInputModule, MatOption, MatSelect, NgFor, FormsModule, HttpClientModule],
  templateUrl: './ticket-page.component.html',
  styleUrl: './ticket-page.component.scss'
})
export class TicketPageComponent implements OnInit {

  public ticket?: Ticket;
  public ticketId?: string = undefined;
  public isCreate = false;

  public personen?: Person[];

  constructor(
    public route: ActivatedRoute,
    private http: HttpClient,
    public router: Router,
  ) {}

  ngOnInit() {
    this.route.params.subscribe(async (params: Params) => {
      const ticketId = params['id'];
      if (ticketId) {
          this.ticketId = ticketId;
          await this.loadTicket();
      } else {
          this.ticketId = Math.floor(Math.random() * 1000).toString();
          this.isCreate = true;
          this.ticket = {};
      }
  });
  }

  public loadTicket() {
    this.http.get("/api/tickets/" + this.ticketId).subscribe((ticket: any) => {
      this.ticket = ticket;
    })
  }

  public storeTicket() {
    if ( this.isCreate) {
    this.http.put("/api/tickets/", this.ticket).subscribe((ticket: any) => {
        
    });;
    } else {
      this.http.put("/api/tickets/" + this.ticket?.id, this.ticket).subscribe((ticket: any) => {

      });
    }
    this.router.navigate(["/home"]);
  }

  public deleteTicket() {
    if (!this.isCreate) {
      this.http.delete("/api/tickets/" + this.ticketId).subscribe((ticket: any) => {

      })
    }
    this.router.navigate(["/home"]);
}

  public cancel() {
    this.router.navigate(["/home"]);
  }

}
