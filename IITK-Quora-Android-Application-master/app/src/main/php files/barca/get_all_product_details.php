 <?php
 

$response = array();

require_once __DIR__ . '/db_connect.php';
 

$db = new DB_CONNECT();
 

$result = mysqli_query($GLOBALS["___mysqli_ston"], "SELECT *FROM products") or die(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false)));
 

if (mysqli_num_rows($result) > 0) {
    
    $response["products"] = array();
 
    while ($row = mysqli_fetch_array($result)) {
     
        $product = array();
        $product["pid"] = $row["pid"];
		$product["name"] = $row["name"];
		
        $product["ques"] = $row["ques"];
        $product["created_at"] = $row["created_at"];
        $product["updated_at"] = $row["updated_at"];
        $product["views"] = $row["views"];
 
       
       
        array_push($response["products"], $product);
    }
    
    $response["success"] = 1;
 
   
   echo json_encode($response);
} else {
    
    $response["success"] = 0;
    $response["message"] = "No products found";
   echo json_encode($response);
}
?> 