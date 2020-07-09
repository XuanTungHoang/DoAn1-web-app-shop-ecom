<?php

require_once 'Controller_main.php';
require_once '../../model/User.php';

class UserController extends Controller_main
{
    public function index(){
        $user=new User();
        //$user->getListUser();

//        print_r($user);
//        die('33');

        $this->dataSendView['user_list']=$user->getListUser();
        return $this->view('views/UI/user/index.php');

    }

    public function add($request){

        $this->dataSendView['errors'] = [];
        $_SESSION['input'] = $request;
        $errors = [];

        if ($request['action'] != null) {

            if (empty($request['input_name'])) $errors['input_name_null'] = 'Field name must be required.';

            if (empty($request['input_email'])) {
                $errors['input_email_null'] = 'Field email must required';
            } else {
                if (!preg_match("/^[a -zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]*$/", $request['input_email'])) {
                    $errors['input_email_invalid'] = 'Field email invalid.';
                }
            }

            if (empty($request['input_password'])) {
                $errors['input_password_null'] = 'Field password must be required';
            }

            if (!empty($errors)) {
                $this->dataSendView['errors'] = $errors;
                return $this->view('views/UI/user/add.php');
            }

            $user = new User();
            $add_user = $user->add($request);
            if ($add_user) {
                unset($_SESSION['input']);
                header("location:/page/user/");
            }
        }
        return $this->view('views/UI/user/add.php');
    }

    public function edit($id){
        $user = new User();
        $user_object = $user->getUserById($id);
        if (empty($user_object)) {
            header('location:/page/user/');
        }
//        print_r($user_object);
//        die('3');
        $this->dataSendView['user_object'] = $user_object;
        $this->dataSendView['errors_update']=[];

        return $this->view('views/UI/user/edit.php');
    }
    public function update($request){
        $user = new User();
        $this->dataSendView['errors_update'] = [];
        $errors = [];

        $user_object = $user->getUserById($request['id']);

        if (empty($request['input_name'])) {
            $errors['input_name_null'] = 'Field name must be required.';
            $user_object->ten = "";
        }
        else{
            if($user_object->ten!=$request['input_name']){
                $user_object->ten=$request['input_name'];
            }
        }

        if(empty($request['input_email']))
        {
            $errors['input_email_null']='Field email must be required.';
            $user_object->email = "";
        }else{
            if (!preg_match("/^[a -zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]*$/", $request['input_email'])) {
                $errors['input_email_invalid'] = 'Field email invalid.';

                $user_object->email=$request['input_email'];
            }
            else {
                $user_object->email=$request['input_email'];
            }
        }

        if(empty($request['input_password']))
        {
            $errors['input_password_null']='Field password must be required.';
            $user_object->password="";
        }
        else
        {
            if($user_object->password!=$request['input_password'])
            {
                $user_object->password=$request['input_password'];
            }
        }

        if(!empty($errors))
        {
            if(!empty($user_object)){
                $this->dataSendView['user_object'] = $user_object;
                //$this->dataSendView['user_object']=$_SESSION['input_update'];
            }
            $this->dataSendView['errors_update']=$errors;
            return $this->view('views/UI/user/edit.php');
        }

        $user_update = $user->update($request);
        if ($user_update) {
            header("location:/page/user/");
        }
    }
    public function delete($id){
        if (!empty($id)) {
            $user = new User();
            $user_delete = $user->delete($id);
            if ($user_delete) {
                header("location:/page/user/");
            }
        }
    }
}