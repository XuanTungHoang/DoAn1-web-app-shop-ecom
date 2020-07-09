<?php
include "connect.php";

$sql="select * from sanpham order by id desc limit 14";
$execute_query=Database::getInstance()->prepare($sql);
$execute_query->execute();
$response=$execute_query->fetchAll();

header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");
header('Content-Type: application/json');

echo json_encode($response);

