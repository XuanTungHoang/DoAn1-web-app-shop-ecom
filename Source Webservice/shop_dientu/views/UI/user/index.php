<a  href="/page/user/add.php" type="button" class="btn btn-success waves-effect w-md waves-light m-b-5">ADD</a>

<table class="table table-dark">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <?php  foreach ($dataSendView['user_list'] as $item){ ?>
        <tr>
            <td><?php echo $item->id ?></td>
            <td><?php echo $item->ten ?></td>
            <td><?php echo $item->email ?></td>
            <td>
                <a href="/page/user/edit.php?id=<?php echo $item->id?>" class="btn btn-icon waves-effect waves-light btn-primary m-b-5"> <i class="fa fa-wrench"></i> </a>
                <a  href="/page/user/delete.php?id=<?php echo $item->id?>" class="btn btn-icon waves-effect waves-light btn-danger m-b-5"> <i class="fa fa-remove"></i> </a>
            </td>
        </tr>
    <?php } ?>
    </tbody>
</table>

