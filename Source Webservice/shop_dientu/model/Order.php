<?php

require_once '../../connect.php';
class Order
{
    public function getOrders($request,$limit){

        //
        $sql_count ="select count(*) from donhang where (sodienthoai like ? or tenkhachhang like ?)";
        $execute_query_count=Database::getInstance()->prepare($sql_count);
        $execute_query_count->bindValue(1,'%'.$request['keyword'].'%');
        $execute_query_count->bindValue(2,'%'.$request['keyword'].'%');
        $execute_query_count->execute();
        $total_page=ceil((int)$execute_query_count->fetchColumn()/$limit);
        $page_start=($request['page']-1)*$limit;

        $sql ="select * from donhang where (sodienthoai like ? or tenkhachhang like ?) ORDER BY  id DESC limit ?,?";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->bindValue(1,'%'.$request['keyword'].'%');
        $execute_query->bindValue(2,'%'.$request['keyword'].'%');
        $execute_query->bindValue(3,$page_start,PDO::PARAM_INT);
        $execute_query->bindValue(4,$limit,PDO::PARAM_INT);
        $execute_query->execute();

        return ['data'=>$execute_query->fetchAll(),'total_page'=>$total_page];

    }

    public function getDetailOrder($order_id){
        //tee
        //SELECT o.quantity,p.* FROM `order_details` as o , products as p WHERE order_id  = $order_id and p.id = o.product_id
        //return ['teo'=>$order_id];
        $sql="select o.soluongsanpham, p.* from sanpham as p ,chitietdonhang as o where madonhang=$order_id and p.id=o.masanpham;";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->execute();
        return $execute_query->fetchAll();
    }

    public function changeStatus($id){

        $response = ['success'=>false,'status_changed'=>''];
        $sql = "select * from donhang where id = ?";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->bindValue(1,$id,PDO::PARAM_INT);
        $execute_query->execute();
        $order = $execute_query->fetchObject();

        $update_status = '';
        if($order->trangthai == 'waiting'){
            $update_status = 'confirmed';
        }else if($order->trangthai == 'confirmed'){
            $update_status = 'successfully';
        }else if($order->trangthai == 'successfully'){
            $update_status = 'cancel';
        }else if($order->trangthai == 'cancel'){
            $update_status = 'waiting';
        }

        $sql_update_order = "update donhang set trangthai = ? where id=?";
        $execute_query_update=Database::getInstance()->prepare($sql_update_order);
        $execute_query_update->bindValue(1,$update_status);
        $execute_query_update->bindValue(2,$id,PDO::PARAM_INT);
//        $execute_query_update->execute();
        if($execute_query_update->execute()){
            $response['success']  = true;
            $response['status_changed']=$update_status;
        }

        return $response;
    }
}