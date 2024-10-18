[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/YfrAWYyB)

Projekt Ticketsystem von Robin, Luca und Jamie für das Modul M321
-------------------------------------------------------------------------
### Docker-Container

Jede Komponente des Systems wird in einem eigenen Docker-Container ausgeführt:

1. **Frontend-Container**:
    - NGINX dient als Webserver, um die Angular-Anwendung bereitzustellen.
    - Die Anwendung wird in den Ordner `/usr/share/nginx/html` kopiert, und NGINX liefert die Dateien auf Port `80`.
   
2. **Backend-Container**:
    - Ein Java Spring Boot-Service, der eine REST-API bereitstellt, läuft auf Port `9090`.
    - Das Backend verbindet sich mit RabbitMQ, um Nachrichten auszutauschen, und greift auf die PostgreSQL-Datenbank zu, um Ticketdaten zu verwalten.

3. **RabbitMQ-Container**:
    - RabbitMQ dient als Messaging-System, um lose Kopplung zwischen Backend-Diensten oder zwischen dem Backend und zukünftigen Diensten zu ermöglichen.
    - Die Verwaltungsschnittstelle ist auf Port `15672` zugänglich, während die Standard-AMQP-Portnummer `5672` verwendet wird.

4. **PostgreSQL-Datenbank** (optional - falls benötigt):
    - PostgreSQL wird als Datenbank verwendet, um die Tickets und Benutzerinformationen zu speichern.

## Funktionsweise

Das Ticketing-System besteht aus den folgenden Prozessen:

1. **Frontend**:
    - Benutzer greifen über ihren Browser auf das Frontend zu.
    - Das Frontend ist eine Angular-Anwendung, die in einem NGINX-Container gehostet wird und mit dem Backend über eine REST-API kommuniziert.

2. **Backend**:
    - Das Backend ist eine Spring Boot-Anwendung, die auf eingehende HTTP-Anfragen des Frontends reagiert.
    - Es führt CRUD-Operationen auf Tickets durch, die in der PostgreSQL-Datenbank gespeichert sind.
    - Es nutzt RabbitMQ, um Nachrichten zu verarbeiten und Aufgaben asynchron zu erledigen, beispielsweise die Verarbeitung von Benachrichtigungen oder die Kommunikation mit anderen Services.

3. **RabbitMQ**:
    - RabbitMQ fungiert als Message-Broker, der asynchrone Kommunikation zwischen Services ermöglicht. Zukünftige Erweiterungen könnten zusätzliche Dienste einführen, die auf verschiedene RabbitMQ-Nachrichten lauschen.

4. **PostgreSQL-Datenbank**:
    - Die Datenbank speichert die Ticketinformationen, die vom Backend über die Datenbank-API (JDBC) verwaltet werden.

### Ablauf eines Ticket-Erstellungsprozesses

1. Ein Benutzer navigiert zur Ticket-Erstellungsseite im Frontend und gibt die benötigten Daten ein.
2. Das Frontend sendet eine HTTP POST-Anfrage an die REST-API des Backends (`/api/tickets`).
3. Das Backend speichert das Ticket in der PostgreSQL-Datenbank und sendet bei Bedarf eine Nachricht an RabbitMQ, um asynchrone Verarbeitungsschritte einzuleiten.

## Einrichtung und Ausführung

### Voraussetzungen

- Docker und Docker Compose müssen auf deinem System installiert sein.

### Installation und Ausführung

1. **Projekt klonen**:
    ```bash
    git clone https://github.com/BBZ-BL-IT/projektarbeit-the-ticket-system.git
    cd ticketing-system
    ```

2. **Build und Starten der Container**:
    ```bash
    docker-compose up --build
    ```

3. **Zugriff auf die Anwendung**:
    - Frontend: Gehe zu [http://localhost/4200](http://localhost/4200).
    - Backend API: Zugriff über [http://localhost:9090/api/tickets](http://localhost:9090/api/tickets).
    - Für die Übersicht der verschiedenen Komponente über [http://localhost:9090/swagger-ui/index.html/](http://localhost:9090/swagger-ui/index.html/)

4. **RabbitMQ Dashboard**:
    - Aufruf des RabbitMQ Dashboards unter [http://localhost:15672](http://localhost:15672).
    - Standard-Anmeldedaten: `guest/guest`.

## Entwicklungs-Workflow

- Das Frontend verwendet Angular und NGINX zur Bereitstellung.
- Das Backend verwendet Spring Boot und stellt eine REST-API bereit.
- RabbitMQ dient der Nachrichtenvermittlung zwischen den Diensten.

### Frontend Entwicklung

Navigiere in das `Frontend/ticketing-frontend`-Verzeichnis und entwickle wie gewohnt mit Angular CLI:

```bash
cd Frontend/ticketing-frontend
npm install
ng serve

