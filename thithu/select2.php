<?php

/*
 * Following code will retrieve students in class '12A'
 */

// array for JSON response
$response = array();

require_once 'config.php';

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Query to retrieve students in class '12A'
$query = "SELECT h.* FROM hocsinh h JOIN lop l ON h.lopid = l.lopid WHERE l.tenlop = '12A'";

$result = $conn->query($query);

if ($result->num_rows > 0) {
    // looping through all results
    $response["students"] = array();
    while($row = $result->fetch_assoc()) {
        // temp student array
        $student = array();
        $student["hocsinhid"] = $row["hocsinhid"];
        $student["lopid"] = $row["lopid"];
        $student["hoten"] = $row["hoten"];
        $student["ngaysinh"] = $row["ngaysinh"];
        $student["gioitinh"] = $row["gioitinh"];
        $student["diachi"] = $row["diachi"];
        $student["loptruong"] = $row["loptruong"];
        $student["bithuchidoan"] = $row["bithuchidoan"];
        $student["ghichu"] = $row["ghichu"];

        // push single student into final response array
        array_push($response["students"], $student);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no students found
    $response["success"] = 0;
    $response["message"] = "No students found in class '12A'";

    // echo JSON response
    echo json_encode($response);
}
?>
