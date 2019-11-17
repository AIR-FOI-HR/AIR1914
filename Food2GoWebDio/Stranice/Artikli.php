<?php
include ('zaglavlje.php');
include ('../Backend/ArtikliBack.php');
$artikl=[];

?>
<br>
<div class="container h-100" >

    <p class="h2 text-center"> Upravljanje artiklima </p><hr><br>
    <div class="row h-100 justify-content-center align-items-center ">
        <form class="col-12" method="post" name="upravljanjeArtiklima" id="artikli">
            <?php
            DohvatiArtikle();
            ?>
            <br>
            <br>
            
        <form class="col-12" method="post" id="formaZaRadArtiklima">
            <p  class="h3 text-center">Novi artikl</p><hr>';

            <br><br>

            <div class="form-group row">
                <label for="nazivArtikla" class="col-sm-2 col-form-label">Naziv :</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="nazivArtikla" name="nazivArtikla" placeholder="Unesite naziv artikla" required>
                </div><br>

            </div>
            <div class="form-group row">
                <label for="cijenaArtikla" class="col-sm-2 col-form-label">Cijena :</label>
                <div class="col-sm-9">
                    <input type="number" min="1" max="1000"  value="10" class="form-control" id="cijenaArtikla" name="cijenaArtikla" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="kolicinaRobe" class="col-sm-2 col-form-label">Kolicina :</label>
                <div class="col-sm-9">
                    <input type="number" min="1" max="1000" value="10" class="form-control" id="kolicinaRobe" name="kolicinaRobe" required>
                </div>
            </div>
            <div class="form-group row">
                <label for="opisArtikla" class="col-sm-2 col-form-label">Opis :</label>
                <div class="col-sm-9">
                     <textarea  class="form-control" id="opisArtikla"  name="opisArtikla" cols="50" rows="4" ></textarea>
                </div>
            </div>
            <div class="form-group row">
                <label for="vrstaKategorije" class="col-sm-2 col-form-label">Kategorija :</label>
                <div class="col-sm-9">
                    <?php DohvatiKategorije() ?>
                </div>
            </div>

            <div style="margin: 0 auto" class="form-group row">
                <div class="col-sm-5 align-text-bottom ">
                  <input id="artiklDodaj" name="artiklDodaj" type="submit" class="btn btn-primary btn-lg active " value="Dodaj"></div>


                </div>
        </form>
    </div></div>

<?php
include ('podnozje.php');
?>
