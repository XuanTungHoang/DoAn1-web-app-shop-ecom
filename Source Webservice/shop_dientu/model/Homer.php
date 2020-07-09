<?php

require_once '../../connect.php';
class Homer
{
    public function get_order_from_to($date_request)
    {
        $date_from=$date_request['date_from'];
        $date_to=$date_request['date_to'];

        $sql="select * from donhang where (create_at between '$date_from' and '$date_to');";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->execute();
        //$arr_order_resutl=$execute_query->fetchAll();

//        foreach ($arr_order_result as $item){
//
//        }


//        print_r($execute_query->fetchAll());
//        die('3');
        return $execute_query->fetchAll();
    }

    public function get_order_from_specific_day($date_request)
    {
        $date_from=$date_request['date_from'];
        $sql="select * from donhang where create_at='$date_from' ;";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->execute();
        return $execute_query->fetchAll();
    }
}