/**
 * 检查信息输入
 */
function check()
{
	var x1=document.getElementById("searchItem").value;
	if(x1=="")
		{
		alert("请输入要搜索的内容");
		return false;
		}
	return true;
}