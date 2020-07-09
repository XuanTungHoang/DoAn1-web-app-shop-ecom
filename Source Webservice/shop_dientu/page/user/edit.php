<?php
session_start();

require_once '../../controller/UserController.php';
if(!empty($_GET['id']))
{
    $id=$_GET['id'];
}
else{
    header("location:/page/user/");
}

//print_r($id);
//die('4');
$auth=new UserController();
$auth->edit($id);
