<!DOCTYPE html>
<html>
<head>
    <?php include 'imports.php'; ?>
</head>
<body>
<div class="container" style="margin-top:40px">
  <div class="row">
    <form method="POST" action="backend/add-document.php">
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" id="title" name="title" placeholder="Title..." required>
      </div>  

      <div class="form-group">
        <label for="author">Author</label>
        <input type="text" class="form-control" id="author" name="author" placeholder="Author..." required>
      </div>  

      <div class="form-group">
        <label for="pages">Number of pages</label>
        <input type="text" class="form-control" id="pages" name="pages" placeholder="Pages..." required>
      </div>  

      <div class="form-group">
        <label for="type">Type</label>
        <input type="text" class="form-control" id="type" name="type" placeholder="Type..." required>
      </div>  

      <div class="form-group">
        <label for="format">Format</label>
        <input type="text" class="form-control" id="format" name="format" placeholder="Format..." required>
      </div>  

      <button id="submit-button" type="submit" class="btn btn-outline-success">Submit</button>
      <button class="btn btn-outline-dark"  onclick="history.back()">Go back</button>
  </form>
  </div>
</div>


<!-- <script>
    $(document).ready(function(){
        $("#submit-button").click(function(){
          $.post("backend/add-document.php", 
	        {title : $("title").val() , author : $("author").val(), pages: $("pages").val(), type: $("type").val(), format: $("format").val()});
        })
    })
</script>   -->
</body>
</html>