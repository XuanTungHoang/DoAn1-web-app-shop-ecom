
<form  action="/page/product/update.php" method="POST"  class="form-horizontal" role="form">
    <legend>Form add product</legend>
    <ul style="float: left; margin-left: 180px;">
        <?php foreach ($dataSendView['errors'] as $item):?>
            <li style="color: red;"><?php echo $item ?></li>
        <?php endforeach; ?>
    </ul>
    <br><br><br><br><br><br>
    <input name="id" type="hidden" value="<?php  echo $dataSendView['product_object']->id; ?>" />
    <div class="form-group">
        <label for="">Name</label>
        <input type="text" class="form-control" value="<?php echo $dataSendView['product_object']->tensanpham ?>" name="input_name" id="" placeholder="Name...">
    </div>
    <div class="form-group">
        <label for="">Des</label>
        <textarea class="form-control"  name="input_des" id="" rows="4" cols="50"><?php echo $dataSendView['product_object']->motasanpham ?></textarea>
    </div>
    <div class="form-group">
        <label for="">Price</label>
        <input type="number" class="form-control" value="<?php echo $dataSendView['product_object']->giasanpham ?>" name="price" id="" >
    </div>
    <div class="form-group">
        <label for="">Link Iimage</label>
        <input type="text" name="image" class="form-control" value="<?php echo $dataSendView['product_object']->hinhanhsanphan ?>">
    </div>
    <div class="form-group">
        <label for="">Category</label>
        <select name="categori_id" class="form-control">
            <?php  foreach($dataSendView['cate_list'] as $item):?>
                <option <?php if($item->id == $dataSendView['product_object']->id_sanpham) echo 'selected'; ?> value="<?php echo $item->id ?>"><?php echo $item->tenloaisanpham ?></option>
            <?php  endforeach ?>
        </select>
    </div>
    <button type="submit" class="btn btn-success waves-effect pull-right w-md waves-light m-b-5">Update</button>
</form>



