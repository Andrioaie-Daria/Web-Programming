<?php
  require 'connection.php';
  $type = $mysqli->real_escape_string(htmlentities($_GET["type"]));
  $format = $mysqli->real_escape_string(htmlentities($_GET["format"]));

  if($type === ''){
      $sql_query = "SELECT * FROM documents INNER JOIN formats ON documents.formatId = formats.formatId WHERE formats.name ='{$format}'";
  }
  elseif($format === ''){
    $sql_query = "SELECT * FROM documents INNER JOIN types ON documents.typeId = types.typeId WHERE types.name ='{$type}'";
  }
  else{
    $sql_query = "SELECT * FROM documents INNER JOIN types ON documents.typeId = types.typeId INNER JOIN formats ON documents.formatId = formats.formatId WHERE types.name ='{$type}' AND formats.name ='{$format}'";
  }

  
  $result = mysqli_query($connection, $sql_query);
  if(mysqli_num_rows($result) > 0){
    echo '<div class="container mt-10">'.
           '<div class="row">'.
            '<ul class="list-group">';
    while($row = mysqli_fetch_object($result)){
      echo '<li class="list-group-item">'.
      'Title: ' . $row->title . ',   ' .
      'Author: ' . $row->author . ',   ' .
      'Pages: ' . $row->number_of_pages . ' </li>';
    }
    echo '</ul> </div> </div>';
  }
  

?>