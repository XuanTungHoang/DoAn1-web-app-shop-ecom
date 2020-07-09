<?php

session_start();
require_once 'controller/LoginController.php';

// kiểm tra xem email password có rỗng hay không ( chưa kiểm tra đúng )
if(!empty($_POST['email']) && !empty($_POST['password'])){
    // nếu không rỗng thì lưu lại email, password vào request
    $request = ['email'=>$_POST['email'],'password'=>$_POST['password']];
}else{
    // nếu rỗng lưu request là rỗng
    $request = [];
}

$user=new LoginController();
$user->login($request);
