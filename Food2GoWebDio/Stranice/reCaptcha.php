<?php
if (isset($_POST['g-recaptcha-response'])) {
    if (!empty($_POST['g-recaptcha-response'])) {
        $kljuc = "6LfdUVwUAAAAAFfSoobKMI5MGbB4-z-5qBMSeZEn";
        $ip = $_SERVER['REMOTE_ADDR'];
        $captcha = $_POST['g-recaptcha-response'];
        $odrediste = file_get_contents("https://www.google.com/recaptcha/api/siteverify?secret=$kljuc&response=$captcha&remoteip$ip");
        $polje = json_decode($odrediste, TRUE);
    }
}