<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div>
<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importAll()">一键导入索引库</a>
</div>
<script type="application/javascript">
    function importAll() {
        $.ajax({
            url:"/index/importall",
            type:"GET",
            success:function(data){
                if(data.status==200){
                    $.messager.alert('提示','导入成功');
                } else {
                    $.messager.alert('提示','导入失败');
                }
            }
        });
    }

</script>