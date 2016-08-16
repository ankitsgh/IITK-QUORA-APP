<?php

/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['aid']) && isset($_POST['dislike'])) {

    $aid = $_POST['aid'];
    $like = $_POST['dislike'];
	$abc  =  intval($like);
	
    
    

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql update row with matched pid
    $result = mysqli_query($GLOBALS["___mysqli_ston"], "UPDATE ans SET dislike = '$abc' WHERE aid = $aid");

    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully updated.";

        // echoing JSON response
        echo json_encode($response);
    } else {

    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>