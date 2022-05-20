<?php

require 'backend/connection.php';
try {
  $title = $_GET['title'];
  $result = mysqli_query($connection, "SELECT * FROM documents WHERE title LIKE '{$title}'");
  $result = mysqli_fetch_object($result);
}
catch(Exception $e){
  echo 'Message: ' . $e->getMessage();
}

?>

<!DOCTYPE html>
<html>
<head>
    <?php include 'imports.php'; ?>
</head>
<body>
<div class="container">
    <div class="row">
        <form method="POST" action="backend/update-document.php">
            <input type="hidden" name="old_title" value="<?php echo $title; ?>" />
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="Title..." value="<?php echo $result->title; ?>" required>
            </div>
            <div class="form-group">
                <label for="author">Author</label>
                <input type="text" class="form-control" id="author" name="author" placeholder="Author.." value="<?php echo $result->author; ?>" required>
            </div>

            <div class="form-group">
                <label for="pages">Number of pages</label>
                <input type="text" class="form-control" id="pages" name="pages" placeholder="Pages.." value="<?php echo $result->number_of_pages; ?>" required>
            </div>

            <div class="form-group">
                <label for="type">Type</label>
                <input type="text" class="form-control" id="type" name="type" placeholder="Type.." value="<?php echo $result->typeId; ?>" required>
            </div>
            <div class="form-group">
                <label for="format">Format</label>
                <input type="text" class="form-control" id="format" name="format" placeholder="Format.." value="<?php echo $result->formatId; ?>" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            <a type="button" class="btn btn-danger" onclick="history.back()">Back</a>
        </form>
    </div>
</div>
</body>
</html>