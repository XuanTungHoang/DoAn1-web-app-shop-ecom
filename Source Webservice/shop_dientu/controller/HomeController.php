<?php

require_once 'Controller_main.php';
require_once '../../model/Homer.php';

class HomeController extends Controller_main
{
    public function getOrders($date_request){

        $order=new Homer();

        if(!empty($date_request)){
           // $date_from=$date_request['date_from'];
            $date_to=$date_request['date_to'];
//                print_r($date_request);
//                die('3');
            if($date_to!=null){
//                    print_r($date_request);
//                    die('5');
                $from_to_orders=$order->get_order_from_to($date_request);
                $this->dataSendView['list_orders_result']=$from_to_orders;
            }else{
//                    print_r($date_request);
//                    die('4');
                $order_from_specific_day=$order->get_order_from_specific_day($date_request);
                $this->dataSendView['list_orders_result']=$order_from_specific_day;
            }
        }

        return $this->view('views/UI/home/index.php');
    }

}