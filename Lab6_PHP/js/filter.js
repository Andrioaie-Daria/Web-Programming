$(document).ready(function(){
  $("#form-type").on("keyup", function(event){
      if(event.key === "Enter"){
        var input_value = $(this).val();
        var $result = $("#result");
        if(input_value.length > 0){
            $.get("backend/get-documents-by-type.php", {key: input_value}).done(function(resulted_data){
              $result.html(resulted_data);
            });
        }
        else{
          $result.empty();
        }
      }   
  });
});
