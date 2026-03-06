![CI](https://github.com/JBirnbickl93/fitness/actions/workflows/ci.yml/badge.svg)


___
## ** WORK IN PROGRESS **
** Subject to change, not final yet. **
___
## Fitness-App

Spring Boot Backend für eine Fitness-App, die folgende Funktionen bietet:
- Trainingserstellung und -verwaltung
- Logging von Trainingseinheiten (Übungen, Sätze, Wiederholungen, Gewichte
- Progression / Auswertung (z.B. Volumen, Trends, Empfehlungen)
- Benutzerregistrierung & Anmeldung (JWT, stateless)
- Kalorientracking
- Körperwerte-Tracking (Gewicht, Umfänge)
- 
___

## Technologien
- **Backend**: Java, Spring Boot (REST API)
- **Frontend**: noch offen (z.B. React, Angular, Flutter)
- **Datenbank**: PostgreSQL oder MySQL
- **Build-Tool**: Maven
- **Versionierung**: Git
- **Deployment**: Containierisierung mit Docker
- **Testing**: JUnit, Mockito


## Domain Model

Das Trainingsmodell ist hierarchisch aufgebaut und trennt:
- Workouts (Trainingseinheiten)
- Exercises (Übungsstammdaten)
- WorkoutEntries (Übungen innerhalb eines Workouts)
- SetEntries (einzelne Sätze mit Gewicht und Wiederholungen)

### Domain Überblick
- Ein Benutzer kann mehrere Workouts haben.
- Ein Workout besteht aus mehreren WorkoutEntries.
- Ein WorkoutEntry referenziert eine Exercise.
- Ein WorkoutEntry hat mehrere SetEntries.

```mermaid
erDiagram
    USER ||--o{ WORKOUT : owns
    WORKOUT ||--o{ WORKOUT_ENTRY : contains
    WORKOUT_ENTRY }o--|| EXERCISE : references
    WORKOUT_ENTRY ||--o{ SET_ENTRY : has
```


## Auth Flow (Register → Login → Token)
1) Register

**POST** /api/auth/register
Request (JSON):
```json
{
    "email": "testmail@server.de",
    "password": "testpassword123",
    "nickName": "tester"
}
```
Response: 201 Created (bei Erfolg) oder 400 Bad Request (bei Fehlern, z.B. E-Mail bereits vergeben, kein Nickname, etc.)

2) Login

**POST** /api/auth/login

Request (JSON):
```json
{
  "email": "testmail@server.de",
  "password": "testpassword123"
}
```
Response (JSON):
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwic3ViIjoidGVzdG1haWxAc2VydmVyLmRlIiwiaWF0IjoxNzcyMTEyNjk3LCJleHAiOjE3NzIxOTkwOTd9.a2jDPENgSiuaS7zwh-nx0q144CAQMyM827kBw-lo1Ao"
}
```

**GET** /api/user/me (Testendpunkt)  
→ Gibt "Hello {username}!" zurück, wenn der JWT-Token korrekt ist, ansonsten 401 Unauthorized


## Geplante Features
### Core
- ✅ Benutzerregistrierung & Anmeldung (JWT, stateless)
- ✅ Request-Validierung
- ✅ Globale Fehlerbehandlung (@RestControllerAdvice)
- ✅ Passwort-Hashing (BCrypt)

### Qualitätssicherung
- ✅ CI mit GitHub Actions (Build + Tests bei jedem Push / PR)
- ✅ Integrationstests für Auth-Flow (Register → Login → Token → geschützter Endpunkt)
- ✅ In-Memory-Datenbank (H2) für isolierte Testumgebung

### Training
- [ ] Erstellung und Verwaltung von Trainingsplänen
- [ ] Logging von Trainingseinheiten (Übungen, Sätze, Wiederholungen, Gewichte)
- [ ] Progression / Auswertung (z.B. Volumen, Trends, Empfehlungen)
### Ernährung
- [ ] Integration Kalorienzähler
- [ ] Datenbankanbindung für Nahrungsinformationen (Barcode, Nährwerte, Makro-Tracking)
### Körper & Aktivität
- [ ] Körperwerte-Tracking (Gewicht, Umfänge)
- [ ] Wearable-Integration (Aktivität → Kalorienzähler anpassen)
### Visualisierung
- [ ] Fortschrittsvisualisierung (Diagramme, Statistiken)




## CI/CD Pipeline
- ✅ CI: GitHub Actions (Build + Tests bei jedem Push)
- [ ] Docker: Image Build in CI
- [ ] Security: Dependabot / Dependency-Scans
- [ ] CD: optionales Deployment