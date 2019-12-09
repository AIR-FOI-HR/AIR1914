<?php
include('funkcije.php');
header("Content-Type:application/json");

if (isset($_GET)) {
    if (!empty($_GET['korisnici'])) {
        if ($_GET['korisnici'] == 'svi') {
            DohvatiSveKorisnike();
        }
    } elseif (!empty($_GET['username']) && !empty($_GET['password'])) {
        DohvatiKorisnika($_GET['username'], $_GET['password']);
    } elseif (isset($_GET['metoda'])) {
        if($_GET["metoda"]=="reg"){
            if (!empty($_GET['ime']) && !empty($_GET['prezime']) && !empty($_GET['korisnickoime']) && !empty($_GET['lozinka']) && !empty($_GET['oib']) && !empty($_GET['email'])) {
                RegistrirajKorisnika(($_GET['ime']), ($_GET['prezime']), ($_GET['korisnickoime']), ($_GET['lozinka']), (($_GET['oib'])), ($_GET['email']), ($_GET['adresa']), ($_GET['brojmobitela']), "0", "0", ($_GET['aktivacijski']), "0", "2");
            }
        }
        elseif ($_GET["metoda"]=="zaboravljena"){
            if (!empty($_GET['email']) && !empty($_GET['username'])) {
                PosaljiLozinku($_GET['email'], $_GET['username']);
            }
        }
        elseif ($_GET["metoda"]=="kod"){
            if (!empty($_GET['email']) && !empty($_GET['aktivacijski'])) {
                AktivirajKorisnika($_GET['email'], $_GET['aktivacijski']);
            }
        }
        elseif ($_GET["metoda"]=="narudbe"){
            if(!empty($_GET['username'])){
                DohvatiNarudzbe($_GET['username']);
            }
        }
        elseif ($_GET["metoda"]=="blokiraj"){
            if (!empty($_GET['username']) && !empty($_GET['blokiraj'])) {
                BlokirajKorisnika($_GET['username'], $_GET['blokiraj']);
            }
        }
        elseif($_GET["metoda"]=="kategorije" AND $_GET["kategorije"]=="sve"){
            DohvatiSveKategorije();
        }
        elseif ($_GET["metoda"]=="artikli"){
            if(!empty($_GET['kategorije'])){
                DohvatiArtiklePoKategoriji($_GET['kategorije']);
            }
        }
        elseif($_GET["metoda"]=="racun" AND $_GET["tip"]=="novi"){
            if(!empty($_GET["korisnikid"])){
                KreirajNoviRacun($_GET["korisnikid"]);
            }

        }
        elseif ($_GET["metoda"]=="dodajnarudbi"){
            if(!empty($_GET["artiklid"])){
                DohvatiArtikl($_GET["artiklid"]);
            }
        }
    }

}
