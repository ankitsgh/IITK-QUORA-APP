<?php
 
/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// check for post data
if (isset($_GET['aid'])) {
    $aid = $_GET['aid'];
 
    // get a product from products table
    $result = mysqli_query($GLOBALS["___mysqli_ston"], "SELECT *FROM ans WHERE aid=$aid");
 
    if (!empty($result)) {
        // check for empty result
        if (mysqli_num_rows($result) > 0) {
 
            $result = mysqli_fetch_array($result);
 
            $product = array();
            $product["pid"] = $result["pid"];
			$product["aid"] = $result["aid"];
            $product["name"] = $result["name"];
            $product["ans"] = $result["ans"];
            $product["current_time"] = $result["current_time"];
            $product["updated_time"] = $result["updated_time"];
			  
			 $product["likes"] = $result["likes"];
			 
			  
			 $product["dislike"] = $result["dislike"];
            // success
            $response["success"] = 1;
 
            // user node
            $response["answ"] = array();
 
            array_push($response["answ"], $product);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";
 
        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>