<?php
  require 'connection.php';
  
  try {
      $author = $mysqli->real_escape_string(htmlentities($_POST['author']));
      $title = $mysqli->real_escape_string(htmlentities($_POST['title']));
      $pages = $mysqli->real_escape_string(htmlentities($_POST['pages']));
      $type = $mysqli->real_escape_string(htmlentities($_POST['type']));
      $format = $mysqli->real_escape_string(htmlentities($_POST['format']));

      $sql_insert = "INSERT INTO documents VALUES ('$title', '$author', '$pages', '$type', '$format')";
      $result = mysqli_query($connection, $sql_insert);
      $_SESSION['message'] = "Successful operation!";
  }
  catch(Exception $e){
    echo 'Message: ' . $e->getMessage();
  }
  return header("location: ..");
?>