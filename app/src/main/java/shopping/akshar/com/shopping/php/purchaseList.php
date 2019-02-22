<?php

require "connection.php";

$query = "SELECT * FROM purchase,product WHERE purchase.product_id = product.product_id";

$result = mysqli_query($connect,$query);

while($row = mysqli_fetch_assoc($result)){
    $data[] = $row;
}

echo json_encode($data);

?>