<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!-- Thêm link đến jQuery UI CSS -->
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <title>Đặt lịch</title>
    <style type="text/css">	
     
	 
	#wrapper {width: 100%; margin-top: 0px;}
	#header {background: #0775d3; margin-top: 0px; padding:15px 0px 15px 0px; text-align: center;}
	#header h2 {width: 100%; margin:auto; color: #FFFFFF;}
	#container {width: 100%}
	#container h3 {color: #000;}
	#container #content {margin-top: 20px;}
    </style>
    <script>
      
        function updateInput() {
            var pinDropdown = document.getElementById('pinDropdown');
            var pinIdInput = document.getElementById('maPin');
            var selectedIndex = pinDropdown.selectedIndex;
            pinIdInput.value = selectedIndex + 1;
        }
        function updateInput2() {
            var chiNhanhDropdown = document.getElementById('chiNhanhDropdown');
            var chiNhanhIdInput = document.getElementById('maChiNhanh');
            var selectedIndex = chiNhanhDropdown.selectedIndex;
            chiNhanhIdInput.value = selectedIndex + 1;
        }
        
        $(function() {
            $("#datepicker").datepicker({
                dateFormat: 'dd/mm/yy',
            });
        });
    </script>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Đặt Lịch</h2>
        <a href="CRUDDatLichControll" style="color: orange ;"><h5> > Quay lại QL Đặt lịch </h5></a>
    </div>
</div>

<div id="container">
  <form action="CRUDDatLichControll" method="POST" onsubmit="return validateForm();">
        <input type="hidden" name="command" value="ADD"/>
        
        <table>
				<tbody>
					<tr>
						<td><label>Tên khách hàng:</label></td>
						<td><input type="text" name="tenKH" /></td>
					</tr>
					
						<tr>
							<td><label>Số Điện thoại:</label></td>
							<td><input type="text" name="soDT" /></td>
						</tr>
						
							<tr>
							<td><label>Địa chỉ:</label></td>
							<td><input type="text" name="diaChi" /></td>
						</tr>
						
						<tr>
							<td><label>Số Lượng:</label></td>
							<td><input type="text" name="soLuongPinXuLy" /></td>
						</tr>
					
						<tr>	 <!-- Thêm thông tin đặt lịch -->
				       <td> <label>Thời gian đặt lịch:</label></td>
					        <td><select name="thoiGian" style="width: 150px">
					            <option>8:00</option>
					            <option>9:00</option>
					            <option>10:00</option>
					            <!-- Thêm các giờ còn lại -->
					        </select>
					        <input type="text" id="datepicker"  name="ngayHen" placeholder="Chọn ngày" readonly />
					        </td>
					        
				        </tr>	
						
				 	 <tr>
						<td><label>mã Chi Nhanh:</label></td>
						<td>
							<select id="chiNhanhDropdown" onchange="updateInput2()" style="width: 200px; margin-top: 20px">
			    				<option>144 Nguyen Van Bao, Go Vap</option>
			    				<option>Cong Vien Lang Hoa, Go Vap</option>
			    				<option>Phố đi bộ Nguyễn Huệ, Quận 1</option>
							</select>
							<input type="text" id="maChiNhanh" name="maChiNhanh" style="margin-top: 10px" readonly />
						</td>

					</tr>
					
					<tr>
						<td><label>mã Pin:</label></td>
						<td>
							<select id="pinDropdown" onchange="updateInput()" style="width: 200px; margin-top: 20px">
			    				<option>Pin con ó đỏ</option>
			    				<option>AAA</option>
			    				<option>Pin vuông Golite 9v</option>
							</select>
							<input type="text" id="maPin" name="maPin" style="margin-top: 10px" readonly />
						<td>
					</tr> 
					
					
					
					<tr>
						<td><label>Mô tả:</label></td>
						<td><input type="text" name="moTa"  /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
    </form>
</div>

<!-- Thêm JavaScript để chọn ngày -->
<script>
    $(function() {
        $("#datepicker").datepicker({
            dateFormat: 'dd/mm/yy',
        });
    });
</script>

</body>
</html>