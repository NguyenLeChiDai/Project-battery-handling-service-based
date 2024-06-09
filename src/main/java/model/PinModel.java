package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import entity.ChiNhanh;
import entity.ChiPhi;
import entity.GioHang;
import entity.KhachHang;
import entity.Pin;
import entity.ThongTinDatLich;
import entity.ThuongHieu;


public class PinModel {
	private DataSource dataSource;

	public PinModel(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	//list thông tin pin
	public List<Pin> getALLPin() {
	    List<Pin> dsPin = new ArrayList<Pin>();
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet result = null;

	    try {
	        connection = dataSource.getConnection();
	        String sqlQuery = "SELECT * FROM Pin";
	        statement = connection.prepareStatement(sqlQuery);
	        result = statement.executeQuery();

	        while (result.next()) {
	            int maPin = result.getInt("maPin");
	            String tenPin = result.getString("tenPin");
	            float giaThu = result.getFloat("giaThu");
	            int soLuong = result.getInt("soLuong");
	            String moTa = result.getString("moTa");
	            int thuongHieuId = result.getInt("thuongHieuId");
	            String hinhAnh = result.getString("hinhAnh");

	            Pin pin = new Pin(maPin, tenPin, giaThu, soLuong, moTa, thuongHieuId, hinhAnh);
	            dsPin.add(pin);
	        }
	        return dsPin;
	    } catch (Exception e) {
	        // Xử lý ngoại lệ ở đây
	    } finally {
	        close(connection, statement, result);
	    }
	    return dsPin;
	}
	
	
	
		public void close(Connection myConn, Statement myStmt, ResultSet myRs) {
			try {
				if (myRs != null) {
					myRs.close(); }			
				if (myStmt != null) {
					myStmt.close();	}			
				if (myConn != null) {
					myConn.close();   
					// doesn't really close it ... just puts back in connection pool
				}
			}
			catch (Exception exc) {
				exc.printStackTrace();
			}
		}
		

		//load chi tiết pin
		public void loadChiTiet(Pin pin) {
			Connection connection = null;
			PreparedStatement statement = null;
			try {
				connection = dataSource.getConnection();
				String sqlQuery = "UPDATE Pin " + 
		                 "SET tenPin=?, giaThu=?, moTa=?, hinhAnh=? " +
		                 "WHERE maTT=?";
				statement = connection.prepareStatement(sqlQuery);
				statement.setString(1, pin.getTenPin());
				statement.setFloat(2, pin.getGiaThu());
				statement.setString(3, pin.getMoTa());
				statement.setString(4, pin.getHinhAnh()); 
				statement.setInt(5, pin.getMaPin()); 
				statement.execute();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				close(connection, statement, null);
			}
		}
		
		public Pin getPinByid(int maPin) {
			Statement state = null;
			ResultSet rs;
			Connection con;
			try {
				String sql = "Select * from PIN where maPin = " + maPin;
				con = dataSource.getConnection();
				state = con.createStatement();
				rs = state.executeQuery(sql);
				while (rs.next()) {
					 int maPins = rs.getInt("maPin");
			            String tenPin = rs.getString("tenPin");
			            float giaBan = rs.getFloat("giaThu");
			            int soLuong = rs.getInt("soLuong");
			            String moTa = rs.getString("moTa");
			            int thuongHieuId = rs.getInt("thuongHieuId");
			            String hinhAnh = rs.getString("hinhAnh");

			            Pin pin = new Pin(maPins, tenPin, giaBan, soLuong, moTa, thuongHieuId, hinhAnh);
			            return pin;
			        }
			        
				
			} 
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}
		
		
		//tìm pin theo tên
		public List<Pin> searchPinByName(String inputTenPin) {
			// TODO Auto-generated method stub
			List<Pin> dsPin = new ArrayList<Pin>();
		    Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet result = null;

		    try {
		        connection = dataSource.getConnection();
		        String sqlQuery = "SELECT * FROM Pin WHERE tenPin LIKE ?";
		        statement = connection.prepareStatement(sqlQuery);
		        statement.setString(1, "%" + inputTenPin + "%");
		        result = statement.executeQuery();

		        while (result.next()) {
		        	 int maPin = result.getInt("maPin");
			            String tenPin = result.getString("tenPin");
			            float giaThu = result.getFloat("giaThu");
			            int soLuong = result.getInt("soLuong");
			            String moTa = result.getString("moTa");
			            int thuongHieuId = result.getInt("thuongHieuId");
			            String hinhAnh = result.getString("hinhAnh");

			            Pin pin = new Pin(maPin, tenPin, giaThu, soLuong, moTa, thuongHieuId, hinhAnh);
		            dsPin.add(pin);
		        }
		        return dsPin;
		    } 
		    catch (Exception e) {
		        e.printStackTrace();
		    } 
		    finally {
		        close(connection, statement, result);
		    }
		    return dsPin;
		}
		
	
		
		//thêm thông tin đặt lịch
		public void addThongTinDatLich(ThongTinDatLich datLich) {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    try {
		        conn = dataSource.getConnection();
		        String sqlQuery = "INSERT INTO ThongTinDatLich (tenKH, soDT, diaChi, soLuongPinXuLy, thoiGian, ngayHen, moTa, maPin, maChiNhanh) "
		                         + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		        stmt = conn.prepareStatement(sqlQuery);
		        stmt.setString(1, datLich.getTenKH());
		        stmt.setString(2, datLich.getSoDT()); // Make sure you provide a non-null value for 'soDT'
		        stmt.setString(3, datLich.getDiaChi());
		        stmt.setInt(4, datLich.getSoLuongPinXuLy());
		        stmt.setString(5, datLich.getThoiGian());
		        stmt.setString(6, datLich.getNgayHen());
		        stmt.setString(7, datLich.getMoTa());
		        stmt.setInt(8, datLich.getPin().getMaPin());
		        stmt.setInt(9, datLich.getChiNhanh().getMaChiNhanh());
		        stmt.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        close(conn, stmt, null);
		    }
		}

		//delete thông tin đặt lịch
		public void deleteThongTinDatLich(String id) {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				int maDatLich = Integer.parseInt(id);
				conn = dataSource.getConnection();
				String sql = "DELETE FROM ThongTinDatLich WHERE maDatLich=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, maDatLich);
				stmt.execute();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				close(conn, stmt, null);
			}
		}
		
		//Cập nhật thông tin đặt lịch
		public void updateThongTinDatLich(ThongTinDatLich datLich) {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				conn = dataSource.getConnection();
				String sqlQuery = "UPDATE ThongTinDatLich "
					    + "SET tenKH=?, soDT=?, diaChi=?, thoiGian=?, ngayHen=?, moTa=?, maPin=?, maChiNhanh=?, soLuongPinXuLy=? "
					    + "WHERE maDatLich=?";
				 stmt = conn.prepareStatement(sqlQuery);
				 stmt.setString(1, datLich.getTenKH());
				 stmt.setString(2, datLich.getSoDT()); 
				 stmt.setString(3, datLich.getDiaChi());
				 stmt.setString(4, datLich.getThoiGian());
				 stmt.setString(5, datLich.getNgayHen());
				 stmt.setString(6, datLich.getMoTa());
				 stmt.setInt(7, datLich.getMaPin());
				 stmt.setInt(8, datLich.getMaChiNhanh());
				 stmt.setInt(9, datLich.getSoLuongPinXuLy());
				 stmt.setInt(10, datLich.getMaDatLich());
				stmt.execute();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}	
				
		//UPDATE so luong pin trong kho
		
				@SuppressWarnings("resource")
				public void updateThongTinPin(int maPin, int soLuongPinXuLy) { // Thêm tham số số lượng mới
				    Connection conn = null;
				    PreparedStatement stmt = null;
				    ResultSet rs = null;
				    try {
				        conn = dataSource.getConnection();
				        // Truy vấn để lấy số lượng pin đã xử lý từ bảng ThongTinDatLich
				        String sqlQuerySelect = "SELECT soLuongPinXuLy FROM ThongTinDatLich WHERE maPin=?";
				        stmt = conn.prepareStatement(sqlQuerySelect);
				        stmt.setInt(1, maPin);
				        rs = stmt.executeQuery();
				        soLuongPinXuLy =0;
				        // Lặp qua kết quả trả về (chỉ có một dòng)
				        while (rs.next()) {
				            soLuongPinXuLy = rs.getInt("soLuongPinXuLy");
				        }
				        // Cập nhật số lượng trong bảng Pin bằng cách cộng thêm số lượng mới
				        String sqlQueryUpdate = "UPDATE Pin SET soLuong=soLuong+? WHERE maPin=?"; // Sử dụng phép cộng
				        stmt = conn.prepareStatement(sqlQueryUpdate);
				        stmt.setInt(1, soLuongPinXuLy); // Cộng thêm số lượng mới
				        stmt.setInt(2, maPin);
				        stmt.executeUpdate();
				    } catch (Exception e) {
				        e.printStackTrace();
				    } finally {
				        // Đóng tất cả các tài nguyên
				        try {
				            if (rs != null) rs.close();
				            if (stmt != null) stmt.close();
				            if (conn != null) conn.close();
				        } catch (SQLException e) {
				            e.printStackTrace();
				        }
				    }
				}

				//GET thông tin đặt lịch
				public ThongTinDatLich getDatLich(String maDatLich) {
				    ThongTinDatLich datLich = null;
				    Connection myConn = null;
				    PreparedStatement myStmt = null;
				    ResultSet myRs = null;
				    try {
				        // get connection to database
				        myConn = dataSource.getConnection();
				        // create sql to get selected product
				        String sql = "Select * from ThongTinDatLich where maDatLich = ?";
				        // create prepared statement
				        myStmt = myConn.prepareStatement(sql);
				        myStmt.setString(1, maDatLich);
				        // execute statement
				        myRs = myStmt.executeQuery();
				        // retrieve data from result set row
				        if (myRs.next()) {
				            int maDLich = myRs.getInt("maDatLich");
				            String tenKH = myRs.getString("tenKH");
				            String soDT = myRs.getString("soDT");  
				            String diaChi = myRs.getString("diaChi");
				            String thoiGian = myRs.getString("thoiGian");
				            String ngayHen = myRs.getString("ngayHen");
				            int maPin = myRs.getInt("maPin");
				            int maChiNhanh = myRs.getInt("maChiNhanh");
				            int soLuongPinXuLy = myRs.getInt("soLuongPinXuLy");
				            String moTa = myRs.getString("moTa");
				            
				            
				            // Create ThongTinDatLich object
				            datLich = new ThongTinDatLich(maDLich, tenKH, soDT, diaChi, thoiGian, ngayHen, maChiNhanh, maPin, soLuongPinXuLy, moTa);
				        } else {
				            throw new Exception("Could not find ThongTinDatLich with maDatLich = " + maDatLich);
				        }
				        return datLich;
				    } catch (Exception e) {
				        e.printStackTrace();
				    } finally {
				        // clean up JDBC objects
				        close(myConn, myStmt, myRs);
				    }
				    return null;
				}

				//get Chi Phí
				public ChiPhi getChiPhi(String maChiPhi) {
				    ChiPhi chiPhiTamTinh = null;
				    Connection myConn = null;
				    PreparedStatement myStmt = null;
				    ResultSet myRs = null;
				    try {
				        // get connection to database
				        myConn = dataSource.getConnection();
				        // create sql to get selected product
				        String sql = "Select * from ChiPhi where maChiPhi = ?";
				        // create prepared statement
				        myStmt = myConn.prepareStatement(sql);
				        myStmt.setString(1, maChiPhi);
				        // execute statement
				        myRs = myStmt.executeQuery();
				        // retrieve data from result set row
				        if (myRs.next()) {
				            int maChiPhis = myRs.getInt("maChiPhi");
				            float chiPhi = myRs.getFloat("chiPhi");
				            int maDatLich = myRs.getInt("maDatLich");
				            int maPin = myRs.getInt("maPin");
				            // Create ThongTinDatLich object
				            chiPhiTamTinh = new ChiPhi(maChiPhis, chiPhi, maDatLich, maPin);
				        } else {
				            throw new Exception("Could not find ChiPhi with maChiPhi = " + maChiPhi);
				        }
				        return chiPhiTamTinh;
				    } catch (Exception e) {
				        e.printStackTrace();
				    } finally {
				        // clean up JDBC objects
				        close(myConn, myStmt, myRs);
				    }
				    return null;
				}

				//THÊM CHI PHÍ:
				public void themChiPhi(int maDatLich, int maPin, int chiPhi) {
				    Connection connection = null;
				    PreparedStatement statement = null;

				    try {
				        connection = dataSource.getConnection();
				        String sqlQuery = "INSERT INTO ChiPhi (ChiPhi, maDatLich, maPin) VALUES (?, ?, ?)";
				        statement = connection.prepareStatement(sqlQuery);
				        statement.setInt(1, chiPhi);
				        statement.setInt(2, maDatLich);
				        statement.setInt(3, maPin);
				        statement.executeUpdate();
				    } catch (SQLException e) {
				        e.printStackTrace();
				    } finally {
				        close(connection, statement, null);
				    }
				}
		
		//THUONG HIEU
		public List<ThuongHieu> getAllBrands() {
			List<ThuongHieu> listThuongHieu = new ArrayList<ThuongHieu>();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = dataSource.getConnection();
				String sql = "SELECT * FROM ThuongHieu";
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					int thuongHieuId = rs.getInt("thuongHieuId");
					String name = rs.getString("name");
					ThuongHieu thuongHieu = new ThuongHieu(thuongHieuId, name);
					listThuongHieu.add(thuongHieu);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				close(conn, stmt, rs);
			}
			return listThuongHieu;
		}
		
		//Tính Toán Chi Phí tạm tính ở thông tin đặt lịch:
		public int tinhChiPhiTamTinh(int maDatLich) {
		    int chiPhiTamTinh = 0;
		    Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet result = null;

		    try {
		        connection = dataSource.getConnection();
		        String sqlQuery = "SELECT p.giaThu, t.soLuongPinXuLy " +
		                          "FROM ThongTinDatLich t " +
		                          "INNER JOIN Pin p ON t.maPin = p.maPin " +
		                          "WHERE t.maDatLich = ?";
		        statement = connection.prepareStatement(sqlQuery);
		        statement.setInt(1, maDatLich);
		        result = statement.executeQuery();

		        if (result.next()) {
		            int giaThu = result.getInt("giaThu");
		            int soLuongPinXuLy = result.getInt("soLuongPinXuLy");
		            chiPhiTamTinh = giaThu * soLuongPinXuLy;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        close(connection, statement, result);
		    }

		    return chiPhiTamTinh;
		}
		
		//Chi Phí
		public int tinhChiPhi(int maPin) {
		    int chiPhi = 0;
		    Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet result = null;

		    try {
		        connection = dataSource.getConnection();
		        String sqlQuery = "SELECT p.giaThu, t.soLuong " +
		                          "FROM Pin t " +
		                          "INNER JOIN Pin p ON t.maPin = p.maPin " +
		                          "WHERE t.maPin = ?";
		        statement = connection.prepareStatement(sqlQuery);
		        statement.setInt(1, maPin);
		        result = statement.executeQuery();

		        if (result.next()) {
		            int giaThu = result.getInt("giaThu");
		            int soLuong = result.getInt("soLuong");
		            chiPhi = giaThu * soLuong;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        close(connection, statement, result);
		    }

		    return chiPhi;
		}

		//add pin
		public void addThongTinPin(Pin pin) {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    try {
		        conn = dataSource.getConnection();
		        String sqlQuery = "INSERT INTO Pin (tenPin, giaThu, soLuong, moTa, thuongHieuId, hinhAnh) "
		                         + "VALUES (?, ?, ?, ?, ?, ?)";
		        stmt = conn.prepareStatement(sqlQuery);
		        //stmt.setInt(1, pin.getMaPin());
		        stmt.setString(1, pin.getTenPin()); // Make sure you provide a non-null value for 'soDT'
		        stmt.setFloat( 2, pin.getGiaThu());
		        stmt.setInt(   3, pin.getSoLuong());
		        stmt.setString(4, pin.getMoTa());
		        stmt.setInt(   5, pin.getThuongHieuId());
		        stmt.setString(6, pin.getHinhAnh());
		        
		        stmt.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        close(conn, stmt, null);
		    }
		}
		
		// sửa pin
		public void updatePin(Pin p) {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				conn = dataSource.getConnection();
				String sqlQuery = "UPDATE Pin "
						+ "SET tenPin=?, giaThu=?, soLuong=?, moTa=?, thuongHieuId=?, hinhAnh=? "
						+ "WHERE maPin=?";
				 stmt = conn.prepareStatement(sqlQuery);
			        stmt.setString(1, p.getTenPin());
			        stmt.setFloat(2, p.getGiaThu()); // Make sure you provide a non-null value for 'soDT'
			        stmt.setInt(3, p.getSoLuong());
			        stmt.setString(4, p.getMoTa());
			        stmt.setInt(5, p.getThuongHieuId());
			        stmt.setString(6, p.getHinhAnh());
			        stmt.setInt(7, p.getMaPin());
				stmt.execute();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		//xoa pin
		public void deletePin(String id) {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				int maPin = Integer.parseInt(id);
				conn = dataSource.getConnection();
				String sql = "DELETE FROM Pin WHERE maPin=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, maPin);
				stmt.execute();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				close(conn, stmt, null);
			}
		}

		


				
	
}
