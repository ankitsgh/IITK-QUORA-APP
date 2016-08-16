<?php
$response = array();
 

if (isset($_POST['inputName']) && isset($_POST['inputDesc']) && isset($_POST['pid'])) {
 
    $name = $_POST['inputName'];
    
    $ans = $_POST['inputDesc'];
	$pid = $_POST['pid'];
 
    
    require_once __DIR__ . '/db_connect.php';
 
    
    $db = new DB_CONNECT();
 
   
    $result = mysqli_query($GLOBALS["___mysqli_ston"], "INSERT INTO ans(name,ans,pid) VALUES('$name','$ans','$pid')");
 
    
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