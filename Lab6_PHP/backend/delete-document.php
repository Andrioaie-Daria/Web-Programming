<?php
  require 'connection.php';

  try{
    $title = $mysqli->real_escape_string(htmlentities($_GET['title']));
    $sql_delete = "DELETE FROM documents WHERE title LIKE '{$title}'";
    $result = mysqli_query($connection, $sql_delete);
  }
  catch(Exception $e){
    echo 'Message: ' . $e->getMessage();
  }
  return header("location:".$_SERVER['HTTP_REFERER']);

?>