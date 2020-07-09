<?php
session_start();
require_once '../../controller/UserController.php';

$request = [];
$input_name =!empty($_POST['input_name']) ? $_POST['input_name'] : null;
$action =!empty($_POST['action']) ? $_POST['action'] : null;
$input_email =!empty($_POST['input_email']) ? $_POST['input_email'] : null;
$input_password =!empty($_POST['input_password']) ? $_POST['input_password'] : null;

//$id = $_POST['id'] ;

$request=['input_name'=>$input_name,'input_email'=>$input_email,'input_password'=>$input_password,'action'=>$action];


$auth=new UserController();
$auth->add($request);