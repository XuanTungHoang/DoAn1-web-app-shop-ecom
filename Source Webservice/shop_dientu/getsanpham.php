<?php
include "connect.php";
$page=$_GET['page'];
if(!empty($_POST['id_sanpham'])&&$_POST['id_sanpham']!=-1){
    $idsanpham=$_POST['id_sanpham'];


    $limit=5;
    $pageStart=($page-1)*$limit;
    $sql="select * from sanpham where id_sanpham=$idsanpham limit $pageStart,$limit  ;";
    $execute_query=Database::getInstance()->prepare($sql);
    $execute_query->execute();
    $response=$execute_query->fetchAll();

    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Headers: *");
    header('Content-Type: application/json');
    echo json_encode($response);
}







