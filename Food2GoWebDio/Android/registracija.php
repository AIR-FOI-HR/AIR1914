<?php
require baza.php

define('USER_CREATED', 101);
define('USER_EXIST', 102);
define('USER_FAILURE', 103);

public function kreirajKorisnika($ime, $prezime, $korisnickoIme, $lozinka, $oib, $email, $adresa, $brojMobitela){
    if(!$this->isMailExist($email)){
        $stdm = $this->connection->prepare("INSERT INTO Korisnik (Ime, Prezime, KorisnickoIme, Lozinka, OIB, Email, Adresa, BrojMobitela, BrojPokusaja, StatusKorisnika, Aktivacijski_kod, Broj_bodova, Tip_korisnikaID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0, 0, '00000', 0, 2)");
        $stmt->bind_param("ssssssss", $ime, $prezime, $korisnickoIme, $lozinka, $oib, $email, $adresa, $brojMobitela);
        
        if($stmt->execute()){    
            return USER_CREATED;
        }else{
            return USER_FAILURE;
        }
    }
    return USER_EXIST;
}
    
private function isMailExist($email){
    $stmt = $this->connection->prepare("SELECT ID FROM Korisnik WHERE Email = ?");
    $stmt->bind_param("s", $email);
    $stmt->execute();
    $stmt->store_result();
    return $stmt->num_rows > 0;
}
