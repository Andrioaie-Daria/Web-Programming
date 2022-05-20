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
                    'Pages: ' . $row->number_of_pages . '  ' . '<a href="backend/delete-document.php?title='.$row->title.'" id="delete-button" class="float-end btn btn-outline-warning">Delete</a> </li>';
            }

            // while($row = mysqli_fetch_object($result)){
            //   echo '<li class="list-group-item">'.
            //         'Title: ' . $row->title . ',   ' .
            //         'Author: ' . $row->author . ',   ' .
            //         'Pages: ' . $row->number_of_pages . '  ' . '<button onclick= id="delete-button" class="float-end btn btn-outline-warning">Delete</button> </li>';
            // }
        ?>
      </ul>
      <button class="btn btn-outline-dark" onclick="location.href = './index.php';"">Go back</button>
    </div>
  </div>

  <!-- <script>
    function delete_document(title_value){
        $.get("backend/delete-document.php", 
              {title : title_value});
    }
  </script>   -->
</body>
</html>