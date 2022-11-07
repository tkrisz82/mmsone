# mmsone
Sziasztok!

	Nem tudom a frontend-es rész mennyire volt fontos, mivel egy backend-es pozícióról van szó, így azzal sokat nem is foglalkoztam, csak egy kis retro stílust
  adtam neki.

Applikáció leírása:

	A feladathoz három adatbázis táblát használtam:

user:
id(int, pk), name(varchar), email(varchar), pwd(varchar), logedin(int), type(varchar-admin/user)

car:
id(int, pk), model(varchar), plate(varchar), price(int), broken(int)

rent:
id(int, pk), startdate(date), finishdate(date), userid(int, foreign key), carid(int, foreign key), comment(varchar)


localhost:8080/rentacar címen indul az applikáció

Először egy üdvözlő oldal jelenik meg, ahol ki lehet választani, hogy regisztrálni, vagy belépni akar-e az ember. Regisztráció esetén egy regisztráló oldalra visz, 
ahol  nevet, email-t, jelszót kell megadni. A jelszót hash-elve menti az adatbázisba. Regisztráció után jön a belépés. Ha a felhasználó admin, akkor az admin.html-re 
viszi, ha user, akkor az index.html-re, ahol foglalni lehet.

Az admin látja egy táblázatban a flotta összes autóját, külön táblázatban a törött autókat, tud autót hozzáadni az adatbázishoz és onnan törölni, illetve foglalást
is törölni. Autó törlés esetén az adott autó összes foglalását is törli. Ide tettem a riport funkciót is. Nem egészen voltam benne biztos, hogy a heti bontásnak,
hogy kéne kinéznie, ezért úgy oldottam meg, hogy meg lehet adni, a kezdő és a vég időpontot, illetve hogy az összes, vagy csak egy bizonyos autó bérlési időpontjait 
szeretnénk látni az adott időintervallumban. Egy legördülő menüből lehet választani. (Ennél az esetnél egy Sql lekérdezés ”szűri” a dátumot, user foglalásnál az Sql
lekérdezés után csináltam egy dátum ”szűrést”. Gondolom az Sql lekérdezés a jobb megoldás, főleg nagyobb adatbázisnál, de úgy voltam vele, hogy legyen ez is, az is.)
Kezeli a MethodArgumentTypeMismatchException-t, ha nem adnánk meg időpontokat.

A foglaló oldalon, az index.html-en először táblázatba foglalva lehet látni az aktuálisan ép autókat. Úgy gondoltam érdemesebb csak ezeket feltüntetni. A foglaló 
metódusban maradt egy ellenőrzés, ami figyeli az autó állapotát, azt nem vettem ki, mert utólag jutott eszembe, hogy elég lenne az ép autók listája, de ezen könnyen 
lehet változtatni, kinek hogyan szimpatikusabb. 
Foglalás esetén a dátumok és az autó kiválasztása után a foglalás gombra kattintva, ha az autó nem foglalt az adott időintervallumban, akkor kiírja, hogy
„Sikeres foglalás” és a foglalás árát.

Ezen az oldalon lehet rendszám megadásával kárt, illetve javítást bejelenteni. Az autó további foglalásait nem töröltem az adatbázisból. Ha az autó nem készül el az
admin tudja törölni a foglalásokat, illetve magát az autót is.

Mivel foglalni csak bejelentkezett user tud, ezért található még egy Logout gomb az oldal jobb felső sarkában is kijelentkezéshez. Ez van az admin oldalán is.

Próbáltam a kódot több részre bontani, hogy könnyebben átlátható legyen. Azt viszont nem tudom, hogy ez a túl kevés, vagy már a túl sok kategóriába esik.


Ennyi lenne amit a rendelkezésemre álló időben bele tudtam tenni az applikációba. Remélem ez elegendő a pozíció elnyeréséhez.:) Ha azonban mégsem én lennék a
szerencsés kiválasztott, akkor kérhetnék egy pár mondatos értékelést a munkámról? Csak, hogy tudjam, hogy mit is kéne még beleírni egy ilyen feladatba, hogy 
sikeresen pályázzak egy junior állásra. Köszönöm!
