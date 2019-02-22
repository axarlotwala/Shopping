<?php
if($_SERVER['REQUEST_METHOD']=='POST')
{

	require 'connection.php';

 	$product_id = $_POST['product_id'];

	$Sql_Query = "DELETE FROM cart WHERE product_id = '$product_id'";

	 if(mysqli_query($connect,$Sql_Query))
	{
 		echo "Record Deleted Successfully";
	}
	else
	{
 		echo "Something went wrong";
 	}
 mysqli_close($connect);
}
?>