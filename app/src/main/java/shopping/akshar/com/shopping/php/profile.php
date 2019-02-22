<?php

require "connection.php";

$token = $_GET['token'];

$query = "SELECT * FROM customer WHERE token = '{$token}'";

$result = mysqli_query($connect,$query);

while($row = mysqli_fetch_assoc($result) == true){
    $data[] = $row;
   
}

echo json_encode($data);

?>
