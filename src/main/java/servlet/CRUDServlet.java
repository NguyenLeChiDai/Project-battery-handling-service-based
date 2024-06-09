package servlet;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import model.PinModel;
import model.ThuongHieuModel;
import entity.ChiNhanh;
import entity.KhachHang;
import entity.Pin;
import entity.ThongTinDatLich;
import entity.ThuongHieu;

/**
 * Servlet implementation class CRUDServlet
 */
@WebServlet("/CRUDServlet")
public class CRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/PinTaiChe")
	private DataSource dataSource;
	private PinModel pinModel;
	private ThuongHieuModel thuongHieuModel;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			pinModel = new PinModel(dataSource);
			thuongHieuModel = new ThuongHieuModel(dataSource);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			listBrand(request, response);
			String command = request.getParameter("command");
			if (command == null) {
				command = "LIST";
			}
			switch (command) 
			{		
				case "LIST":    listPin(request, response);
					break;		
			
				case "ADDPIN":     addPin(request, response);
					break;	
				
				case "LOAD":    loadPin(request, response);
					break;	
					
				case "DELETE":	deletePin(request, response);
					break;
					
				case "UPDATEPIN":	updatePin(request, response);
				break;
					
				
				case "SEARCH":
				    searchPin(request, response);
				    break;
				   
				default:
					listPin(request, response);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServletException();
		}
	}

	private void listBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ThuongHieu> listBrands = thuongHieuModel.getAllBrands();
		request.setAttribute("LIST_BRANDS", listBrands);
	}

	

	private void searchPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tenPin = request.getParameter("tenPin");
	    List<Pin> listPin = pinModel.searchPinByName(tenPin);
	    // Tính tổng số tiền đã chi
	    int tongChiPhi = 0;
	    for (Pin pin : listPin) {
	        int chiPhi = pinModel.tinhChiPhi(pin.getMaPin());
	        pin.setChiPhi(chiPhi);
	        tongChiPhi += chiPhi; // Tổng hóa chi phí từng món hàng
	    }

	    // Đặt giá trị tổng chi phí vào thuộc tính của request
	    request.setAttribute("TONG_CHI_PHI", tongChiPhi);
	    request.setAttribute("LIST_PIN", listPin);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("list-pin.jsp");
	    dispatcher.forward(request, response);
	}


	private void loadPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("maPin"));
	    Pin pin = pinModel.getPinByid(id);
	    request.setAttribute("PIN", pin);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("update-pin-form.jsp");
	    dispatcher.forward(request, response);        
	}




	private void listPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Lấy danh sách các pin
	    List<Pin> listPin = pinModel.getALLPin();

	    // Tính tổng số tiền đã chi
	    int tongChiPhi = 0;
	    for (Pin pin : listPin) {
	        int chiPhi = pinModel.tinhChiPhi(pin.getMaPin());
	        pin.setChiPhi(chiPhi);
	        tongChiPhi += chiPhi; // Tổng hóa chi phí từng món hàng
	    }

	    // Đặt giá trị tổng chi phí vào thuộc tính của request
	    request.setAttribute("TONG_CHI_PHI", tongChiPhi);
	    request.setAttribute("LIST_PIN", listPin);

	    // Forward request đến trang JSP
	    RequestDispatcher dispatcher = request.getRequestDispatcher("list-pin.jsp");
	    dispatcher.forward(request, response);
	}
	
	//ADD pin
	private void addPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // int maPin = Integer.parseInt(request.getParameter("maPin"));
		  String tenPin = request.getParameter("tenPin");
		  
		  String giaThuString = request.getParameter("giaThu");
		  float giaThu = Float.parseFloat(giaThuString);
		  
		  String soLuongString = request.getParameter("soLuong");
		  int soLuong = Integer.parseInt(soLuongString);
	      
		  String moTa = request.getParameter("moTa");
	      
	      String thuongHieuIdString = request.getParameter("thuongHieuId");
	      int thuongHieuId = Integer.parseInt(thuongHieuIdString);
	      
	      String hinhAnh = request.getParameter("hinhAnh");
	      Pin pin = new Pin(tenPin, giaThu, soLuong, moTa, thuongHieuId, hinhAnh);
		  pinModel.addThongTinPin(pin);
		  listPin(request, response);
		// TODO Auto-generated method stub
		
	}
	
	//delete PIN
	private void deletePin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("maPin");
		pinModel.deletePin(id);
		listPin(request, response);
		
	}
	
	//UPDATE pin

	private void updatePin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("maPin"));
		
		  String tenPin = request.getParameter("tenPin");
		  String giaThuString = request.getParameter("giaThu");
		  float giaThu = Float.parseFloat(giaThuString);
		  String soLuongString = request.getParameter("soLuong");
		  int soLuong = Integer.parseInt(soLuongString);
	      String moTa = request.getParameter("moTa");
	      String thuongHieuIdString = request.getParameter("thuongHieuId");
	      int thuongHieuId = Integer.parseInt(thuongHieuIdString);
	      String hinhAnh = request.getParameter("hinhAnh");
	      Pin pin = new Pin(id, tenPin, giaThu, soLuong, moTa, thuongHieuId, hinhAnh);
		  pinModel.updatePin(pin);
		  listPin(request, response);
		// TODO Auto-generated method stub
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
