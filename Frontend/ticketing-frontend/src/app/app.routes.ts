import { Routes } from '@angular/router';
import { TicketListComponent } from './ticket-list/ticket-list.component';
import { TicketPageComponent } from './ticket-list/ticket-page.component';

export const routes: Routes = [
    {
        path: "home",
        children: [
            {
                path: '',
                component: TicketListComponent,
            }
        ]
    },
    {
        path: "ticket",
        children: [
            {
                path: '',
                component: TicketPageComponent,
            },
            {
                path: ':id',
                component: TicketPageComponent,
            }
        ]
    },
    {
        path: '**',
        redirectTo: '/home',
        pathMatch: 'full',
    }
];
