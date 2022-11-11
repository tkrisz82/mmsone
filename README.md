# rentacar



Felhasznált technológiák, speciális funkciók: Java, Spring Boot, MySQL, Hibernate, REST, XML, Password hash, Exception kezelés, HTML, CSS 

A forntend nagyon minimál retro stílusban készült.

Applikáció leírása:

A feladathoz három adatbázis táblát használtam, melyek a rentacardb adatbázisban találhatóak:

user:
id(int, pk), name(varchar), email(varchar), pwd(varchar), logedin(int-1/0), type(varchar-admin/user)

car:
id(int, pk), model(varchar), plate(varchar), price(int), broken(int-1/0)

rent:
id(int, pk), startdate(date), finishdate(date), userid(int, foreign key), carid(int, foreign key), comment(varchar)


localhost:8080/rentacar címen indul az applikáció

Először egy üdvözlő oldal jelenik meg, ahol ki lehet választani, hogy regisztrálni, vagy belépni akar-e az ember. Regisztráció esetén egy regisztráló oldalra visz, 
ahol  nevet, email-t, jelszót kell megadni. A jelszót hash-elve menti az adatbázisba. Regisztráció után jön a belépés. Ha a felhasználó admin, akkor az admin.html-re 
viszi, ha user, akkor az index.html-re, ahol foglalni lehet.

Az admin látja egy táblázatban a flotta összes autóját, külön táblázatban a törött autókat, tud autót hozzáadni az adatbázishoz és onnan törölni, illetve foglalást
is törölni. Autó törlés esetén az adott autó összes foglalását is törli. Ide tettem egy riport funkciót is. Meg lehet adni egy kezdő és egy vég időpontot és ki lehet választani egy legördülő menüből, hogy egy adott típus, vagy az összes autóra vonatkozó foglalást szertnénk látni a megadott időintervallumban. (Ennél az esetnél egy Sql lekérdezés ”szűri” a dátumot, a user foglalásnál az Sql lekérdezés után csináltam egy dátum ”szűrést”. Az Sql lekérdezés a jobb és gyorsabb megoldás, főleg nagyobb adatbázisnál, de úgy voltam vele, hogy legyen ez is, az is.) Kezeli a MethodArgumentTypeMismatchException-t, ha nem adnánk meg időpontokat.

A foglaló oldalon, az index.html-en először táblázatba foglalva lehet látni az aktuálisan ép autókat, amelyekre lehet foglalást leadni. Úgy gondoltam érdemesebb csak ezeket feltüntetni. A foglaló metódusban maradt egy ellenőrzés, ami figyeli az autó állapotát, azt nem vettem ki, mert utólag jutott eszembe, hogy elég lenne az ép autók listája, de ezen könnyen lehet változtatni, kinek hogyan szimpatikusabb. 
Foglalás esetén a dátumok és az autó kiválasztása után a foglalás gombra kattintva, ha az autó nem foglalt az adott időintervallumban, akkor kiírja, hogy
„Sikeres foglalás” és a foglalás árát. Az árat feltünteti Euro-ban is. Ezt a napiarfolyam.hu oldalról az adott napi MNB középárfolyam alapján számolja ki.
Ezen az oldalon lehet rendszám megadásával kárt, illetve javítást bejelenteni. Az autó további foglalásait nem töröltem az adatbázisból kár bejelentés esetén. Ha az autó nem készül el az admin tudja törölni a foglalásokat, illetve magát az autót is.

Mivel foglalni csak bejelentkezett user tud, ezért található még egy Logout gomb az oldal jobb felső sarkában is kijelentkezéshez. Ez van az admin oldalán is.


