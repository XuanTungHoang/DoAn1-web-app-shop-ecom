<?php
include 'connect.php';

header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: *");
header('Content-Type: application/json');



$response =$_POST['json'];

$data=json_decode($response,true);


if(!empty($data))
{
    foreach ($data as $item) {
        $madonhang = $item['madonhang'];
        $masanpham = $item['masanpham'];
        $tensanpham = $item['tensanpham'];
        $giasanpham =$item['giasanpham'];
        $soluongsanpham =$item['soluongsanpham'];


        $sql = "INSERT INTO `doandidong`.`chitietdonhang` (madonhang, masanpham, tensanpham,giasanpham,soluongsanpham) 
       VALUES ('".$item['madonhang']."', '".$item['masanpham']."', '".$item['tensanpham']."', '".$item['giasanpham']."','".$item['soluongsanpham']."')";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->execute();
    }
    echo "Thanh cong";
}