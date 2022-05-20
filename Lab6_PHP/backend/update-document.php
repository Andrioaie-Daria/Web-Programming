<?php
    require 'connection.php';
  
    try{
      $old_title = $mysqli->real_escape_string(htmlentities($_POST['old_title']));
      $title = $mysqli->real_escape_string(htmlentities($_POST['title']));
      $author = $mysqli->real_escape_string(htmlentities($_POST['author']));
      $pages = $mysqli->real_escape_string(htmlentities($_POST['pages']));
      $type = $mysqli->real_escape_string(htmlentities($_POST['type']));
      $format = $mysqli->real_escape_string(htmlentities($_POST['format']));
      $sql_update = "UPDATE documents
                     SET title='$title', author='$author', number_of_pages=$pages, typeId=$type, formatId=$format
                     WHERE title LIKE '{$old_title}'";
      $result = mysqli_query($connection, $sql_update);               
    }
    catch(Exception $e){
      echo "Message: ".$e->getMessage();
    }
    return header("location: ../update.php");
?>