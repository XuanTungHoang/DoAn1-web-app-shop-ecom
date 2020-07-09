<form action="/page/user/update.php" method="post" role="form">
    <legend>Form edit user</legend>
    <ul>
        <?php foreach ($dataSendView['errors_update'] as $item):?>
            <li style="color: red"><?php echo $item ?></li>
        <?php endforeach; ?>
    </ul>
    <!--    phai co them id de xac dinh row-->
    <input name="id" type="hidden" value="<?php  echo $dataSendView['user_object']->id; ?>" />

    <div class="form-group">
        <label for="">Name</label>
        <input type="text" value="<?php echo $dataSendView['user_object']->ten ?>" class="form-control" name="input_name" id="" placeholder="Name...">
    </div>
    <div class="form-group">
        <label for="">Email</label>
        <input type="text" value="<?php echo $dataSendView['user_object']->email ?>" class="form-control" name="input_email" id="" placeholder="Email...">
    </div>
    <div class="form-group">
        <label for="">Password</label>
        <input type="password" value="<?php echo $dataSendView['user_object']->password ?>" class="form-control" name="input_password" id="" placeholder="Password...">
    </div>
    <button type="submit"  class="btn btn-success pull-right">Update</button>
</form>