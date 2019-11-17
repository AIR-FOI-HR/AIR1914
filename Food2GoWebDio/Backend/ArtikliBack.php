<?php
include ('../Baza/Baza.php');
function DohvatiKategorije(){
    $i=0;
    $baza = new Baza();
    $baza->spojiDB();
    $kategorije = $baza->selectDB("SELECT * FROM `Kategorija` ");
    echo'<select class="form-control" id="kategorija" name="kategorija">';
    while($row=$kategorije->fetch_assoc()){
        $i++;
        echo '<option value="'.$row["ID"].'"';
        if ($i==1){echo'selected="true"';}
        echo'>'.$row["Naziv"].'</option>';
    }
    echo '</select>';

    $baza->zatvoriDB();
}