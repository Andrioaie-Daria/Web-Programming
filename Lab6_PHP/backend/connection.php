<?php
  $server_name = 'localhost';
  $username = 'root';
  $password = '';
  $database_name = 'documents_db';

  $connection = mysqli_connect($server_name, $username, $password, $database_name);
  $mysqli = new mysqli($server_name, $username, $password, $database_name);

  
  if(!$connection){
    die("Connection failed: " . mysqli_connect_error());
  }
?>

