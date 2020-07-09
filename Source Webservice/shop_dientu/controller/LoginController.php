<?php

require_once 'model/Login.php';
class LoginController
{
    public function login ($request){

        // nếu request không phải rỗng thì thực hiện kiểm tra email password với dữ liệu dưới db.
        if(!empty($request)){

            // tạo ra 1 đối tượng Auth đc định nghĩa tại Auth.php để checkLogin
            $auth =  new Login();
            $checkAuth = $auth->checkLogin($request);

            // nếu đối tượng lấy lên là có thì ta lưu đối tượng đó vào bộ nhớ tạm session
            if(!empty($checkAuth)){
                // lưu đối tượng vào session
                $_SESSION['auth'] = $checkAuth;

                // login thành công  cho về trang home
                header("location:/page/home/home.php");
            }else{
                // ngược lại ta gán 1 biến có thông điệp cảnh báo nhập sai
                $fail_login = 'Email or Password wrong...';
            }
        }
        // hiển thị cái form login.
        // tất cả các biến trong hàm này đều có thể gọi và sử dụng trong views/admins/UI/login.php
        include "views/UI/login.php";
    }
    public function logout(){
        // xóa cái session tức cái thằng user đang đăng nhập và về trang login
        unset($_SESSION['auth']);
        header("location:/login.php");
    }
}