<?php

require_once '../../connect.php';
class User
{
    public function getListUser(){
        $sql="select *from users";
        $query=Database::getInstance()->prepare($sql);
        $query->execute();

//        print_r($query->fetchAll());
//        die('555');
        return $query->fetchAll();
    }

    public function add($request){
        $sql="insert into `doandidong`.`users` (`ten`,`email`,`password`) 
              values (?,?,?) ";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->bindValue(1,$request['input_name']);
        $execute_query->bindValue(2,$request['input_email']);
        $execute_query->bindValue(3,$request['input_password']);
        $execute_query->execute();
        return true;
    }
    public function getUserById($id){
        $sql="select *from users where id=?";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->bindValue(1,$id);
        $execute_query->execute();
        return $execute_query->fetchObject();
    }

    public function update($request)
    {
        //print_r($request);
        $sql="update `doandidong`.`users` set `ten`=?,`email`=?,`password`=? where (id=?);";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->bindValue(1,$request['input_name']);
        $execute_query->bindValue(2,$request['input_email']);
        $execute_query->bindValue(3,$request['input_password']);
        $execute_query->bindValue(4,$request['id']);
        $execute_query->execute();
        return true;
    }
    public function delete($id)
    {
        $sql="delete from `users` where id=? ";
        $execute_query=Database::getInstance()->prepare($sql);
        $execute_query->bindValue(1,$id);
        $execute_query->execute();
        return true;
    }
}