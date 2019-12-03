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

}


?>