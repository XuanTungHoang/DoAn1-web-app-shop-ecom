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

$auth=new UserController();
$auth->delete($id);
