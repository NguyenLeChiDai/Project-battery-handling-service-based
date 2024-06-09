<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách đặt lịch</title>
<link type="text/css" rel="stylesheet" href="css/styleCRUD.css">
</head>
<script>
    function sortProductsByPriceAscending() {
        window.location.href = 'CRUDDatLichControll?command=LIST_BY_PRICE_ASCENDING';
    }
    
    function sortProductsByPriceDescending() {
        window.location.href = 'CRUDDatLichControll?command=LIST_BY_PRICE_DESCENDING';
    }
    
    function listDongHo() {
        window.location.href = 'CRUDDatLichControll?command=LIST';
    }
    
    function searchDatLich() {
        var searchInput = document.getElementById('searchInput').value;
        window.location.href = 'CRUDDatLichControll?command=SEARCH&soDT=' + searchInput;
    }

    function filterByBrand() {
        var selectedBrandId = document.getElementById('brandFilter').value;
        window.location.href = 'CRUDDatLichControll?command=LIST_BY_BRAND&thuongHieuId=' + selectedBrandId;
    }
</script>
<body>
	<div id="wrapper">
	
		<div id="header">
       	
		                <a href="PinServlet" style="color: white;"><h3>Trang chủ</h3></a>
		                <a href="CRUDServlet" style="color: orange ;"><h5> > Quản Lý Pin </h5></a>
		                <a href="CRUDDatLichControll" style="color: orange ;"><h5> > Quản Lý Đặt Lịch </h5></a>

			<h2>Quản Lý Đặt Lịch</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			
			<input type="button" value="thêm lịch" 
				   onclick="window.location.href='add-dat-lich-form.jsp'; return false;"
				   class="add-button"
				   style="margin-left: 0px; margin-right: 20px"/>
				   
			<input type="button" value="Giá: tăng dần" onclick="sortProductsByPriceAscending()"
				class="add-button"
				style="margin-left: 0px; margin-right: 20px"/>
				
			<input type="button" value="Giá: giảm dần" onclick="sortProductsByPriceDescending()"
				class="add-button"
				style="margin-left: 0px; margin-right: 20px">
				
			<input type="button" value="Bỏ lọc" onclick="listDongHo()"
				class="add-button"
				style="margin-left: 0px; margin-right: 20px">
				
			<input type="text" id="searchInput" placeholder="Nhập số điện thoại khách hàng..." style="width: 200px">
			<input type="button" value="Tìm kiếm" onclick="searchDatLich()" class="add-button" style="margin-left: 0px; margin-right: 20px">
			
			<select id="brandFilter" onchange="filterByBrand()" style="width: 200px">
			    <option value="">Chọn Thương Hiệu</option>
				<c:forEach var="brand" items="${LIST_BRANDS}">
					<option value="${brand.thuongHieuId}">${brand.name}</option>
				</c:forEach>
			</select>	
			<table style="width: 1200px">
				<tr>
					<th>Tên khách hàng</th>
					<th>Số điện thoại</th>
					<th>Thời Gian</th>
					<th>Ngày hẹn</th>
					<th>Mã pin</th>
					<th>Mã chi nhánh</th>
					<th>Số lượng đặt</th>
					<th>Mô tả</th>
					<th>Chi phí tạm tính</th>
					<th>Hành động</th>
				</tr>
				
				<c:forEach var="tempDatLich" items="${LIST_DAT_LICH}">
					
					<c:url var="tempLink" value="CRUDDatLichControll">
						<c:param name="command" value="LOADDATLICH" />
						<c:param name="maDatLich" value="${tempDatLich.maDatLich}" />
					</c:url>

					<c:url var="deleteLink" value="CRUDDatLichControll">
						<c:param name="command" value="DELETE" />
						<c:param name="maDatLich" value="${tempDatLich.maDatLich}" />
					</c:url>	
					
					<c:url var="chapNhanLink" value="CRUDDatLichControll">
						<c:param name="command" value="CHAPNHAN" />
						<c:param name="maPin" value="${tempDatLich.pin.maPin}" />
						<c:param name="maDatLich" value="${tempDatLich.maDatLich}" />
					</c:url>																	
					  <tr>
					  	<td>${tempDatLich.tenKH}</td>
					  	<td>${tempDatLich.soDT}</td>
				        <td>${tempDatLich.thoiGian}</td>
				        <td>${tempDatLich.ngayHen}</td>   
				        <td ><input style="width: 30px" type="text" name="maPin" readonly="readonly"
									   value="${tempDatLich.pin.maPin}" /></td>
				        <td>${tempDatLich.chiNhanh.maChiNhanh}</td>
				        <td><input style="width: 30px" type="text" name="soLuong" readonly="readonly"
									   value="${tempDatLich.soLuongPinXuLy}" /></td>
				        <td>${tempDatLich.moTa}</td>
				        
				         <td>${tempDatLich.chiPhi}</td>
				        
				        
				        
				        <td>
							<a href="${tempLink}">Cập nhật</a> 							  
							<a href="${deleteLink}"
							onclick="if (!(confirm('Bạn có chắn chắn muốn xóa thông tin đặt lịch này?'))) return false">
							Xóa</a>	
							<a href="${chapNhanLink}&soLuongPinXuLy=${tempDatLich.soLuongPinXuLy}" onclick="if (!(confirm('Bạn có chắc chắn muốn chấp nhận thông tin đặt lịch này?'))) return false">Chấp nhận</a>
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>
</html>