package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.PinModel;
import entity.GioHang;
import entity.Pin;





@WebServlet("/PinServlet")
public class PinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	
	private PinModel pinModel;
	
	@Resource(name="jdbc/PinTaiChe")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();

		try {
			pinModel = new PinModel(dataSource);
			
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String command = request.getParameter("command");
	        if (command == null) {
	            command = "TEST";
	        }
	        switch (command) {
	            case "TEST":
	            	dsPinVaTatCaPin(request, response);
	                break;
	            case "LOADTHONGTIN":
	            	ShowViewThongTinDatLich(request, response);	               
	                break;
	                
	            case "LOADCHITIET":
	            	ShowViewItem(request, response);	               
	                break;

	            case "TAIKHOAN":
	                taiKhoan(request, response); 
	                
	                break;
	            case "TAIKHOANCHITIET":
	                taiKhoan(request, response);
	                break;
	            case "TINTUC":
	                tinTuc(request, response);
	                break;
	                
	            case "THONGTIN":
	                thongTin(request, response);
	                break;
	            case "THONGBAO":
	                thongBao(request, response);
	                break;
	            case "DIACHIGIAO":
	                diaChi(request, response);
	                break;
	            case "TATCAPIN":
	                tatCaPin(request, response);
	                break;
	                
	            case "SEARCH":
				    searchPin(request, response);
				    break;
				/*
				 * case "DELETE": xoaGioHang(request, response); break;
				 */
	                
	                
	            default:
	            	dsPinVaTatCaPin(request, response);               
	                break;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//DANH SÁCH PIN
	private void dsPinVaTatCaPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    List<Pin> dsTatCaPin = pinModel.getALLPin();
	    

	    request.setAttribute("PIN", dsTatCaPin);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/view/trangchu.jsp");
	    dispatcher.forward(request, response);
	    
	}
	
	//SHOW CHI TIẾT PIN
	public void ShowViewItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int maPin = Integer.parseInt(request.getParameter("maPin"));
		Pin pin = pinModel.getPinByid(maPin);
		request.setAttribute("PIN", pin);
		request.getRequestDispatcher("/view/chitietpin.jsp").forward(request, response);
	}
	
	//SHOW CHI TIẾT Thông tin đặt lịch
		public void ShowViewThongTinDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
			int maPin = Integer.parseInt(request.getParameter("maPin"));
			Pin pin = pinModel.getPinByid(maPin);
			request.setAttribute("PIN", pin);
			request.getRequestDispatcher("/view/datlich.jsp").forward(request, response);
		}
	
	
	//TÀI KHOẢN
	private void taiKhoan(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/login.jsp");
			dispatcher.forward(request, response);		
		}
	
	
	// TIN TỨC
	private void tinTuc(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/tintuc.jsp");
			dispatcher.forward(request, response);		
		}
	
	//THÔNG TIN
	private void thongTin(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/thongtinlienhe.jsp");
			dispatcher.forward(request, response);		
		}
	
	//THÔNG BÁO
		private void thongBao(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/thongbao.jsp");
				dispatcher.forward(request, response);		
			}
		
	//ĐỊA CHỈ GIAO HÀNG
		private void diaChi(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {
						
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/diachigiaohang.jsp");
				dispatcher.forward(request, response);		
			}
		
		//TẤT CA Pin
		private void tatCaPin(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {		
			 List<Pin> dsTatCaPin = pinModel.getALLPin();
			 request.setAttribute("PIN", dsTatCaPin);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/view/tatcapin.jsp");
					dispatcher.forward(request, response);		
					}
		
		//TÌM Pin
		private void searchPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String tenPin = request.getParameter("tenPin");
		    List<Pin> listDongHo = pinModel.searchPinByName(tenPin);
		    request.setAttribute("PIN", listDongHo);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/view/timsanpham.jsp");
		    dispatcher.forward(request, response);
		}

	
	
	
	
	
	
}
