<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function() {
    // 给文本框加个keypress，即键盘按下的时候判断
	$("#ctl00_ContentPlaceHolder1_txtSumValue").keypress(function(event) {
        if (!$.browser.mozilla) {
            if (event.keyCode && (event.keyCode < 48 || event.keyCode > 57) && event.keyCode != 46) {
                // ie6,7,8,opera,chrome管用
                event.preventDefault();
            }
        } else {
        if (event.charCode && (event.charCode < 48 || event.charCode > 57) && event.keyCode != 46) {
                // firefox管用
                event.preventDefault();
            }
        }
    });

    // 当文本框失去焦点的时候，检测输入的是否是数字
    $("#ctl00_ContentPlaceHolder1_txtSumValue").blur(function() {
        var input = $(this);
        var v = $.trim(input.val());
        //alert("输入值：" + v);
        var reg = new RegExp("^[0-9]+(.[0-9]{2})?$", "g");
        if (!reg.test(v)) {
            alert("请输入一个数字，最多只能有两位小数！");
            input.val("0");
        }
    });
});
</script>
</head>
<body>

</body>
</html>