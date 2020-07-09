<?php

session_start();
require_once '../../controller/UserController.php';

$user=new UserController();
$user->index();