<?php
		
	require_once "connection.php";
	
	
	$upload_path = 'upload/';
	$server_ip = gethostbyname(gethostname());
	$upload_url = 'http://'.$server_ip.'/shopping/'.$upload_path;

	$responce = array();

	if ($_SERVER['REQUEST_METHOD'] == 'POST') 
	{
	 	if(isset($_POST['name']) and isset($_FILES['image']['name']))
	 	{
				global $connect; 	

	 			$name = $_POST['name'];

	 			$fileinfo = pathinfo($_FILES['image']['name']);
	 			$extension = $fileinfo['extension'];

	 			$file_url = $upload_url . getFileName() . '.'. $extension;

	 			$file_path = $upload_path . getFileName() . '.'. $extension;	

	 			try{

	 				move_uploaded_file($_FILES['image']['tmp_name'],$file_path);

	 				$query = "INSERT INTO image(url,name) VALUES('$file_url','$name');";

	 				mysqli_query($connect,$query);
	 				{
	 					$responce['error'] = false;
	 					$responce['url'] = $file_url;
	 					$responce['name'] = $name;
	 				}

	 			}
	 			catch(Exception $e)
	 			{
	 				$responce['error'] = false;
	 				$responce['message']=$e->getMessage();
	 			}
	 		//mysqli_close($connect);
	 	}
	 		else
	 		{
	 				$responce['error'] = true;
	 				$responce['message'] = 'please choose file';
	 		}
	 	echo json_encode($responce);
	}

	function getFileName()
	 {
	 
	 	global $connect;

	 	$sql = "SELECT name as name FROM image";
	 	$result = mysqli_fetch_array(mysqli_query($connect,$sql));
	 	
	 	if($result['name'] == null)
	 	{
	 		return 0;
	 	}
	 	else
	 	{
	 		return $result['name'];
	 	}
	 	mysqli_close($connect);
	 }
?>