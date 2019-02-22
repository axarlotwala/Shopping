<?php

if($_SERVER["REQUEST_METHOD"]== "POST"){

    require 'connection.php';
    registerToken();
}

Function registerToken(){
    global $connect;

    $token = $_POST["token"];
    $product_id = $_POST["product_id"];
    $cart_qty = $_POST["cart_qty"];
    
    $query = "Insert Into cart(token,product_id,cart_qty) Values ('$token','$product_id','$cart_qty');";

    mysqli_query($connect,$query) or die (mysqli_error($connect));
    mysqli_close($connect);

}

?>
