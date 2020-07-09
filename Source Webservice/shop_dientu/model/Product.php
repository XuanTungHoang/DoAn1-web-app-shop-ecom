<?php

require_once '../../connect.php';
class Product
{
    public function getListProduct($request,$limit)
    {
        $sql_count="select count(*) from sanpham where tensanpham  like ? ";
        $execute_query_count=Database::getInstance()->prepare($sql_count);
        $execute_query_count->bindValue(1,'%'.$request['keyword'].'%');
        $execute_query_count->execute();
        $total_page=ceil((int)$execute_query_count->fetchColumn()/$limit);
        $page_start=($request['page']-1)*$limit;

        $sql="select p.*,c.tenloaisanpham as cate_name from sanpham as p ,loaisanpham as c where p.id_sanpham=c.id and p.tensanpham like ? ORDER BY  id DESC limit ?,? ";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->bindValue(1,'%'.$request['keyword'].'%');
        $execute_query->bindValue(2,$page_start,PDO::PARAM_INT);
        $execute_query->bindValue(3,$limit,PDO::PARAM_INT);
        $execute_query->execute();

//        print_r($execute_query->fetchAll());
//        die('3');
        return ['data'=>$execute_query->fetchAll(),'total_page'=>$total_page];
    }

    public function add($request){

//        print_r($request);
//        die('3');
        $sql="insert into `doandidong`.`sanpham` (`tensanpham`,`giasanpham`,`hinhanhsanphan`,`motasanpham`,`id_sanpham`) values (?,?,?,?,?)";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->bindValue(1,$request['input_name']);
        $execute_query->bindValue(2,$request['price']);
        $execute_query->bindValue(3,$request['image']);
        $execute_query->bindValue(4,$request['input_des']);
        $execute_query->bindValue(5,$request['categori_id']);
        $execute_query->execute();

        return true;
    }

    public function getListCategory(){
        $sql="select * from loaisanpham ";
        $query=Database::getInstance()->prepare($sql);
        $query->execute();
        return $query->fetchAll();
    }

    public function getProductById($id)
    {
        $sql="select p.*,c.tenloaisanpham cate_name from sanpham as p,loaisanpham as c where p.id_sanpham=c.id and p.id=?";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->bindValue(1,$id);
        $execute_query->execute();

//        print_r($execute_query->fetchObject());
//        die('3');
        return $execute_query->fetchObject();
    }

    public function update($request)
    {
        //print_r($request);
        $sql="update `doandidong`.`sanpham` set `tensanpham`=?,`giasanpham`=?,`hinhanhsanphan`=?,`motasanpham`=?,`id_sanpham`=? where (id=?);";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->bindValue(1,$request['input_name']);
        $execute_query->bindValue(2,$request['price']);
        $execute_query->bindValue(3,$request['image']);
        $execute_query->bindValue(4,$request['input_des']);
        $execute_query->bindValue(5,$request['categori_id']);
        $execute_query->bindValue(6,$request['id']);
        $execute_query->execute();
        return true;
    }
    public function delete($id)
    {
        $sql="delete from `sanpham` where id=? ";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->bindValue(1,$id);
        $execute_query->execute();
        return true;
    }
}