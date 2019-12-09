<?php
require("../Baza/Baza.php");

function dohvatiRezultate($sql)
{
    $baza = new Baza();
    $baza->spojiDB();
    $rezultat = $baza->selectDB($sql);
    $baza->zatvoriDB();
    return $rezultat;
}

function izvrsiUpit($sql)
{
    $baza = new Baza();
    $baza->spojiDB();
    $baza->updateDB($sql);
    $baza->zatvoriDB();

}

function vratiOdgovor($status, $brojRedaka, $poruka, $podaci)
{
    $odgovor = ["status" => $status, "brojRedaka" => $brojRedaka, "poruka" => $poruka, "podaci" => $podaci];
    echo json_encode($odgovor);
}

function DohvatiSveKorisnike()
{
    $polje = dohvatiRezultate("SELECT * FROM Korisnik");
    $brojRedova = mysqli_num_rows($polje);
    $status = "OK";
    $red = [];
    $final = [];
    $poruka = "Korisinici su pronadjeni";
    if ($brojRedova > 0) {
        while ($row = $polje->fetch_assoc()) {
            $red['id'] = $row["ID"];
            $red["username"] = $row["KorisnickoIme"];
            $red["lozinka"] = $row["Lozinka"];
            $final[] = $red;
        }

    }
    vratiOdgovor($status, $brojRedova, $poruka, $final);

}

function DohvatiKorisnikaReg($user, $email, $oib)
{
    $polje = dohvatiRezultate("SELECT *FROM Korisnik WHERE KorisnickoIme='$user' OR Email='$email' OR OIB='$oib'");
    $brojRedova = mysqli_num_rows($polje);

    return ($brojRedova == 0) ? false : true;

}

function BlokirajKorisnika($user, $blokiraj)
{
    $status = "OK";
    $red = [];
    $final = [];
    $poruka = "Nije uspjelo";
    if ($blokiraj == "da") {
        $upit1 = "UPDATE Korisnik SET StatusKorisnika=2 WHERE KorisnickoIme='$user'";
        izvrsiUpit($upit1);
        $status = "Nije OK";
        $poruka = "Korisnik je blokiran!";
    } else {
        $upit2 = "UPDATE Korisnik SET StatusKorisnika=1 WHERE KorisnickoIme='$user'";
        izvrsiUpit($upit2);
        $upit3 = "UPDATE Korisnik SET BrojPokusaja=0 WHERE KorisnickoIme='$user'";
        izvrsiUpit($upit3);
        $poruka = "Korisnik je odblokiran!";
    }
    vratiOdgovor($status, 0, $poruka, $final);

}

function BlokirajKorisnikaPrijePrijave($user)
{
    $korisnik = dohvatiRezultate("SELECT * FROM Korisnik WHERE KorisnickoIme='$user'");
    $brojRedova = mysqli_num_rows($korisnik);
    //korisnik postoji
    if ($brojRedova > 0) {
        while ($row = $korisnik->fetch_assoc()) {
            if ($row['BrojPokusaja'] >= 3) {
                $upit1 = "UPDATE Korisnik SET StatusKorisnika=1 WHERE KorisnickoIme='$user'";
                izvrsiUpit($upit1);
                $upit2 = "UPDATE Korisnik SET BrojPokusaja=0 WHERE KorisnickoIme='$user'";
                izvrsiUpit($upit2);
            }
        }
    }

}

function DohvatiKorisnika($user, $pass)
{
    BlokirajKorisnikaPrijePrijave($user);
    $final = [];
    $korisnik = dohvatiRezultate("SELECT * FROM Korisnik WHERE KorisnickoIme='$user'");
    $brojRedova = mysqli_num_rows($korisnik);
    //korisnik postoji
    if ($brojRedova > 0) {
        while ($row = $korisnik->fetch_assoc()) {
            $status = "OK";
            $red = [];
            $final = [];
            if ($row["Lozinka"] == $pass && $row["StatusKorisnika"] > 1) {
                $red['id'] = $row["ID"];
                $red["username"] = $row["KorisnickoIme"];
                $red["lozinka"] = $row["Lozinka"];
                $red["status"] = $row["StatusKorisnika"];
                $red['brojPokusaja'] = 0;
                $final[] = $red;
                $upit1 = "UPDATE Korisnik SET BrojPokusaja= 0 WHERE KorisnickoIme='$user' and Lozinka = '$pass'";
                izvrsiUpit($upit1);
                $poruka = "Korisnik je pronaden i spreman za prijavu!";
                vratiOdgovor($status, $brojRedova, $poruka, $final);
            } //korisnik postoji, no prijava iz nekog slucaja ne prolazi
            else {
                //blokiran
                if ($row["StatusKorisnika"] == 1) {
                    $status = "Nije OK";
                    $poruka = "Korisnik je pronaden, ali je blokiran!";
                    vratiOdgovor($status, $brojRedova, $poruka, $final);
                } //nije aktivan
                elseif ($row["StatusKorisnika"] == 0) {
                    $status = "Nije OK";
                    $poruka = "Korisnik je pronaden, ali nije aktiviran!";
                    vratiOdgovor($status, $brojRedova, $poruka, $final);

                } else {
                    $upit2 = "UPDATE Korisnik set BrojPokusaja=BrojPokusaja+1 WHERE KorisnickoIme='$user' ";
                    $status = "Nije OK";
                    $poruka = "Korisnik je pronaden, ali kriva je lozinka";
                    izvrsiUpit($upit2);
                    vratiOdgovor($status, $brojRedova, $poruka, $final);
                }
            }
        }
    } //korisnik ne postoji
    else {
        $poruka = "Korisnik nije pronaden";
        $brojRedova = 0;
        $status = "Nije OK";
        vratiOdgovor($status, $brojRedova, $poruka, $final);
    }
}

function posaljiKod($akt, $mail)
{
    $kome = $mail;
    $naslov = "Aktivacijski kod";
    $sadrzaj = "Uspjesno ste registrirani, vas registracijski kod je " . $akt . " i traje 24 sata";
    $od = "From: igradiski@foi.hr";
    mail($kome, $naslov, $sadrzaj, $od);
}

function posaljiLozinkuMail($pass, $mail)
{
    $kome = $mail;
    $naslov = "Lozinka";
    $sadrzaj = "Vaša lozinka je:" . $pass . "";
    $od = "From: igradiski@foi.hr";
    mail($kome, $naslov, $sadrzaj, $od);
}

function RegistrirajKorisnika($ime, $prezime, $username, $password, $oib, $email, $adresa, $brojmobitela, $brojpokusaja, $status, $aktivacijski, $bodovi, $tipkorisnika)
{
    $final = [];

    if (DohvatiKorisnikaReg($username, $email, $oib) == false) {
        //LUPI REGISTRACIJU SAD
        $upit = "INSERT INTO Korisnik values(DEFAULT,'$ime','$prezime','$username','$password','$oib','$email','$adresa','$brojmobitela','" . $brojpokusaja . "','$status','$aktivacijski','$bodovi','$tipkorisnika')";
        izvrsiUpit($upit);
        $status = "OK";
        $poruka = "Korisnik je registriran";
        posaljiKod($aktivacijski, $email);
    } else {
        $status = "Nije OK";
        $poruka = "Parametri nisu ispravno uneseni";
    }
    vratiOdgovor($status, 0, $poruka, $final);

}

function DohvatiKorisnikaAkt($email, $aktivacijski)
{
    $polje = dohvatiRezultate("SELECT *FROM Korisnik WHERE Email='$email' AND AktivacijskiKod='$aktivacijski' AND StatusKorisnika=0");
    $brojRedova = mysqli_num_rows($polje);

    return ($brojRedova == 0) ? false : true;

}

function AktivirajKorisnika($mail, $aktivacijski)
{

    if (DohvatiKorisnikaAkt($mail, $aktivacijski) == true) {
        $sql = "UPDATE Korisnik set StatusKorisnika=2 WHERE Email='$mail' AND AktivacijskiKod='$aktivacijski'";
        izvrsiUpit($sql);
        $status = "OK";
        $red = [];
        $final = [];
        $poruka = "Korisnik je aktiviran";
    }else{
        $status="Nije OK";
        $poruka="Unijeli ste krive podatke ili je korisnik vec aktiviran";
        $final = [];
    }

    vratiOdgovor($status, 0, $poruka, $final);
}

function stvoriLozinku()
{
    $code1 = md5("PasswordSalt" . microtime());
    $pass = substr($code1, 0, 10);
    return $pass;
}

function PosaljiLozinku($mail, $username)
{
    $pass = stvoriLozinku();
    $polje = izvrsiUpit("UPDATE Korisnik SET Lozinka ='$pass' WHERE KorisnickoIme='$username' AND Email = '$mail'");
    $polje = dohvatiRezultate("SELECT * FROM Korisnik WHERE KorisnickoIme='$username' AND Email = '$mail'");
    $brojRedova = mysqli_num_rows($polje);
    $status = "OK";
    $red = [];
    $final = [];
    $poruka = "Mail s lozinkom je poslan";
    if ($brojRedova > 0) {
        while ($row = $polje->fetch_assoc()) {
            posaljiLozinkuMail($row["Lozinka"], $row['Email']);
        }
    } else {
        $status = "Nije OK";
        $poruka = "Ne postoji taj korisnik";

    }
    vratiOdgovor($status, $brojRedova, $poruka, $final);
}


function KorisnikGetID($user)
{
    $ID = "";
    $korisnik = dohvatiRezultate("SELECT * FROM Korisnik WHERE KorisnickoIme='$user'");
    $brojRedova = mysqli_num_rows($korisnik);
    if ($brojRedova > 0) {
        while ($row = $korisnik->fetch_assoc()) {
            return $ID = $row['ID'];
        }
    }
    return $ID;
}
function DohvatiNarudzbe($username){
    $final = [];
    $korisnikID = KorisnikGetID($username);
    $racuni = dohvatiRezultate("SELECT * FROM Racun WHERE Korisnik_ID='$korisnikID'");
    $brojRedova = mysqli_num_rows($racuni);
    $artikl=[];
    $racun=[];
    $broj=1;
    if ($brojRedova > 0) {
        while ($row = $racuni->fetch_assoc()) {
            $id=$row["ID"];
            $artikli=dohvatiRezultate("
                SELECT Artiikl_Temporalno_Cijena.Cijena AS 'Cijena',Artikl.Naziv AS 'Naziv',Artikl.Opis AS 'Opis',StavkeRacuna.Kolicina AS 'Kolicina',
                Racun.BrojRacuna AS 'BrojRacuna',Racun.Ukupno AS 'Ukupno', Racun.Datum AS 'Datum'
                FROM Artiikl_Temporalno_Cijena RIGHT JOIN
                 Artikl ON Artiikl_Temporalno_Cijena.ID= Artikl.Artiikl_Temporal_ID RIGHT JOIN
                 StavkeRacuna ON StavkeRacuna.Artikl_ID=Artikl.ID 
                RIGHT JOIN Racun ON StavkeRacuna.Racun_ID=Racun.ID WHERE Racun.ID='$id'");
            $brojRedovaArtikla=mysqli_num_rows($artikli);
            if($brojRedovaArtikla>0){
                while ($rowArtikl = $artikli->fetch_assoc()) {
                    $artikl["BrojRacuna"]=$rowArtikl["BrojRacuna"];
                    $artikl["Naziv"]=$rowArtikl["Naziv"];
                    $artikl["Cijena"]=$rowArtikl["Cijena"];
                    $artikl["Opis"]=$rowArtikl["Opis"];
                    $artikl["Kolicina"]=$rowArtikl["Kolicina"];
                    $artikl["Ukupno"]=$rowArtikl["Ukupno"];
                    $artikl["Datum"]=$rowArtikl["Datum"];
                    $racun[]=["Artikl"=>$artikl];
                }
            }
            $final[]=["Racun"=>$racun];
            $racun=[];

        }

    }
    else {
    $status = "Nije OK";
    $brojRedova = 0;
    $poruka = "Greska kod dohvacanja racuna";
    }
    $status = "OK";
    $brojRedova = $brojRedova;
    $poruka = "Dohvaceno";
vratiOdgovor($status, $brojRedova, $poruka, $final);
}


function DohvatiSveKategorije(){
    $polje = dohvatiRezultate("SELECT * FROM Kategorija");
    $brojRedova = mysqli_num_rows($polje);
    $status = "OK";
    $red = [];
    $final = [];
    $poruka = "Kategorije su pronadene";
    if ($brojRedova > 0) {
        while ($row = $polje->fetch_assoc()) {
            $red['id'] = $row["ID"];
            $red["Naziv"] = $row["Naziv"];
            $final[] = $red;
        }
    }
    vratiOdgovor($status, $brojRedova, $poruka, $final);
}

function DohvatiArtiklePoKategoriji($kategorija){
    $polje = dohvatiRezultate("SELECT * FROM Artikl WHERE KategorijaID='$kategorija'");
    $brojRedova = mysqli_num_rows($polje);
    $status = "OK";
    $red = [];
    $final = [];
    $poruka = "Artikli su pronadeni!";
    if ($brojRedova > 0) {
        while ($row = $polje->fetch_assoc()) {
            $red['ID'] = $row["ID"];
            $red["Naziv"] = $row["Naziv"];
            $red["Kolicina"]=$row["Kolicina"];
            $red["MinimalnaKolicina"]=$row["MinimalnaKolicina"];
            $red["Slika"]=$row["Slika"];
            $red["Opis"]=$row["Opis"];

            $idTemp=$row["Artiikl_Temporal_ID"];
            $polje2 = dohvatiRezultate("SELECT * FROM Artiikl_Temporalno_Cijena WHERE ID='$idTemp'");
            $brojRedova2 = mysqli_num_rows($polje2);
            if ($brojRedova2 > 0) {
                while ($row2 = $polje2->fetch_assoc()) {
                    $red["Cijena"]=$row2["Cijena"];
                    $red["VrijediOD"]=$row2["VrijediOD"];
                    $red["VrijediDO"]=$row2["VrijediDO"];
                }
            }
            $final[] = $red;
        }
    }
    else{
        $poruka = "Artikli nisu pronadeni!";
    }
    vratiOdgovor($status, $brojRedova, $poruka, $final);
}
function GenerirajRandomBrojRacuna(){
    $broj=mt_rand(1111111, 9999999);
    $polje = dohvatiRezultate("SELECT * FROM Racun WHERE BrojRacuna='$broj'");
    $brojRedova = mysqli_num_rows($polje);
    if ($brojRedova > 0) {
        GennerirajRandomBrojRacuna();
    }
    else{
        return $broj;
    }
}
function KreirajNoviRacun($user){
    $brojRacuna=GenerirajRandomBrojRacuna();
    $date= (new \DateTime())->format('Y-m-d H:i:s');
    $upit = "INSERT INTO Racun VALUES(DEFAULT,'$brojRacuna',1,1,'$date',0,'$user',0,1,4,0)";
    izvrsiUpit($upit);
    $status = "OK";
    $red = [];
    $final = [];
    $poruka="Inicijalna narudba je kreirana!";
    $polje= dohvatiRezultate("SELECT * FROM Racun ORDER BY id DESC LIMIT 1");
    $brojRedova = mysqli_num_rows($polje);
    if ($brojRedova > 0) {
        while ($row = $polje->fetch_assoc()) {
            $final["ID"]=$row["ID"];
        }
    }
    vratiOdgovor($status, 1, $poruka, $final);
}
function DohvatiArtikl($id){

}

