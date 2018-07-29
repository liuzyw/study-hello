<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<script src="<%=request.getContextPath() %>/static/js/jquery.min.js" type="text/javascript"></script>

<html>

    <head>
        
    </head>
    
    <body>
        
    <form id="payForm" action="/goAlipay" method="post">
        <table>
        	<tr>
        		<td>
        			订单编号: 33213
        		</td>
        	</tr>
        		<td>
        			产品名称: 苹果
        		</td>
        	<tr>

        	</tr>
        		<td>
        			<input type="submit" value="前往支付宝进行支付">
        			
        			<input type="button" value="微信扫码支付" onclick="wxpayDisplay()">
        		</td>
        	</tr>
        </table>
    </form>
    
    	
    </body>
    
</html>

</script>

