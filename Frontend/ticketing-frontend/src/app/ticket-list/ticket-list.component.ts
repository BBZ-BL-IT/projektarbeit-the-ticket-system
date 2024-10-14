import { Component, OnInit } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { Ticket } from '../models/ticket';
import { CommonModule, NgFor } from '@angular/common';
import { MatCard, MatCardModule } from '@angular/material/card';
import { RouterLink } from '@angular/router';
import {Connection} from 'rabbitmq-client'
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-ticket-list',
  standalone: true,
  imports: [CommonModule, MatIcon, NgFor, MatCardModule, RouterLink, HttpClientModule],
  templateUrl: './ticket-list.component.html',
  styleUrl: './ticket-list.component.scss'
})
export class TicketListComponent implements OnInit {

  public tickets?: Ticket[];

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.http.get("/api/users").subscribe((tickets: any) => {
      this.tickets = tickets;
    })
  }
  
}