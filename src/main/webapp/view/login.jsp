<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

	<link rel="stylesheet" href="./css/login.css" />
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<style>
	.custom-btn{
		padding: 10px;
		color: red;
		background-color: white;
		width: 50%;
		display: block;
		margin: auto;
	}
	.custom-btn:hover{
		background-color:green;
		color:white;
	}
	.card{
		margin: auto;
		margin-top:50px;
		margin-bottom:100px;
	}
</style>
<body>
	<div class ="row">
		<div class="card">
<div class="login-box">
	<div class="login-snip">
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Đăng nhập</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Đăng ký</label>
		
		<div class="login-space">
		
			<form action="Login" method="post">
			    <div class="login">
			        <div class="group">
			            <label for="user" class="label">Tên đăng nhập</label>
			            <input id="user" name="user" type="text" class="input" placeholder="Nhập tên đăng nhập">
			        </div>
			        <div class="group">
			            <label for="pass" class="label">Mật khẩu</label>
			            <input id="pass" name="pass" type="password" class="input" data-type="password" placeholder="Nhập mật khẩu">
			        </div>
			        <div class="group">
			            <input id="check" name="check" type="checkbox" class="check" checked>
			            <label for="check"><span class="icon"></span> Duy trì đăng nhập</label>
			        </div>
			        <div class="group">
			            <button class="custom-btn" type="submit" >Đăng nhập</button>
			        </div>
			        <div class="hr"></div>
			        <div class="foot">
			            <a href="#">Quên mật khẩu?</a>
			            
			        </div>
			        <div class="alertaler-danger" role="alert" >
			 			<p class="text-danger">${mess}</p>
					</div>
			    </div>
			   
			</form>
		<form action="SignUp" method="post">
    <div class="sign-up-form">
        <div class="group">
            <label for="user" class="label">Tên đăng nhập</label>
            <input id="user" name="user" type="text" class="input" placeholder="Tạo tên đăng nhập" required>
        </div>
        <div class="group">
            <label for="pass" class="label">Mật khẩu</label>
            <input id="pass" name="pass" type="password" class="input" data-type="password" placeholder="Tạo mật khẩu" required>
        </div>
        <div class="group">
            <label for="repass" class="label">Nhập lại mật khẩu</label>
            <input id="repass" name="repass" type="password" class="input" data-type="password" placeholder="Nhập lại mật khẩu" required>
        </div>
        <div class="group">
            <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i>Đăng ký</button>
        </div>
        <div class="hr"></div>
<div class="foot">
            <label for="tab-1">bạn đã có tài khoản?</label>
        </div>
    </div>
</form>

		
		</div>
	</div>
</div>   
</div>

</div>

</body>
</html>