<?php
session_start();
require_once '../../controller/ProductController.php';

$request=[];
$action=!empty($_POST['action']) ? $_POST['action'] : null;
$input_name=!empty($_POST['input_name']) ? $_POST['input_name'] : null;
$input_des=!empty($_POST['input_des']) ? $_POST['input_des'] : null;
$price=!empty($_POST['price']) ? $_POST['price'] : null;
$image=!empty($_POST['image']) ? $_POST['image'] : null;
$categori_id=!empty($_POST['categori_id']) ? $_POST['categori_id'] : null;

$request=['action'=>$action,'input_name'=>$input_name,'input_des'=>$input_des,
    'price'=>$price,'image'=>$image,'categori_id'=>$categori_id];

$product=new ProductController();
$product->add($request);



