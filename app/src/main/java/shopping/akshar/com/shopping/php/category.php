<?php

require "connection.php";

$query = "SELECT * FROM category";

$result = mysqli_query($connect,$query);

while($row = mysqli_fetch_assoc($result)){
    $data[] = $row;
}

echo json_encode($data);

?>