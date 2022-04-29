# Rendszerterv
## 1. A rendszer célja

Programunk célja... (work in progress)

## 2. Üzleti folyamatok modellje

### 2.1 Üzleti szereplők

A rendszer regisztrált fiókkal használható. Csak bejelentkezés után lehet használni a programot.
Annak eldöntése, hogy a szoftvert kinek az eszközére települ és ki használhatja
azt a megrendelő vállalat döntésére van bízva.

## 3. Követelmények

### 3.1 Funkcionális követelmények

| ID | Megnevezés | Leírás |
| --- | --- | --- |
| A1 | Használat | A programot csak az arra jogosultak tudják használni, regisztráció szükséges. |
(work in progress)

### 3.2 Nemfunkcionális követelmények

| ID | Megnevezés | Leírás |
| --- | --- | --- |
| B1 | Letisztult design | A program ablakainak a designja legyen letisztult, átlátható, könnyen használható. Törekszünk a lehető legkényelmesebb megjelenésre.
| Bn | ... | ...

### 3.3 Támogatott eszközök
Mivel Java alkalmazás, ezért ez platform független.
... (work in progress)

## 4. Funkcionális terv
### Kliens program
A program indulásakor a felhasználónak be kell jelentkezni. Sikeres bejelentkezés után előrhetővé válik a vászon.

### Szerver program
A programnak fogadnia kell a kliens általi TCP kapcsolódási kérvényeket. Ezek után ha a kliens oldali felhasználó helyes bejelentkezési adatokat küldd el, akkor a fiókkal kapcsolatus funkciók elérhetővé vállnak. A szerver program egy konzolos alkalmazás.

... (work in progress)

## 5. Fizikai környezet

### Vásárolt softwarekomponensek és külső rendszerek
Nincsenek vásárolt szoftverkomponensek, egyetemi hallgatói azonosítóval elérhetőek ingyenesen.
Linode szerver

### Fejlesztő eszközök
IntelliJ Idea, Visual Studio Code

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

### Log tábla
 * ID
 * Dátum
 * Fontossági szint
 * Esemény leírása
 
(work in progress)

## 8. Implementációs terv

### Kliens
 * Java ablakos program.
 * A felhasználói felület - Java GUI alkalmazás Swift segítségével.
 * A programok objektum orientált programozási paradigma használatával.
 * Alkalmazott Tervezési minták : (work in progress)

### Szerver
 * Java konzolos program.
 * A programok objektum orientált programozási paradigma használatával.
 * Alkalmazott Tervezési minták : (work in progress)

## 9. Tesztterv

A tesztelések célja a rendszer és komponensei funkcionalitásának teljes vizsgálata,
ellenőrzése a rendszer által megvalósított üzleti szolgáltatások verifikálása.
A teszteléseket a fejlesztői csapat minden tagja elvégzi.
Egy teszt eredményeit a tagok dokumentálják külön fájlokba.

### 9.1 A tesztelési jegyzőkönyv kitöltésére egy sablon:

**Tesztelő:** Vezetéknév Keresztnév
**Tesztelési fajta** (Unit teszt, funkcionális teszt)

Tesztszám | Rövid leírás | Várt eredmény | Eredmény | Megjegyzés
----------|--------------|---------------|----------|-----------
például. Teszt #01 | Bejelentkezés | A felhasználó az adatok megadásával sikeresen be tud jelentkezni | A felhasználó sikeresen bejelentkezett | Nem találtam problémát.
... | ... | ... | ... | ...

## 10. Telepítési terv

### Kliens
Szoftver telepítési terv: Szükségünk van egy számítógépre, amelyen el tudjuk indítani az alkalmazást. 

### Szerver
Szoftver telepítési terv: A szervernek futtatnia kell java alkalmazásokat, mysql adatbázist.

(work in progress)

## 11. Karbantartási terv

Fontos ellenőrizni:
*	Adatok elmentése, megjelenítése jól működik.
*	A program nem lassul be.
*	A szerver megfelelően működik.

Figyelembe kell venni a felhasználó által jött visszajelzést is a programmal kapcsolatban.
Ha hibát talált, mielőbb orvosolni kell, lehet az:
*	Működéssel kapcsolatos
*	Kinézet, dizájnnal kapcsolatos
