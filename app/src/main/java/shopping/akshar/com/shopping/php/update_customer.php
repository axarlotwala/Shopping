<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

	require 'connection.php';

  
 	$token = $_POST['token'];
	$cust_name = $_POST["cust_name"];
	$cust_phoneno = $_POST["cust_phoneno"];
	$cust_address = $_POST["cust_address"];
	$cust_pincode = $_POST["cust_pincode"];


	$Sql_Query = "UPDATE customer SET cust_name= '$cust_name',cust_phoneno = '$cust_phoneno',cust_address = '$cust_address',cust_pincode = '$cust_pincode' WHERE token = '$token'";

 	if(mysqli_query($connect,$Sql_Query))
	{
 		echo 'Record Updated Successfully';
	}
	else
	{
 		echo 'Something went wrong';
	 }
 }

 mysqli_close($connect);
?> 