# Rendszerterv
## 1. A rendszer célja

Célunk egy olyan program létrehozása, mely segítségével több felhasználó egy pixelart-os vásznon keresztül készithetnek rajzokat. A projekt két programból fog állni: szerver és kliens.


## 2. Üzleti folyamatok modellje

### 2.1 Üzleti szereplők

A rendszer regisztrált fiókkal használható. Csak bejelentkezés után lehet használni a programot.
Annak eldöntése, hogy a szoftvert kinek az eszközére települ és ki használhatja
azt a megrendelő vállalat döntésére van bízva.


## 3. Követelmények

### 3.1 Funkcionális követelmények

| ID | Megnevezés | Leírás |
| --- | --- | --- |
| K1 | Használat | A programot csak az arra jogosultak tudják használni, regisztráció szükséges. |
| S1 | Funkció | A szerver képes legyen több felhasználót kezelni |
| K2 | Használat | A felhasználó választhasson színt |
| K3 | Használat | A felhasználó átszínezhessen egy pixelt |
| S2 K4 | Funkció | A szerver és kliens tudjon kommunikálni egymással |
| S3 | Funkció | A szerver használja a MySQL adatbázist |

### 3.2 Nemfunkcionális követelmények

| ID | Megnevezés | Leírás |
| --- | --- | --- |
| B1 | Letisztult design | A program ablakainak a designja legyen letisztult, átlátható, könnyen használható. Törekszünk a lehető legkényelmesebb megjelenésre.
| Bn | ... | ...

### 3.3 Támogatott eszközök
Mivel Java alkalmazás, ezért ez platform független.

## 4. Funkcionális terv
### Kliens program
A program indulásakor a felhasználónak be kell jelentkezni. Sikeres bejelentkezés után előrhetővé válik a vászon.
Ha a bejelentkezett felhasználó admin, akkor van lehetősége felhasználó kezelő ablakot is megnyitni, ahol új felhasználót tud hozzáadni, módosítani adatot neki, törölni.

### Szerver program
A programnak fogadnia kell a kliens általi TCP kapcsolódási kérvényeket. Ezek után ha a kliens oldali felhasználó helyes bejelentkezési adatokat küldd el, akkor a fiókkal kapcsolatus funkciók elérhetővé vállnak. A szerver program egy konzolos alkalmazás.


## 5. Fizikai környezet

### Vásárolt softwarekomponensek és külső rendszerek
Nincsenek vásárolt szoftverkomponensek, egyetemi hallgatói azonosítóval elérhetőek ingyenesen.
Linode szerver

### Fejlesztő eszközök
IntelliJ Idea

## 6. Architekturális terv

### 6.1 Webszerver
Ubuntu szerver. TCP kapcsolaton keresztül a kliens kapcsolódik a szerverhez.

### 6.2 Adatbázis rendszer
Szerver-Kliens alkalmazás, a szerver gondoskodik a biztonságos adatbázis kapcsolatról.
A szerver MySQL adatbázist használ.

## 7. Adatbázis terv

Adatbázis terv részletei

### Felhasználó tábla
 * ID
 * Felhasználónév
 * Jelszó (hash-elt)
 * Jogosultsági szint

### Pixel tábla
 * Pozíció x
 * Pozíció y
 * Szín
 * Szerkesztő ID-je
 

## 8. Implementációs terv

### Kliens
 * Java ablakos program.
 * A felhasználói/adminisztrációs felület - Java GUI alkalmazás Swift segítségével.
 * A programok objektum orientált programozási paradigma használatával.
 * Alkalmazott Tervezési minták : Facade

### Szerver
 * Java konzolos program.
 * A programok objektum orientált programozási paradigma használatával.
 * Alkalmazott Tervezési minták : Facade, Observer


## 9. Tesztterv

A tesztelések célja a rendszer és komponensei funkcionalitásának teljes vizsgálata,
ellenőrzése a rendszer által megvalósított üzleti szolgáltatások verifikálása.
A teszteléseket a fejlesztői csapat minden tagja elvégzi.
Unit tesztek külön komponensekhez.

(Unit tesztek elérhetőek a kliens és a szoftvernek is a Test mappájukon belül)

## 10. Telepítési terv

### Kliens
Szoftver telepítési terv: Szükségünk van egy számítógépre, amelyen el tudjuk indítani az alkalmazást. 

### Szerver
Szoftver telepítési terv: A szervernek futtatnia kell java alkalmazásokat, mysql adatbázist.

## 11. Karbantartási terv

Fontos ellenőrizni:
*	Adatok elmentése, megjelenítése jól működik.
*	A program nem lassul be.
*	A szerver megfelelően működik.

Figyelembe kell venni a felhasználó által jött visszajelzést is a programmal kapcsolatban.
Ha hibát talált, mielőbb orvosolni kell, lehet az:
*	Működéssel kapcsolatos
*	Kinézet, dizájnnal kapcsolatos
