<?php
session_start();
require_once '../../controller/ProductController.php';

if(!empty($_GET['id']))
{
    $id=$_GET['id'];
}
else{
    header("location:/page/product/");
}

$product=new ProductController();
$product->delete($id);

