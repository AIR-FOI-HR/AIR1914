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
    $poruka="Korisinici su pronadjeni";
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