/**
 * Created by Administrator on 2016/10/26.
 */
function trxorderExcel() {
    $("#searchForm").prop("action","/admin/orderDetial/trxorderExcel");
    $("#searchForm").submit();
    $("#searchForm").prop("action","/admin/orderDetial/list")
}