import { Routes } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { AddTicketComponent } from "./vendor/add-ticket/add-ticket.component";



const routeConfig: Routes = [
    {
        path: "home",
        component: HomeComponent,
        title: "Home"
    }, 
    {
        path: "add-tickets",
        component: AddTicketComponent,
        title: "Add tickets"
    }
]

export default routeConfig;