<?php
require ("../Baza/Baza.php");

function dohvatiRezultate($sql){
    $baza = new Baza();
    $baza->spojiDB();
    $rezultat=$baza->selectDB($sql);
    $baza->zatvoriDB();
    return $rezultat;
}

function izvrsiUpit($sql){
    $baza = new Baza();
    $baza->spojiDB();
    $baza->updateDB($sql);
    $baza->zatvoriDB();

}

function vratiOdgovor($status, $brojRedaka, $poruka, $podaci){
    $odgovor = ["status"=>$status,"brojRedaka"=>$brojRedaka,"poruka"=>$poruka, "podaci"=>$podaci];
    echo json_encode($odgovor);
}

function DohvatiSveKorisnike(){
    $polje=dohvatiRezultate("SELECT * FROM Korisnik");
    $brojRedova=mysqli_num_rows($polje);
    $status="OK";
    $red=[];
    $final=[];
    $poruka="Korisinici su pronadjeni";
    if ($brojRedova>0){
        while($row=$polje->fetch_assoc()){
            $red['id']=$row["ID"];
            $red["username"]=$row["KorisnickoIme"];
            $red["lozinka"]=$row["Lozinka"];
            $final[]=$red;
        }

    }
    vratiOdgovor($status,$brojRedova,$poruka,$final);

}
function DohvatiKorisnika($user,$pass){
    $polje=dohvatiRezultate("SELECT * FROM Korisnik WHERE KorisnickoIme='$user' AND Lozinka = '$pass'");
    $brojRedova=mysqli_num_rows($polje);
    $status="OK";
    $red=[];
    $final=[];
    $poruka="Korisnici su pronadjeni";
    if ($brojRedova>0){
        while($row=$polje->fetch_assoc()){
            $red['id']=$row["ID"];
            $red["username"]=$row["KorisnickoIme"];
            $red["lozinka"]=$row["Lozinka"];
            $final[]=$red;
        }
    }
    else{
        $status="Nije OK";
        $poruka="Korisinici nisu pronadjeni";
        
    }
    vratiOdgovor($status,$brojRedova,$poruka,$final);


}
function posaljiKod($akt,$mail){
    $kome=$mail;
    $naslov="Aktivacijski kod";
    $sadrzaj="Uspjesno ste registrirani, vas registracijski kod je ".$akt." i traje 24 sata";
    $od="From: igradiski@foi.hr";
    mail($kome,$naslov, $sadrzaj, $od);
}
function posaljiLozinkuMail($pass,$mail){
    $kome=$mail;
    $naslov="Lozinka";
    $sadrzaj="Vaša lozinka je:".$pass ."";
    $od="From: igradiski@foi.hr";
    mail($kome,$naslov, $sadrzaj, $od);
}
function RegistrirajKorisnika($ime,$prezime,$username,$password,$oib,$email,$adresa,$brojmobitela,$brojpokusaja,$status,$aktivacijski,$bodovi,$tipkorisnika){
    $postojiKorisnik=false;
    $jsonArray = json_decode(DohvatiKorisnika($username,$password));
    foreach ($jsonArray as $value){
        if($value->Poruka=="Korisnici su pronadjeni"){
            $postojiKorisnik=true;
        }
    }
    if($postojiKorisnik==false){
            //LUPI REGISTRACIJU SAD
        $upit="INSERT INTO Korisnik values(DEFAULT,'$ime','$prezime','$username','$password','$oib','$email','$adresa','$brojmobitela','$brojpokusaja','0','$aktivacijski','$bodovi','$tipkorisnika' )";
        izvrsiUpit($upit);
        $status="OK";
        $red=[];
        $final=[];
        $poruka="Korisnik je registriran";
        posaljiKod($aktivacijski,$email);
        vratiOdgovor($status,0,$poruka,$final);
    }
}

function AktivirajKorisnika($mail,$aktivacijski){
    $sql="UPDATE Korisnik set StatusKorisnika=1 WHERE  Email ='$mail' AND Aktivacijski_kod='$aktivacijski'";
    izvrsiUpit($sql);
    $status="OK";
    $red=[];
    $final=[];
    $poruka="Korisnik je registriran";
    vratiOdgovor($status,0,$poruka,$final);
}
function PosaljiLozinku($mail,$username) {
    $polje=dohvatiRezultate("SELECT * FROM Korisnik WHERE KorisnickoIme='$username' AND Email = '$mail'");
    $brojRedova=mysqli_num_rows($polje);
    $status="OK";
    $red=[];
    $final=[];
    $poruka="Mail s lozinkom je poslan";
    if ($brojRedova>0){
        while($row=$polje->fetch_assoc()){
            posaljiLozinkuMail($row["Lozinka"],$row['Email']);
        }
    }
    else{
        $status="Nije OK";
        $poruka="Ne postoji taj korisnik";

    }
    vratiOdgovor($status,$brojRedova,$poruka,$final);
}
