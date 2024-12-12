import { Routes } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { AddTicketComponent } from "./vendor/add-ticket/add-ticket.component";
import { BuyTicketComponent } from "./customer/buy-ticket/buy-ticket.component";
import { SimulationComponent } from "./simulation/simulation/simulation.component";
import { ConfigurationComponent } from "./configuration/configuration/configuration.component";



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
    },
    {
        path: "buy-tickets",
        component: BuyTicketComponent,
        title: "Buy ticket"
    },
    {
        path: "simulation",
        component: SimulationComponent,
        title: "Simulation"
    },
    {
        path: "config",
        component: ConfigurationComponent,
        title: "Configuration"
    }

    
]

export default routeConfig;