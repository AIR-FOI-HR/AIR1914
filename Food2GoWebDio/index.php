<?php
session_start();
include("./Stranice/zaglavlje.php");
include ("./Baza/Baza.php");

$veza = new Baza();
$veza->spojiDB();


?>

<h1>Ovo je početna stranica</h1>

<?php

include("./Stranice/podnozje.php");
?>

