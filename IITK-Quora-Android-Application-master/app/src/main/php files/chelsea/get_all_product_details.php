 <?php
 

$response = array();

require_once __DIR__ . '/db_connect.php';
 

$db = new DB_CONNECT();
 

$result = mysqli_query($GLOBALS["___mysqli_ston"], "SELECT *FROM ans") or die(((is_object($GLOBALS["___mysqli_ston"])) ? mysqli_error($GLOBALS["___mysqli_ston"]) : (($___mysqli_res = mysqli_connect_error()) ? $___mysqli_res : false)));
 

if (mysqli_num_rows($result) > 0) {
    
    $response["answ"] = array();
 
    while ($row = mysqli_fetch_array($result)) {
     
        $product = array();
        $product["pid"] = $row["pid"];
		$product["aid"] = $row["aid"];
        $product["ans"] = $row["ans"];
		$product["name"] = $row["name"];
        $product["current_time"] = $row["current_time"];
        $product["updated_time"] = $row["updated_time"];
		$product["likes"] = $row["likes"];
		$product["dislike"] = $row["dislike"];
 
       
        array_push($response["answ"], $product);
    }
    
    $response["success"] = 1;
 
   
   echo json_encode($response);
} else {
    
    $response["success"] = 0;
    $response["message"] = "No products found";
   echo json_encode($response);
}
?> 