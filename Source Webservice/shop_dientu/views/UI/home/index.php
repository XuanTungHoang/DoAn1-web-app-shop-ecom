<h1 style="margin-left: 10px;color: green">Tìm kiếm- Thống kê</h1>
<br><br>
<form action="/page/home/home.php" method="post" style="margin-left: 10px">
    From
    <input type="date" name="date_from" value="<?php  if(!empty($_POST['date_from'])) echo $_POST['date_from'];?>">

    To
    <input type="date" name="date_to" value="<?php if(!empty($_POST['date_to'])) echo $_POST['date_to'] ?>">
    <button type="submit">Xuất</button>
</form>
<br><br>

<div class="table-rep-plugin">
    <div class="table-responsive" data-pattern="priority-columns">

        <table id="tech-companies-1" class="table  table-striped" >
            <thead>
            <tr>
                <th >ID</th>
                <th >Tên khách hàng</th>
                <th >Địa chỉ</th>
                <th >Số điện thoại</th>
                <th>Tổng tiền</th>
                <th>Trạng thái hiện tại</th>
                <th >Ngày mua</th>
            </tr>
            </thead>
            <tbody>
            <?php  if(!empty($dataSendView['list_orders_result']))  foreach ($dataSendView['list_orders_result'] as $item): ?>
                <tr>

                    <td><?php echo $item->id?></td>
                    <td><?php echo $item->tenkhachhang?></td>
                    <td><?php echo $item->diachi?></td>
                    <td><?php echo $item->sodienthoai?></td>
                    <td><?php echo $item->tongtien?></td>
                    <td><?php echo $item->trangthai?></td>
                    <td><?php echo $item->create_at?></td>
                </tr>
            <?php  endforeach; ?>
            </tbody>
        </table>
    </div>
</div>


