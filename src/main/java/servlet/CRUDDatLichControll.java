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

import model.DatLichModel;
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
@WebServlet("/CRUDDatLichControll")
public class CRUDDatLichControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/PinTaiChe")
	private DataSource dataSource;
	private PinModel pinModel;
	private DatLichModel datLichModel;
	private ThuongHieuModel thuongHieuModel;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDDatLichControll() {
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
			datLichModel = new DatLichModel(dataSource);
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
				case "LIST":    listThongTinDatLich(request, response);
					break;		
				
				case "ADD":     addThongTinDatLich(request, response);
					break;
				case "ADDDATLICH":     addThongTinDatLichVsGioHang(request, response);
				break;
					
				case "LOADDATLICH":    loadDatLich(request, response);
					break;	
						
				case "UPDATE":	updateDatLich(request, response);
					break;	
					
				case "DELETE":	deleteThongTinDatLich(request, response);
					break;
					
				case "CHAPNHAN":	chapNhanDatLich(request, response);
				
				break;
					

				case "SEARCH":
				    searchDatLich(request, response);
				    break;
						
 
				default:
					listThongTinDatLich(request, response);
			}
		}
		catch (Exception e) {
			throw new ServletException();
		}
	}

	private void listBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ThuongHieu> listBrands = thuongHieuModel.getAllBrands();
		request.setAttribute("LIST_BRANDS", listBrands);
	}


	//Tìm lịch đặt theo số điện thoại
	private void searchDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String soDT = request.getParameter("soDT");
	    List<ThongTinDatLich> listDatLich = datLichModel.searchDatLichBySoDT(soDT);
	    
	    // Tính toán và lưu chi phí tạm tính
	    for (ThongTinDatLich datLich : listDatLich) {
	        int chiPhiTamTinh = pinModel.tinhChiPhiTamTinh(datLich.getMaDatLich());
	        datLich.setChiPhi(chiPhiTamTinh);
	    }
	    
	    request.setAttribute("LIST_DAT_LICH", listDatLich);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("list-dat-lich.jsp");
	    dispatcher.forward(request, response);
	}

	private void deleteThongTinDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("maDatLich");
		datLichModel.deleteThongTinDatLich(id);
		listThongTinDatLich(request, response);
	}

	private void updateDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("maDatLich"));
		  String tenKH = request.getParameter("tenKH");
	      String soDT = request.getParameter("soDT");
	      String diaChi = request.getParameter("diaChi");
	      String thoiGian = request.getParameter("thoiGian");
	      String ngayHen = request.getParameter("ngayHen");
	      
	      String  maChiNhanhString = request.getParameter("maChiNhanh");
	      int maChiNhanh = Integer.parseInt(maChiNhanhString);
	      
	      String maPinString = request.getParameter("maPin");
	      int maPin = Integer.parseInt(maPinString);
	      
	      String  soLuongPinXLString = request.getParameter("soLuongPinXuLy");
	      int soLuongPinXL = Integer.parseInt(soLuongPinXLString);
	      
	      String moTa = request.getParameter("moTa");
	      
		ThongTinDatLich datLich = new ThongTinDatLich(id, tenKH, soDT, diaChi, thoiGian, ngayHen, maChiNhanh, maPin,soLuongPinXL, moTa);
		datLichModel.updateThongTinDatLich(datLich);
		listThongTinDatLich(request, response);
	}

	private void loadDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("maDatLich");
		ThongTinDatLich datLich  = datLichModel.getDatLich(id);
		request.setAttribute("DATLICH", datLich);
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-dat-lich-form.jsp");
		dispatcher.forward(request, response);		
	}

	private void addThongTinDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // Lấy dữ liệu từ form
	      String tenKH = request.getParameter("tenKH");
	      String soDT = request.getParameter("soDT");
	      String diaChi = request.getParameter("diaChi");      
	      String thoiGian = request.getParameter("thoiGian");
	      String ngayHen = request.getParameter("ngayHen");
	      
	      String  maChiNhanhString = request.getParameter("maChiNhanh");
	      int maChiNhanh = Integer.parseInt(maChiNhanhString);
	      
	      String maPinString = request.getParameter("maPin");
	      int maPin = Integer.parseInt(maPinString);
	      
	      String  soLuongPinXLString = request.getParameter("soLuongPinXuLy");
	      int soLuongPinXL = Integer.parseInt(soLuongPinXLString);
	      
	      String moTa = request.getParameter("moTa");
	      
	      // Tạo đối tượng ThongTinDatLich và gọi hàm addThongTinDatLich
	      ThongTinDatLich thongTinDatLich = new ThongTinDatLich(tenKH, soDT, diaChi, thoiGian, ngayHen, new ChiNhanh(maChiNhanh), new Pin(maPin), soLuongPinXL, moTa);
	      datLichModel.addThongTinDatLich(thongTinDatLich);
	      listThongTinDatLich(request, response);
	  }
	
	private void addThongTinDatLichVsGioHang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // Lấy dữ liệu từ form
	      String tenKH = request.getParameter("tenKH");
	      String soDT = request.getParameter("soDT");
	      String diaChi = request.getParameter("diaChi");      
	      String thoiGian = request.getParameter("thoiGian");
	      String ngayHen = request.getParameter("ngayHen");
	      
	      String  maChiNhanhString = request.getParameter("maChiNhanh");
	      int maChiNhanh = Integer.parseInt(maChiNhanhString);
	      
	      String maPinString = request.getParameter("maPin");
	      int maPin = Integer.parseInt(maPinString);
	      
	      String  soLuongPinXLString = request.getParameter("soLuongPinXuLy");
	      int soLuongPinXL = Integer.parseInt(soLuongPinXLString);
	      
	      String moTa = request.getParameter("moTa");
	      
	      // Tạo đối tượng ThongTinDatLich và gọi hàm addThongTinDatLich
	      ThongTinDatLich thongTinDatLich = new ThongTinDatLich(tenKH, soDT, diaChi, thoiGian, ngayHen, new ChiNhanh(maChiNhanh), new Pin(maPin), soLuongPinXL, moTa);
	      datLichModel.addThongTinDatLich(thongTinDatLich);
	      dsPinVaTatCaPin(request, response);
	  }
	//DANH SÁCH PIN
		private void dsPinVaTatCaPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		    List<Pin> dsTatCaPin = pinModel.getALLPin();
		    request.setAttribute("PIN", dsTatCaPin);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/view/trangchu.jsp");
		    dispatcher.forward(request, response);
		    
		}
	
	//DANH SÁCH ĐẶT LỊCH
	private void listThongTinDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ThongTinDatLich> listDatLich = datLichModel.getALLDatLich();
		
		 // Tính toán và lưu chi phí tạm tính
	    for (ThongTinDatLich datLich : listDatLich) {
	        int chiPhiTamTinh = pinModel.tinhChiPhiTamTinh(datLich.getMaDatLich());
	        datLich.setChiPhi(chiPhiTamTinh);
	    }
		
		request.setAttribute("LIST_DAT_LICH", listDatLich);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-dat-lich.jsp");
		dispatcher.forward(request, response);
	}

	
	private void chapNhanDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			 String maDatLich = request.getParameter("maDatLich");
			 String maPinString = request.getParameter("maPin");
		      int id = Integer.parseInt(maPinString);
		      
		      String  soLuongPinXLString = request.getParameter("soLuongPinXuLy");
		      int soLuong = Integer.parseInt(soLuongPinXLString);
		      

	        Pin pin = new Pin(id, soLuong);
	        pin.setMaPin(id);
	        pin.setSoLuong(soLuong); 

	        datLichModel.updateThongTinPin(id, soLuong); // Truyền giá trị mới của số lượng
	        datLichModel.deleteThongTinDatLich(maDatLich);
	        listThongTinDatLich(request, response);
		 } catch (NumberFormatException e) {
		        e.printStackTrace();
		        
		    }
	
		}

	
	private void listPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Pin> listPin = pinModel.getALLPin();
		request.setAttribute("LIST_PIN", listPin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-pin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
