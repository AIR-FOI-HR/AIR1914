<?php
include_once ('funkcije.php');
header("Content-Type:application/json");

if(isset($_GET)){
    if(!empty($_GET['korisnici'])){
        if($_GET['korisnici']=='svi'){
            DohvatiSveKorisnike();
        }
    }
    if(!empty($_GET['username']) && !empty($_GET['password'])  ){
        DohvatiKorisnika($_GET['username'],$_GET['password']);
    }
    if(!empty($_GET['ime']) && !empty($_GET['prezime']) && !empty($_GET['korisnickoime'])&& !empty($_GET['lozinka']) && !empty($_GET['oib'])&& !empty($_GET['email'])
        && !empty($_GET['adresa']) && !empty($_GET['brojmobitela']) && !empty($_GET['brojpokusaja']) && !empty($_GET['status'])  && !empty($_GET['aktivacijski'])
        && !empty($_GET['bodovi']) && !empty($_GET['tipkorisnika'])){
        RegistrirajKorisnika(($_GET['ime']),($_GET['prezime']),($_GET['korisnickoime']),($_GET['lozinka']),($_GET['oib']),($_GET['email']),($_GET['adresa']),($_GET['brojmobitela']),($_GET['brojpokusaja']),($_GET['status']),($_GET['aktivacijski']),($_GET['bodovi']),($_GET['tipkorisnika']));
    }

    if(!empty($_GET['email']) && !empty($_GET['aktivacijski'])){
        AktivirajKorisnika($_GET['email'],$_GET['aktivacijski']);
    }
    if(!empty($_GET['email']) && !empty($_GET['username'])){
        PosaljiLozinku($_GET['email'],$_GET['username']);
    }


}


?>