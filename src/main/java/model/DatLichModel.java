package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import entity.ChiNhanh;
import entity.Pin;
import entity.ThongTinDatLich;

public class DatLichModel {
	private DataSource dataSource;

	public DatLichModel(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	//List Thông tin đặt lịch
		public List<ThongTinDatLich> getALLDatLich() {
		    List<ThongTinDatLich> dsDatLich = new ArrayList<>();
		    Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet result = null;

		    try {
		        connection = dataSource.getConnection();
		        String sqlQuery = "SELECT * FROM ThongTinDatLich";
		        statement = connection.prepareStatement(sqlQuery);
		        result = statement.executeQuery();

		        while (result.next()) {
		            int maDatLich = result.getInt("maDatLich");
		            String tenKH = result.getString("tenKH");
		            String soDT = result.getString("soDT");
		            String diaChi = result.getString("diaChi");
		            String thoiGian = result.getString("thoiGian");
		            String ngayHen = result.getString("ngayHen");
		            String moTa = result.getString("moTa"); 
		            int soLuongPinXuLy = result.getInt("soLuongPinXuLy");
		            int maPin = result.getInt("maPin");
		            int maChiNhanh = result.getInt("maChiNhanh");

		            ThongTinDatLich thongTinDatLich = new ThongTinDatLich(maDatLich, tenKH, soDT, diaChi, thoiGian, ngayHen, new ChiNhanh(maChiNhanh), new Pin(maPin), soLuongPinXuLy, moTa);
		            dsDatLich.add(thongTinDatLich);
		        }
		    } catch (Exception e) {
		        // Log or handle exception
		        e.printStackTrace();
		    } finally {
		        close(connection, statement, result);
		    }
		    return dsDatLich;
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
		
		//Tìm lịch đặt theo số điện thoại
		public List<ThongTinDatLich> searchDatLichBySoDT(String inputSoDT) {
			// TODO Auto-generated method stub
			List<ThongTinDatLich> dsDatLich = new ArrayList<ThongTinDatLich>();
		    Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet result = null;

		    try {
		        connection = dataSource.getConnection();
		        String sqlQuery = "SELECT * FROM ThongTinDatLich WHERE soDT LIKE ?";
		        statement = connection.prepareStatement(sqlQuery);
		        statement.setString(1, "%" + inputSoDT + "%");
		        result = statement.executeQuery();

		        while (result.next()) {
		        	 int maDatLich = result.getInt("maDatLich");
			            String tenKH = result.getString("tenKH");
			            String soDT = result.getString("soDT");
			            String diaChi = result.getString("diaChi");
			            String thoiGian = result.getString("thoiGian");
			            String ngayHen = result.getString("ngayHen");
			            String moTa = result.getString("moTa"); 
			            int soLuongPinXuLy = result.getInt("soLuongPinXuLy");
			            int maPin = result.getInt("maPin");
			            int maChiNhanh = result.getInt("maChiNhanh");

			            ThongTinDatLich thongTinDatLich = new ThongTinDatLich(maDatLich, tenKH, soDT, diaChi, thoiGian, ngayHen, new ChiNhanh(maChiNhanh), new Pin(maPin), soLuongPinXuLy, moTa);
			            dsDatLich.add(thongTinDatLich);
		        }
		        return dsDatLich;
		    } 
		    catch (Exception e) {
		        e.printStackTrace();
		    } 
		    finally {
		        close(connection, statement, result);
		    }
		    return dsDatLich;
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
}

