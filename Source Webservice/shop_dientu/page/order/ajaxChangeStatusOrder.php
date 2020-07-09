<?php

session_start();
require_once '../../controller/OrderController.php';

$order = new OrderController();
$order->ajaxChangeStatus($_POST['order_id']);