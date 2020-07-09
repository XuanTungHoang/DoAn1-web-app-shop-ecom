<?php

session_start();
require_once '../../controller/OrderController.php';

$auth = new OrderController();
$auth->ajaxOrderDetail($_POST['order_id']);
