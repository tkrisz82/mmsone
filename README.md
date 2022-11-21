# rentacar



Felhasznált technológiák, speciális funkciók: Java, Spring Boot, MySQL, Hibernate, AWS, REST, XML, Password hash, Exception kezelés, HTML, CSS 



Applikáció leírása:

A forntend nagyon minimál retro stílusban készült. Nem az volt a fő prioritás.

Ez egy alap autókölcsönző alkalmazás. Nagyon egyszerűen megfogalmazva, csak regisztrált, belépett felhasználó kölcsönözhet adott időpontban szabad, nem törött autót. Adminként bejelentkezve egyéb funkciók is elérhetők: riport, törlés...

Az alkalmazás és a fukciók részletesebb leírása lentebb található.

Az adatbázis AWS-en keresztül érhető el, így néha kicsit gondolkodós.

Három táblát használtam, melyek a rentacardb adatbázisban találhatók:

user:
id(int, pk), name(varchar), email(varchar), pwd(varchar), logedin(int-1/0), type(varchar-admin/user)

car:
id(int, pk), model(varchar), plate(varchar), price(int), broken(int-1/0)

rent:
id(int, pk), startdate(date), finishdate(date), userid(int, foreign key), carid(int, foreign key), comment(varchar)




localhost:8080/rentacar címen indul az applikáció

Először egy üdvözlő oldal jelenik meg, ahol ki lehet választani, hogy regisztrálni, vagy belépni akar-e a user. Regisztráció esetén egy regisztráló oldalra visz, 
ahol  nevet, email-t, jelszót kell megadni. A jelszót hash-elve menti az adatbázisba. Regisztráció után jön a belépés. Ha a felhasználó admin, akkor az admin.html-re 
viszi, ha user, akkor az index.html-re, ahol foglalni lehet.

Az admin látja egy táblázatban a flotta összes autóját, külön táblázatban a törött autókat, tud autót hozzáadni az adatbázishoz és onnan törölni, illetve foglalást
is törölni. Autó törlés esetén az adott autó összes foglalását is törli. Ide tettem egy riport funkciót is. Meg lehet adni egy kezdő és egy vég időpontot és ki lehet választani egy legördülő menüből, hogy egy adott típus, vagy az összes autóra vonatkozó foglalást szertnénk látni a megadott időintervallumban. (Ennél az esetnél egy Sql lekérdezés ”szűri” a dátumot, a user foglalásánál az Sql lekérdezés után csináltam egy dátum ”szűrést”. Az Sql lekérdezés a jobb és gyorsabb megoldás, főleg nagyobb adatbázisnál, de úgy voltam vele, hogy legyen ez is, az is.) Kezeli a MethodArgumentTypeMismatchException-t, ha nem adnánk meg időpontokat.

A foglaló oldalon, az index.html-en először táblázatban foglalva lehet látni az aktuálisan ép autókat, amelyekre foglalást lehet leadni. Foglalás esetén a dátumok és az autó kiválasztása után a foglalás gombra kattintva -ha az autó nem foglalt az adott időintervallumban- akkor kiírja, hogy „Sikeres foglalás” és a foglalás árát. Az árat feltünteti Euro-ban is. Ezt a napiarfolyam.hu oldalról az adott napi MNB középárfolyam alapján számolja ki. Sikertelen foglalás esetén is ad tájékoztatást, hogy mi volt a probléma.
Ezen az oldalon lehet rendszám megadásával kárt, illetve javítást bejelenteni. Az autó további foglalásait nem töröltem az adatbázisból kár bejelentés esetén. Ha az autó nem készül el az admin tudja törölni a foglalásokat, illetve magát az autót is.

Mivel a funkciókat használni csak bejelentkezett user tudja, ezért található az oldalon egy Logout gomb is a kijelentkezéshez.


