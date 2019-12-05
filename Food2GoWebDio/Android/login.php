<?php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
 require "baza.php";

 $user_name = $_POST["userName"];
 $user_pass = $_POST["password"];

 $sql = "SELECT * FROM Korisnik WHERE KorisnickoIme LIKE '$user_name' AND Lozinka LIKE '$user_pass'";

 $response = mysqli_query($connection, $sql);
 $result = array();
 $result['login'] = array();

 if(mysqli_num_rows($response) === 1){
	$row = mysqli_fetch_assoc($response);
	if(password_verify($user_pass, $row['Lozinka'])){
		$index['KorisnickoIme'] = $row['KorisnickoIme'];
		
		array_push($result['login'], $index);
		$result['success'] = "1";
		$result['message'] = "success";
		$result->write(json_encode($result));
		
		mysqli_close($connection);
	}
	else{
		$result['success'] = "0";
		$result['message'] = "error";
		$result->write(json_encode($result));
		
		mysqli_close($connection);
    }
 }
}
?>