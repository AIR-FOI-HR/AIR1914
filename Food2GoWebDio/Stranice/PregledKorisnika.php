<?php
include("zaglavlje.php");
include("../Backend/DohvacanjeKorisnika.php")
?>


<?php

echo '<br/>';?>
<br>

    <p class="h2 text-center"> Upravljanje korisnicima </p><hr><br>

        <form class="col-12" method="post" name="upravljanjeArtiklima" id="artikli" style="margin: 0 auto">
           <?php TablicaKorisnika();
            ?>

            <p>0 je blokiran
                <br>
                1 je aktivan
            </p>
    </form>



<?php
include ('podnozje.php')
?>