<div class="row">
    <div class="col-sm-12">
        <div class="card-box">
            <form  style="width: 30%;float: right "  class="form-group" method="get" action="/page/product/index.php">
                <input style="border: 1px solid cornflowerblue;" class="form-control" type="text" name="keyword">
                <input  class="form-control" value="<?php echo $_GET['page'];?>" type="hidden" name="page">
                <button style="margin-top: 5px ;float: right" class="btn btn-success" type="submit">Search</button>
            </form>
            <br>
            <br>
            <a href="/page/product/add.php" type="button" class="btn btn-success waves-effect w-md waves-light m-b-5">ADD</a>
            <br>
            <br>
            <div class="table-rep-plugin">
                <?php if(count($dataSendView['product_list'])>0){ ?>
                <div class="table-responsive" data-pattern="priority-columns">
                    <table id="tech-companies-1" class="table  table-striped">
                        <thead>
                        <tr>
                            <th >ID</th>
                            <th >Name</th>
                            <th >Price</th>
                            <th>Link Image</th>
                            <th >Description</th>
                            <th>Category</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?php foreach ($dataSendView['product_list'] as $item): ?>
                            <tr>
                                <td><?php echo $item->id?></td>
                                <td><?php echo $item->tensanpham?></td>
                                <td><?php echo $item->giasanpham?></td>
                                <td><?php echo $item->hinhanhsanphan?></td>
                                <td><?php echo $item->motasanpham?></td>
                                <td><?php echo $item->cate_name?></td>
                                <td>
                                    <a href="/page/product/edit.php?id=<?php echo $item->id?>" class="btn btn-icon waves-effect waves-light btn-primary m-b-5"> <i class="fa fa-wrench"></i> </a>
                                    <a  href="/page/product/delete.php?id=<?php echo $item->id?>" class="btn btn-icon waves-effect waves-light btn-danger m-b-5"> <i class="fa fa-remove"></i> </a>
                                </td>
                            </tr>
                        <?php  endforeach; ?>
                        </tbody>
                    </table>
                    <div style="float:right" class="btn-group m-b-10">
                        <div class="btn-group">
                            <a href="/page/product/?page=<?php if($_GET['page']>1){
                                echo $_GET['page']-1;
                            } ?>" type="button" class="btn btn-default <?php if($_GET['page']==1){echo 'disabled';} ?>">
                                Previous
                            </a>
                        </div>
                        <?php for ($i=1;$i<=$dataSendView['total_page'];$i++) { ?>
                            <a href="/page/product/?page=<?php echo $i; if(!empty($_GET['keyword'])) echo '&keyword='.$_GET['keyword'] ?>"
                               type="button"
                               class="btn btn-<?php if($_GET['page']==$i){echo 'primary';} ?>">
                                <?php echo $i; ?>
                            </a>
                        <?php }?>
                        <div class="btn-group">
                            <a href="/page/product/?page=<?php if($_GET['page']<$dataSendView['total_page']){
                                echo $_GET['page']+1;
                            } ?>" type="button" class="btn btn-default <?php if($_GET['page']==$dataSendView['total_page']){echo 'disabled';} ?>">
                                Next
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <?php } else{echo '<h1>No data</h1>';}?>
        </div>
    </div>
</div>
<script></script>