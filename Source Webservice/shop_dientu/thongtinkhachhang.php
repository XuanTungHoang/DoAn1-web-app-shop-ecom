<?php
include "connect.php";

$tenkhachhang=$_POST['tenkhachhang'];
$sodienthoai=$_POST['sodienthoai'];
$diachi=$_POST['diachi'];
$tongtien=$_POST['tongtien'];
$trangthai='waiting';
if(!empty($tenkhachhang)&&!empty($sodienthoai)&&!empty($diachi)){

    $sql="insert into donhang(tenkhachhang,sodienthoai,diachi,tongtien,trangthai,create_at) values ('$tenkhachhang','$sodienthoai','$diachi','$tongtien','$trangthai',now()) ;";
    $execute_query=Database::getInstance()->prepare($sql);
    $execute_query->execute();

    $max_id="select max(id) from donhang";
    $query=Database::getInstance()->prepare($max_id);
    $query->execute();

    $id_donhang=$query->fetchColumn();
    echo $id_donhang;
}


