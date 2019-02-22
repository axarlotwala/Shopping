<?php 
require "connection.php";
$cust_email = $_POST["cust_email"];
$cust_password = $_POST["cust_password"];
$mysql_qry = "select * from customer where cust_email like '$cust_email' and cust_password like '$cust_password';";
$result = mysqli_query($connect ,$mysql_qry);
if(mysqli_num_rows($result) > 0) {
echo "login success !!!!! Welcome user";
}
else {
echo "login not success";
}
 
?>