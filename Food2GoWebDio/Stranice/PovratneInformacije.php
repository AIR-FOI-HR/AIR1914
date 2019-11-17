<?php
include("zaglavlje.php");
include("../Backend/PovranteInformacijeBack.php");
if(isset($_POST['nagrada'])){
    DodajBodove();
}



?>
<br>
<div class="container h-100" >
    <p class="h2 text-center">Povratne informacije</p><hr><br>
    <?php
    PrikaziPovratneInformacije();
    ?>
    <br>