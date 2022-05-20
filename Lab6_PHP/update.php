<?php
  require 'backend/connection.php';

  $select_query = "SELECT * FROM documents";
  $result = mysqli_query($connection, $select_query);
?>

<!DOCTYPE html>
<html>
<head>
    <?php include 'imports.php'; ?>
</head>
<body>
  <div class="container mt-10">
    <div class="row">
      <ul class="list-group" style="margin-top:70px">
        <?php
            while($row = mysqli_fetch_object($result)){
              echo '<li class="list-group-item">'.
                    'Title: ' . $row->title . ',   ' .
                    'Author: ' . $row->author . ',   ' .
                    'Pages: ' . $row->number_of_pages . '  ' . '<a href="./update-selected-document.php?title='.$row->title. '"  class="float-end btn btn-outline-info">Edit</a> </li>';
            }
        ?>
      </ul>
      <button class="btn btn-outline-dark" onclick="location.href = './index.php';">Go back</button>
    </div>
  </div>
</body>
</html>