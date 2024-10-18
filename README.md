[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/YfrAWYyB)

Projekt Ticketsystem von Robin, Luca und Jamie für das Modul M321
-------------------------------------------------------------------------
1. Open Docker
2. clone the project with "git clone https://github.com/BBZ-BL-IT/projektarbeit-the-ticket-system.git"
3. go into the CMD and type: "docker-compose up --build"
-------------------------------------------------------------------------

If the project doesn't start after "docker-compose up --build"

Start the Spring-Boot Application here src/main/java/ch/m321/ticketsystem/TicketsystemApplication.java

To start the Frontend:
1. go into Frontend/ticketing-frontend
2. npm install
3. ng s

Um rabbitmq zu starten: docker-compose up rabbitmq
Benutzername: guest
Passwort: guest

To start only Backend with RabbitMq: docker-compose up --build backend
