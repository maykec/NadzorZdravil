# NadzorZdravil aplikacja 
Android aplikacija napisana v Kotlin programskem jeziku in je namenjena rednemu preverjanju jemanja zdravil. Aplikacija deluje skupaj s servisno aplikacijo, ki je objavljena na tem [repositoriju](https://github.com/maykec/OpomnikZdravil)

## Opis aplikacije
Z aplikacijo lahko dodamo nov opomnik za zdravila. Opomnik se shrani v podatkovno bazo Google Firebase in se sihnorizira s servisno aplikacijo. Ob času, ko je potrebno vzeti zdravilo, servisna aplikacija sproži obvestilo. Obvestilo je potrebno potrditi. Servisna aplikacija meri čas od pojava obvestila do potrdila obvestila. Na tak način se zabeleži morebitna zamuda ali preskok pri jemanju zdravil.
Aplikacija je sestavljena iz treh zaslonov

### Zaslon Pregled
![](https://raw.githubusercontent.com/maykec/NadzorZdravil/master/blob/home.png)

Na tem zaslonu je prikazan seznam opomnikov za zdravila. Vsak element v seznamu je ime zdravila in čas ob katerem je potrebno zdravilo vzeti.