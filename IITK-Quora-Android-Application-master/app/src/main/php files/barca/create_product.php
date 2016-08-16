<?php
$response = array();
 

if (isset($_POST['name']) && isset($_POST['inputDesc'])) {
 
    $name = $_POST['name'];
    
    $ques = $_POST['inputDesc'];
 
    
    require_once __DIR__ . '/db_connect.php';
 
    
    $db = new DB_CONNECT();
 
   
    $result = mysqli_query($GLOBALS["___mysqli_ston"], "INSERT INTO products(name,ques) VALUES('$name', '$ques')");
 
    
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