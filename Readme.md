# NadzorZdravil aplikacja 
Android aplikacija napisana v Kotlin programskem jeziku in je namenjena rednemu preverjanju jemanja zdravil. Aplikacija deluje skupaj s servisno aplikacijo, ki je objavljena na tem [repositoriju](https://github.com/maykec/OpomnikZdravil)

## Opis aplikacije
Z aplikacijo lahko dodamo nov opomnik za zdravila. Opomnik se shrani v podatkovno bazo Google Firebase in se sihnorizira s servisno aplikacijo. Ob času, ko je potrebno vzeti zdravilo, servisna aplikacija sproži obvestilo. Obvestilo je potrebno potrditi. Servisna aplikacija meri čas od pojava obvestila do potrdila obvestila. Na tak način se zabeleži morebitna zamuda ali preskok pri jemanju zdravil.
Aplikacija je sestavljena iz treh zaslonov

### Zaslon Pregled
![](https://raw.githubusercontent.com/maykec/NadzorZdravil/master/blob/home.png)

Na tem zaslonu je prikazan seznam opomnikov za zdravila. Vsak element v seznamu je ime zdravila in čas ob katerem je potrebno zdravilo vzeti.

### Zaslon Obvestila
![](https://raw.githubusercontent.com/maykec/NadzorZdravil/master/blob/notifications.png)

Prikazuje seznam že sproženih obvestil iz servisne aplikacije. Vsak element v seznamu prikazuje ime zdravila in kdaj/če/s kolikšno zamudo je bil prejem zdravila potrjen. (Uporabnk je potrdil obvestilo v servisni aplikaciji)

### Zaslon Dodajanje Zdravila
![](https://raw.githubusercontent.com/maykec/NadzorZdravil/master/blob/add_event.png)

Zaslon za dodajanje novega zdravila. Uporabnik nastavi ime zdravila in čas jemanja zdravila. Obvestila se bodo zaradi preprostosti sprožala vsaki dan. 


## Zagon aplikacje
Aplikacijo je potrebbo odpreti v Android Studio

1. `git clone https://github.com/maykec/NadzorZdravil.git`
2. Potrebno je imeti konfiguriran Google Firebase račun
