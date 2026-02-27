___
## ** WORK IN PROGRESS **
** Subject to change, not final yet. **
___
## Fitness-App
___

## Geplante Features
### Core
- [x] Benutzerregistrierung & Anmeldung (JWT, stateless)
- [x] Request-Validierung
- [x] Globale Fehlerbehandlung (@RestControllerAdvice)
- [x] Passwort-Hashing (BCrypt)]

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


## Technologien
- **Backend**: Java, Spring Boot (REST API)
- **Frontend**: noch offen (z.B. React, Angular, Flutter)
- **Datenbank**: PostgreSQL oder MySQL
- **Build-Tool**: Maven
- **Versionierung**: Git
- **Deployment**: Containierisierung mit Docker
- **Testing**: JUnit, Mockito

## CI/CD (geplant)
- [ ] CI: GitHub Actions (Build + Tests bei jedem Push)
- [ ] Docker: Imgae Build in CI
- [ ] Security: Dependabot / Dependency-Scans
- [ ] CD: optionales Deployment

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