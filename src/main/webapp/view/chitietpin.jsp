<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
 <head>
  <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="icon" href="img/logo-mini.jpg" type="image/x-icon" />
    <link rel="stylesheet" href="css/style_trangchu.css" type="text/css"/>
    <link rel="stylesheet" href="css/reset.css" />
    
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="fontawesome/css/all.css" />
    
   
    
    
    <script src="js/jquery.js"></script>
    <script src="js/style.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>Chi tiết đồng hồ</title>
</head>

<body>


  <script type="text/javascript">
        $(document).ready(function() {
            $('.list-menu li').hover(function() {
                    $(this).find(".sub-menu").stop().fadeIn(500);
                },
                function() {
                    $(this).find(".sub-menu").stop().fadeOut(300);
                });
        });
        
        function searchPin() {
            var searchInput = document.getElementById('searchInput').value;
            window.location.href = 'PinServlet?command=SEARCH&tenPin=' + searchInput;
        }
        
        function filterByBrand() {
            var selectedBrandId = document.getElementById('brandFilter').value;
            window.location.href = 'CRUDServlet?command=LIST_BY_BRAND&thuongHieuId=' + selectedBrandId;
        }
    </script>
    
    
    
<input type="hidden" name="command" value="TEST">

            
 <div id="main">
    <!-- ============ HEADER ===============-->
    <div id="header">
        
        <div class="banner">
            <div class="login">
              
                <div class="sub-login">
                    <p><a href="tel:0354985272"><i class="fas fa-phone-alt"></i> HOTLINE: 0354985272</a></p>
                    <ul>
                    
                    <!-- Ẩn nút TK sau khi đăng nhập -->
                    <c:if test ="${sessionScope.acc == null}">
                         	<li>
                            <p><a href="PinServlet?command=TAIKHOAN"><i class="fas fa-user"></i>Tài khoản</a></p>
							</li>
                        </c:if>
                       
                    </ul>
                    <p><a href="#"><i class="fas fa-map-marker-alt" ></i>Hệ thống cửa hàng</a></p>
                    <a href="?command=VIEWCART" class="cart-link">
					  <i class="fas fa-shopping-cart" style="margin-left: 1000px;"></i>
					  <span class="cart-counter">1</span>
					  <span class="cart-title">Giỏ hàng</span>
					</a>
                    
                     </div>
            </div>
            <div class="sub-banner">
                <a href="PinServlet"><img class="logo" style="color: red; width: 60%; height:84px;  " src="imgPin/logo.jpeg" alt="" /></a>
                
                <form action>
                    <input type="search" id="searchInput" placeholder="Bạn đang tìm dòng pin nào..." />
                    
                </form>
                <i class="fas fa-search" onclick="searchPin()" ></i>
            </div>
        </div>
    </div>   
</div> 

<!-- ============END HEADER=============== -->
        <!-- ============MENU=============== -->
        

    <nav style="margin-left: 0px;">
        <ul class="list-menu" style="margin-left: 50px;">
        
        <!--  Hello Người dùng-->
        <c:if test="${not empty sessionScope.acc}">
						    <c:if test="${not empty sessionScope.acc.user}">
						        <li class="f">
						            <a  href="#" style="color: while;">Hello ${sessionScope.acc.user}</a>
						        </li>
						        
						     
						        <li class="f">
						            <a style="color: while;" href="LogoutControll">Logout</a>
						        </li>
						    </c:if>
						    
						    
						</c:if>
    
        
            <li class="f"><a style="color: black; margin-left: 150px;" href="PinServlet">Trang chủ</a></li>
            
            
         
            <li class="dmsp" ><a href="#">THƯƠNG HIỆU<i class="fas fa-caret-down"></i></a>
                <ul class="sub-menu">
               
                <c:forEach var="brand" items="${LIST_BRANDS}">
					<li> value="${brand.thuongHieuId}">${brand.name}</li>
				</c:forEach>
                    <li ><a href="#pincono" >Pin con ó</a></li>
                    <li><a href="#AAA">AAA</a></li>
                    <li><a href="#pinvuong">Pin vuông</a></li>
                </ul>
          </li>

            <li>
	            <a href="#">TIN TỨC<i class="fas fa-caret-down"></i></a>
	             <ul class="sub-menu">
	                
	                <li><a href="PinServlet?command=TINTUC">TIN XỬ LÝ PIN</a></li>
	               
	            </ul>
           </li>
           
           <li><a href="PinServlet?command=THONGTIN">THÔNG TIN</a></li>
           
           <c:if test ="${sessionScope.acc.isAdmin == 1}">
	           
	           <li>
	            <a href="#">QUẢN LÝ<i class="fas fa-caret-down"></i></a>
	             <ul class="sub-menu">
	                
	                <li><a href="CRUDServlet">QUẢN LÝ PIN</a></li>
	                <li><a href="CRUDDatLichControll">QUẢN LÝ ĐẶT LỊCH</a></li>
	               
	            </ul>
           </li>
           
          </c:if>
       </ul>
  </nav>
  
 
      <div class="slider">
  <img src="imgPin/anhpin2.jpg" style="height: 400px;">
 </div>
 <div id="wrapper">
<!-- ============END HEADER=============== -->
    
<div class="slider"></div>
<!-- THONG TIN -->
<div class="container-1">
    <div class="row">
        <div class="row ml-3">
            <a href="PinServlet"><h6>Trang chủ</h6></a>
            <h6 style="color: gray;"> >Chi tiết pin</h6>
        </div>
    </div>
    <div class="border-bottom">
        <a href="https://vi-vn.facebook.com"><img src="img/img-news/face3.PNG" alt="" style="border-radius: 5px; width: 30px; height: 30px; float:right"></a>
        <a href="https://twitter.com/?lang=vi"><img src="img/img-news/twitt3.PNG" alt="" style="border-radius: 5px; width: 30px; height: 30px; float: right;"></a>
        <a href="https://www.pinterest.com/"><img src="img/img-news/print2.PNG" alt="" style="border-radius: 5px; width: 30px; height: 30px; float: right;"></a>
        <h3 style="font-family:courier,arial,helvetica; text-align: center;">Thông tin chi tiết</h3>
    </div>
    
    <form action="PinServlet" method="get">
        
      
        <div class="row">
        	    <input name="command" type="hidden" value="LOADTHONGTIN">
				<input name="maPin" type="hidden" value="${PIN.getMaPin()}">
            <div class="col-5 ml-2">
                <td><img src="${PIN.getHinhAnh()}" alt="Image" width="300" height="250"></td>
            </div>
            <div class="col-6">
                <h3>${PIN.getTenPin()}</h3>
                <p><span class="text-danger">${PIN.getGiaThu()}đ /20 Pin</span></p>
                <p class="col-10 border-bottom mt-10" ><strong>Mô tả:</strong>:</p>
                <div class="row border-bottom ml-2">
                    <div class="col-12">
                        <p>${PIN.getMoTa()}</p>
                    </div>
                </div>
				
              <!--   THEM Đặt Lịch -->
              <c:if test ="${sessionScope.acc == null}">
                         	<li>
                            <a href="PinServlet?command=TAIKHOANCHITIET"><span class="btn btn-danger" style="display: inline-block; width: 300px; margin-top: 10px; cursor: pointer;" onclick="addToCart()">Đăng nhập để đặt lịch xử lý pin</span></a>
							</li>
                        </c:if>
              
              <c:if test="${not empty sessionScope.acc}">
						    
						     <button type="submit" class="btn btn-danger " style="width: 300px; margin-top: 10px;">Đặt lịch xử lý pin</button>	
						         
			</c:if>
			
			 
            </div>
        </div>
    </form>
    <div class="row  ml-3">
        <div class="col-4"><a href="https://twitter.com/?lang=vi"><img src="img/Twitter.PNG" alt="" style="width: 100px; border-radius: 10px;"></a></div>
        <div class="col-4"><a href="https://vi-vn.facebook.com/"></a><img src="img/Facebook.PNG" alt="" style="width: 100px; height: 55px; border-radius: 10px"></a></div>
        <div class="col-4"><a href="https://www.instagram.com/"><img src="img/Ins.PNG" alt="" style="width: 100px; height: 55px; border-radius: 10px"></a></div>
    </div>
</div>               
            
                  

 <!-- Footer -->
     <div class="footer">
        <div class="container">
             <div class="footer-main">
                <div class="footer-main__map">
                    <img src="imgPin/tai-che-pin-ac-quy.jpg" width="99%" height="99%"  style="border:0;" allowfullscreen="" loading=""></img>
                </div>

                <div class="footer-list">
                    <div class="footer-wraper-1">
                        <p class="footer-list__title">
                                <strong> DBD.com ! </strong> 
                        </p>

                        <ul class="footer-address font-15">
                            <li class="footer-address__items">
                                <i class="footer-icon fas fa-map-marker-alt"></i><span class="footer-address__items-none">Address: HCM - 12 Nguyễn Văn Bảo - Quận Gò Vấp</span>
                            </li>
                             <li class="footer-address__items">
                                <i class="footer-icon fas fa-phone-alt"></i><a href="#" class="footer-address__items-link" tel: >0354.985.272</a>
                            </li>
                            <li class="footer-address__items">
                                <i class="footer-icon fas fa-envelope"></i><a href="#" class="footer-address__items-link">sinhviennguyenlechidai@gmail.com</a>
                            </li>
                        </ul>

                        <div class="footer-info">
                            <i class="footer-info__icon fab fa-facebook-f"></i>
                            <i class="footer-info__icon fab fa-youtube"></i>
                            <i class="footer-info__icon fab fa-instagram"></i>
                        </div>

                        <p class="footer-desc">Nhằm hưởng ứng phong trào Ngày Trái đất, DBD.com hân hạnh triển khai chiến dịch thu gom pin cũ đã qua sử dụng với
                         mong muốn góp phần bảo vệ môi trường và nâng cao ý thức cộng đồng về tác hại của rác thải pin điện tử.
                        </p>
                    </div>
                </div>   
            </div>
              
        </div>
            <div class="copyright">
                    <div class="copyright-main">
                      @2024 - <span class="copyright-main__color">DBD.com</span> 
                      <span class="copyright-mobile-1"> | </span> 
                      <span class="copyright-mobile-2">
                        WEB
                        <a href="DongHoServlet?command=THONGTIN"><span style="color: orange;">Nguyễn Lê Chí Đại - LƯƠNG TRẦN GIA BẢO - NGUYỄN CÔNG DANH</span></a>
                      </span>
                    </div>

                    <div class="top">
                        <a class="top-link" href="#">Trở lại đầu trang <i class="top-icon fas fa-chevron-up"></i></a>
                    </div>
            </div>  

        </div>
</body>
</html>