<?php


class Controller_main
{
    protected $auth = null; // biến auth để lưu cái thằng đang đăng nhập
    protected $dataSendView = []; // biến này để lưu những gì ta cần gửi lên view
    protected $limit=null; // sử dụng để phân trang
    /**
     * Controller constructor.
     */
    public function __construct()
    {
        $this->limit=8 ;

        // nếu cái url mà người dùng đang ở đó tồn tại đường dẫn /pages/admin
        if (substr($_SERVER['REQUEST_URI'], 0, 5) == '/page') {

            // nếu tồn tại thằng session tức là có user ( tình trạng đã đăng nhập )
            if (!empty($_SESSION['auth'])) {

                // ta gán auth đúng bằng cái thằng user đang đăng nhập
                $this->auth = $_SESSION['auth'];
            } else {
                // ngược lại tức là không có user ( chưa đăng nhập ) thì ta cho về thẳng trang login
                // cái này nhằm khắc phục tình trạng chưa đăng nhập vẫn vào đc /pages/admin
                header('location:/login.php');
            }
        }
    }



    public function view($path)
    {

        // nếu cái auth tồn tại tức là có user đăng nhập rồi
        if (!empty($this->auth)) {
            // gán auth cho chính thằng user đó
            $auth = $this->auth;
        }
        //$dataSendView đc gán cho $this->dataSendView để đổ lên view
        $dataSendView = $this->dataSendView;
        // khi gọi hàm view ta mặc định có header và footer chỉ cần truyền phần thân vào biến path là xong
        include('../../views/layouts/header.php');
        include('../../' . $path);
        include('../../views/layouts/footer.php');
    }

}