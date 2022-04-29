# Rendszerterv
## 1. A rendszer célja

Programunk célja... (work in progress)

## 3. Üzleti folyamatok modellje

### 3.1 Üzleti szereplők

A rendszer regisztrált fiókkal használható. Csak bejelentkezés után lehet használni a programot.
Annak eldöntése, hogy a szoftvert kinek az eszközére települ és ki használhatja
azt a megrendelő vállalat döntésére van bízva.

## 4. Követelmények

### 4.1 Funkcionális követelmények

| ID | Megnevezés | Leírás |
| --- | --- | --- |
| A1 | Használat | A programot csak az arra jogosultak tudják használni, regisztráció szükséges. |
(work in progress)

### 4.2 Nemfunkcionális követelmények

| ID | Megnevezés | Leírás |
| --- | --- | --- |
| B1 | Letisztult design | A program ablakainak a designja legyen letisztult, átlátható, könnyen használható. Törekszünk a lehető legkényelmesebb megjelenésre.
| Bn | ... | ...

### 4.3 Támogatott eszközök
Mivel Java alkalmazás, ezért ez platform független.
... (work in progress)

## 5. Funkcionális terv
A program indulásakor a felhasználónak be kell jelentkezni. Sikeres bejelentkezés után előrhetővé válik a vászon.
... (work in progress)

## 6. Fizikai környezet

### Vásárolt softwarekomponensek és külső rendszerek
Nincsenek vásárolt szoftverkomponensek, egyetemi hallgatói azonosítóval elérhetőek ingyenesen.

### Fejlesztő eszközök
IntelliJ Idea, Visual Studio Code

## 8. Architekturális terv

### 8.1 Webszerver
TCP kapcsolaton keresztül a kliens kapcsolódik a szerverhez.

### 8.2 Adatbázis rendszer
Szerver-Kliens alkalmazás, a szerver gondoskodik a biztonságos adatbázis kapcsolatról.
A szerver MySQL adatbázist használ.

## 9. Adatbázis terv

Adatbázis terv részletei (work in progress)

## 10. Implementációs terv

 * Java ablakos program.
 * A felhasználói felület - Java GUI alkalmazás Swift segítségével.
 * A programok objektum orientált programozási paradigma használatával.
 * Alkalmazott Tervezési minták : (work in progress)

## 11. Tesztterv

A tesztelések célja a rendszer és komponensei funkcionalitásának teljes vizsgálata,
ellenőrzése a rendszer által megvalósított üzleti szolgáltatások verifikálása.
A teszteléseket a fejlesztői csapat minden tagja elvégzi.
Egy teszt eredményeit a tagok dokumentálják külön fájlokba.

### 11.1 A tesztelési jegyzőkönyv kitöltésére egy sablon:

**Tesztelő:** Vezetéknév Keresztnév
**Tesztelési fajta** (Unit teszt, funkcionális teszt)

Tesztszám | Rövid leírás | Várt eredmény | Eredmény | Megjegyzés
----------|--------------|---------------|----------|-----------
például. Teszt #01 | Bejelentkezés | A felhasználó az adatok megadásával sikeresen be tud jelentkezni | A felhasználó sikeresen bejelentkezett | Nem találtam problémát.
... | ... | ... | ... | ...

## 12. Telepítési terv

(work in progress)

Szoftver telepítési terv: Szükségünk van egy számítógépre, amelyen el tudjuk indítani az alkalmazást. 

## 13. Karbantartási terv

Fontos ellenőrizni:
*	Adatok elmentése, megjelenítése jól működik.
*	A program nem lassul be.
*	A szerver megfelelően működik.

Figyelembe kell venni a felhasználó által jött visszajelzést is a programmal kapcsolatban.
Ha hibát talált, mielőbb orvosolni kell, lehet az:
*	Működéssel kapcsolatos
*	Kinézet, dizájnnal kapcsolatos
