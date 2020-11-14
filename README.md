# Cinema App [![Build Status](https://github.com/gianmarcomennecozzi/cinema-app/workflows/tests/badge.svg)](https://github.com/gianmarcomennecozzi/cinema-app/actions)

Created a console app that allows the user to:
- creates a new room (rows x cols)
- selects a room from the list of the created ones
- books a seat
- shows an output of the seats and their status (“A” for available, “R” for reserved)
- shows a different metric for the selected room

The user can interact with the app thanks to a menu.

### Usage

```bash
$ git clone https://github.com/gianmarcomennecozzi/cinema-app.git
$ cd cinema-app
$ docker build -t cinema-app .
$ docker run -i cinema-app
```

### Test 
```bash
./gradlew test
```