<?php
require_once 'Controller_main.php';
require_once '../../model/Product.php';

class ProductController extends Controller_main
{
    public function index($request)
    {

        $product=new Product();
        $product_list=$product->getListProduct($request,8);

        $this->dataSendView['product_list']=$product_list['data'];
        $this->dataSendView['total_page']=$product_list['total_page'];

        if(count($product_list['data'])==0)
        {
            $request['page']=1;
            $product_list=$product->getListProduct($request,8);
        }

        $this->dataSendView['product_list']=$product_list['data'];

        return $this->view('views/UI/product/index.php');
    }

    public function add($request)
    {

        $this->dataSendView['errors']=[];
        $_SESSION['input_product']=$request;
        $errors=[];

        $cate=new Product();
        $cate_list=$cate->getListCategory();
        $this->dataSendView['cate_list']=$cate_list;
        //print_r($cate_list['data']);
        if($request['action']!=null)
        {
            if(empty($request['input_name'])){
                $errors['input_name_null']='Filed name must be required';
            }
            if(empty($request['input_des'])){
                $errors['input_des_null']='Filed description must be required';
            }
            if(empty($request['price'])){
                $errors['price_null']='Filed price must be required';
            }
            if(empty($request['image'])){
                $errors['image_null']='File  link image must be required';
            }
            if(!empty($errors)){
                $this->dataSendView['errors']=$errors;
                return $this->view('views/UI/product/add.php');
            }

            $product=new Product();
            $product_add=$product->add($request);
            if($product_add){
                header("location:/page/product/");
            }
        }

        return $this->view('views/UI/product/add.php');
    }
    public function edit($id)
    {
        $cate=new Product();
        $cate_list=$cate->getListCategory();
        $this->dataSendView['cate_list']=$cate_list;

        $product=new Product();
        $product_object=$product->getProductById($id);
        $this->dataSendView['product_object']=$product_object;
        $this->dataSendView['errors']=[];

        return $this->view('/views/UI/product/edit.php');
    }
    public function update($request)
    {

        $cate=new Product();
        $cate_list=$cate->getListCategory();
        $this->dataSendView['cate_list']=$cate_list;

        $this->dataSendView['errors']=[];
        $errors=[];

        $product=new Product();
        $product_object=$product->getProductById($request['id']);

        if(empty($request['input_name'])){
            $errors['input_name_null']='Filed name must be required';
            $product_object->tensanpham="";
        }else{
            if($request['input_name']!=$product_object->tensanpham){
                $product_object->tensanpham=$request['input_name'];
            }
        }

        if(empty($request['input_des'])){
            $errors['input_des_null']='Filed description must be required';
            $product_object->motasanpham="";
        }else{
            if($request['input_des']!=$product_object->motasanpham){
                $product_object->motasanpham=$request['input_des'];
            }
        }

        if(empty($request['price'])){
            $errors['price']='Filed price must be required';
            $product_object->giasanpham="";
        }else{
            if($request['price']!=$product_object->giasanpham){
                $product_object->giasanpham=$request['price'];
            }
        }

        if (empty($request['image'])) {
            $errors['input_image'] = 'Field link image must be required';
            $product_object->hinhanhsanphan="";
        }else{
            if($request['image']!=$product_object->hinhanhsanphan){
                $product_object->hinhanhsanphan=$request['image'];
            }
        }
        if(!empty($errors)){
            if(!empty($product_object)){
                $this->dataSendView['product_object']=$product_object;
            }
            $this->dataSendView['errors']=$errors;
            return $this->view('views/UI/product/edit.php');
        }

        $product=new Product();
        $product_update=$product->update($request);
        if($product_update)
        {
            header("location:/page/product/");
        }

    }

    public function delete($id)
    {
        $product=new Product();
        $product_delete=$product->delete($id);
        if($product_delete){
            header("location:/page/product/");
        }
    }

}