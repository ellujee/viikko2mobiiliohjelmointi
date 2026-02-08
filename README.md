#Viikko 2

Tavoitteena oli päivittää aiempaa sovellusta niin, että se käyttää ViewModelia. Sovelluksessa voi lisätä uusia tehtäviä, merkitä niitä tehdyiksi, poistaa tehtäviä ja järjestellä niitä. Composessa näyttö päivittyy automaattisesti, kun siellä oleva tieto muuttuu. Lista tehtävistä on tallennettu ViewModeliin mutableStateOf-muuttujaan.
Kun listaan lisätään tehtävä tai checkboxia painetaan, Compose huomaa muutoksen ja näyttää näytön heti uudestaan uusilla tiedoilla. Remember muistaa asiat vain niin kauan kuin sovellus pysyy samassa tilassa. Jos esimerkiksi käännät puhelimen pystyasennosta vaaka-asentoon, remember unohtaa kaiken ja lista tyhjenee. ViewModel taas pysyy tallessa, vaikka näyttöä käännettäisiin tai sovelluksessa tapahtuisi muita muutoksia. Se on siis turvallisempi paikka säilyttää tärkeää tietoa kuin pelkkä remember.

#Viikko 3

MVVM (Model-View-ViewModel) on arkkitehtuurimalli, joka erottaa sovelluksen käyttöliittymän, logiikan ja datan toisistaan.

-Model: Sisältää datan ja liiketoimintalogiikan.

-View: Piirtää käyttöliittymän datan perusteella

-ViewModel: Toimii välittäjänä. Se säilyttää sovelluksen tilan ja hoitaa logiikan.

Compose on suunniteltu reagoimaan tilan muutoksiin. Kun ViewModelissa oleva data muuttuu, Compose päivittää vain tarvittavat osat näytöstä automaattisesti.
StateFlow näyttää aina sovelluksen senhetkisen tilan, se lähettää automaattisesti viestin käyttöliittymälle, joka päivittää itsensä heti näyttämään uudet tiedot.

#Viikko 4

-Navigointi Jetpack Composessa tarkoittaa sitä, miten sovelluksessa liikutaan eri näkymien välillä ilman, että koko sovellus käynnistyy uudestaan. Se vaihtaa vain ruudulla näkyvän sisällön.

-NavController: Se on Jetpack Composen keskeinen työkalu, joka pitää kirjaa sovelluksen tilasta, kuten navigointihistoriasta ja siitä, mikä näkymä on parhaillaan auki. Sen avulla suoritetaan varsinaiset siirtymät näkymien välillä.

-NavHost: Näkymässä sijaitseva ikkuna, jonka sisällä eri näkymät vaihtuvat. Se yhdistää NavControllerin navigointikaavioon, joka määrittelee kaikki mahdolliset kohteet sovelluksessa.

-Sovelluksen navigaatio on toteutettu NavHost-komponentilla, johon on määritelty omat reitit näkymille. Käyttäjä voi siirtyä näiden välillä alareunan navigointipalkin kautta.

-Sovelluksessa käytetään MVVM-mallia siten, että molemmat ruudut käyttävät yhtä ja samaa ViewModelia. Navigoinnin yhteydessä molemmille ruuduille annetaan sama ViewModel-olio, jolloin molemmat näkevät tismalleen saman datan.

-Kalenterinäkymä on toteutettu niin, että tehtävät on ryhmitelty päivämäärien mukaan. Sovellus käy läpi tehtävälistan ja näyttää kunkin päivän kohdalla siihen kuuluvat menot. Tehtävien lisääminen ja muokkaaminen on hoidettu AlertDialog-ikkunalla, joka ponnahtaa näytölle. Kun painat tallenna-nappia, se kutsuu ViewModelin addTask- tai editTask-toimintoa, joka päivittää listan ja sulkee ikkunan automaattisesti.

Käyttöohjeet:

Lataa koodi koneellesi tai hae se Gitistä. Avaa projekti Android Studiossa, valitse yläpalkista vihreä Run-nappi, sovellus aukeaa emulaattoriin.

