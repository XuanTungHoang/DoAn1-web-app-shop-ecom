<?php
session_start();
require_once 'controller/LoginController.php';

// tạo ra đối tượng AuthController và gọi hàm logout
$auth = new LoginController();
$auth->logout();
