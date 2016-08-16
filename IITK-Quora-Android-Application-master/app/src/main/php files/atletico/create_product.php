<?php
$response = array();
 

if (isset($_POST['aid']) && isset($_POST['name'])&&isset($_POST['comment'])) {
 
    $name = $_POST['name'];
    
    
	$aid = $_POST['aid'];
	
	$comment = $_POST['comment'];
 
    
    require_once __DIR__ . '/db_connect.php';
 
    
    $db = new DB_CONNECT();
 
   
    $result = mysqli_query($GLOBALS["___mysqli_ston"], "INSERT INTO comments(aid,name,comment) VALUES('$aid','$name','$comment')");
 
    
    if ($result) {
        
        $response["success"] = 1;
        $response["message"] = "Product successfully created.";
 
        
        echo json_encode($response);
    } else {
       
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
        
        echo json_encode($response);
    }
} else {
    
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
   
    echo json_encode($response);
}
?>