<?php

require_once 'Controller_main.php';
require_once '../../model/Order.php';
class OrderController extends Controller_main
{
    public function index($request)
    {
        $order = new Order();

        $order = $order->getOrders($request, $this->limit);
        //print_r($kind_list['data']);
        $this->dataSendView['orders'] = $order['data'];
        $this->dataSendView['total_page'] = $order['total_page'];

        return $this->view('views/UI/order/index.php');
    }
    public function ajaxOrderDetail($order_id)
    {
        header("Access-Control-Allow-Origin: *");
        header("Access-Control-Allow-Headers: *");
        header('Content-Type: application/json');

        $order = new Order();

        $order_detail = $order->getDetailOrder($order_id);
        echo json_encode($order_detail);

    }

    public function ajaxChangeStatus($request)
    {
        header("Access-Control-Allow-Origin: *");
        header("Access-Control-Allow-Headers: *");
        header('Content-Type: application/json');
        $order = new Order();
        $change_status=  $order->changeStatus($request);

//        print_r($change_status);
//        die('3');
        echo json_encode($change_status);
    }
}