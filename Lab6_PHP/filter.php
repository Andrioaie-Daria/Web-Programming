<!DOCTYPE html>
<html>
<head>
    <?php include 'imports.php'; 
      require 'backend/connection.php';

      $select_query = "SELECT * FROM documents";
      $result = mysqli_query($connection, $select_query);
    ?>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

  <div class="container mt-10">
    <div class="row">
        <div class="input-group mt-10 col-12">
            <div class="form-outline mt-10">
                <input type="search" id="form-type" class="form-control" placeholder="Document type"/>
            </div>
            <div class="form-outline mt-10">
                <input type="search" id="form-format" class="form-control" placeholder="Document format"/>
            </div>
        </div>

        <div class="mt-5" id='previous-filter'>

        </div>  

        <div id='result'>
          <div class="container">
            <div class="row">
              <ul class="list-group">
                <?php
                while($row = mysqli_fetch_object($result)){
                  echo '<li class="list-group-item">'.
                     'Title: ' . $row->title . ',   ' .
                     'Author: ' . $row->author . ',   ' .
                     'Pages: ' . $row->number_of_pages .'</li>';
                }
                ?>
              </ul>
            </div>
          </div>  
        </div>

        <a href="index.php" type="button" class="btn btn-outline-dark">Go back</a>
    </div>
  </div> 

  <script>
        $(document).ready(function(){
          let filter = "None";

          $("#form-type").on("keyup", function(event){
            if(event.key === "Enter"){
              var type_value = $("#form-type").val();
              var format_value = $("#form-format").val();
              var $result = $("#result");
              if(type_value.length > 0){
                  $.get("backend/filter-documents.php", {type: type_value, format: format_value}).done(function(resulted_data){
                  $result.html(resulted_data);
                  $("#previous-filter").html("Documents filtered by type:");
                  filter = "type";
                  });
              }
              else{
                $result.empty();
              }
            }   
         });

         $("#form-format").on("keyup", function(event){
            if(event.key === "Enter"){
              var type_value = $("#form-type").val();
              var format_value = $("#form-format").val();
              var $result = $("#result");
              if(format_value.length > 0){
                  $.get("backend/filter-documents.php", {type: type_value, format: format_value}).done(function(resulted_data){
                  $result.html(resulted_data);
                  $("#previous-filter").html("Documents filtered by format:");
                  filter = "format";
                  });
              }
              else{
                $result.empty();
              }
            }   
         });
         });
  </script>
</body>
</html>