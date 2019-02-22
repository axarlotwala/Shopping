<?php

if($_SERVER["REQUEST_METHOD"]== "POST"){

    require 'connection.php';
    registerToken();
}

Function registerToken(){
    global $connect;

    $token = $_POST["token"];
    $cust_email = $_POST["cust_email"];
    $cust_password = $_POST["cust_password"];

    $query = "Insert Into customer(token,cust_email,cust_password) Values ('$token','$cust_email','$cust_password');";

    mysqli_query($connect,$query) or die (mysqli_error($connect));
    mysqli_close($connect);

}

?>
