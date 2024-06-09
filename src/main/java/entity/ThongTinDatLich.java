	package entity;
	
	import java.sql.Date;
	
	public class ThongTinDatLich {
		private int maDatLich;
		private String tenKH;
		private String soDT;
		private String diaChi;
		private String thoiGian;
		private String ngayHen;
		private ChiNhanh chiNhanh;
		private int maChiNhanh;
		private int maPin;
		private int soLuongPinXuLy;
		private float chiPhi;
		
		private Pin pin;
		private String moTa;
		public ThongTinDatLich(int maDatLich, String tenKH, String soDT, String diaChi, String thoiGian, String ngayHen,
				ChiNhanh chiNhanh, Pin pin, int soLuongPinXuLy, String moTa) {
			super();
			this.maDatLich = maDatLich;
			this.tenKH = tenKH;
			this.soDT = soDT;
			this.diaChi = diaChi;
			this.thoiGian = thoiGian;
			this.ngayHen = ngayHen;
			this.chiNhanh = chiNhanh;
			this.pin = pin;
			this.soLuongPinXuLy = soLuongPinXuLy;
			this.moTa = moTa;
		}
		public ThongTinDatLich(String tenKH, String thoiGian, String ngayHen, ChiNhanh chiNhanh, Pin pin, String moTa) {
			super();
			this.tenKH = tenKH;
			this.thoiGian = thoiGian;
			this.ngayHen = ngayHen;
			this.chiNhanh = chiNhanh;
			this.pin = pin;
			this.moTa = moTa;
		}
		public ThongTinDatLich(String tenKH, String soDT, String diaChi, String thoiGian, String ngayHen,
				ChiNhanh chiNhanh, Pin pin,int soLuongPinXuLy, String moTa) {
			super();
			this.tenKH = tenKH;
			this.soDT = soDT;
			this.diaChi = diaChi;
			this.thoiGian = thoiGian;
			this.ngayHen = ngayHen;
			this.chiNhanh = chiNhanh;
			this.pin = pin;
			this.soLuongPinXuLy = soLuongPinXuLy;
			this.moTa = moTa;
		}
		
		
		public ThongTinDatLich(int maDatLich, String tenKH, String soDT, String diaChi, String thoiGian, String ngayHen,
				int maChiNhanh, int maPin,int soLuongPinXuLy, String moTa) {
			super();
			this.maDatLich = maDatLich;
			this.tenKH = tenKH;
			this.soDT = soDT;
			this.diaChi = diaChi;
			this.thoiGian = thoiGian;
			this.ngayHen = ngayHen;
			this.maChiNhanh = maChiNhanh;
			this.maPin = maPin;
			this.soLuongPinXuLy = soLuongPinXuLy;
			this.moTa = moTa;
		}
		
		
		public ThongTinDatLich(String tenKH, String soDT, String diaChi, String thoiGian, String ngayHen,
				int maChiNhanh, int maPin, int soLuongPinXuLy, String moTa) {
			super();
			this.tenKH = tenKH;
			this.soDT = soDT;
			this.diaChi = diaChi;
			this.thoiGian = thoiGian;
			this.ngayHen = ngayHen;
			this.maChiNhanh = maChiNhanh;
			this.maPin = maPin;
			this.soLuongPinXuLy = soLuongPinXuLy;
			this.moTa = moTa;
		}
		
		
		public ThongTinDatLich(int maDatLich, String tenKH, String soDT, String diaChi, String thoiGian, String ngayHen,
				ChiNhanh chiNhanh, int maChiNhanh, int maPin, int soLuongPinXuLy, float chiPhi, Pin pin, String moTa) {
			super();
			this.maDatLich = maDatLich;
			this.tenKH = tenKH;
			this.soDT = soDT;
			this.diaChi = diaChi;
			this.thoiGian = thoiGian;
			this.ngayHen = ngayHen;
			this.chiNhanh = chiNhanh;
			this.maChiNhanh = maChiNhanh;
			this.maPin = maPin;
			this.soLuongPinXuLy = soLuongPinXuLy;
			this.chiPhi = chiPhi;
			this.pin = pin;
			this.moTa = moTa;
		}
		
		public ThongTinDatLich(int maDatLich, String tenKH, String soDT, String diaChi, String thoiGian, String ngayHen,
				int maChiNhanh, int maPin, int soLuongPinXuLy, float chiPhi, String moTa) {
			super();
			this.maDatLich = maDatLich;
			this.tenKH = tenKH;
			this.soDT = soDT;
			this.diaChi = diaChi;
			this.thoiGian = thoiGian;
			this.ngayHen = ngayHen;
			this.maChiNhanh = maChiNhanh;
			this.maPin = maPin;
			this.soLuongPinXuLy = soLuongPinXuLy;
			this.chiPhi = chiPhi;
			this.moTa = moTa;
		}
		
		public float getChiPhi() {
			return chiPhi;
		}
		public void setChiPhi(float chiPhi) {
			this.chiPhi = chiPhi;
		}
		public int getMaDatLich() {
			return maDatLich;
		}
		public void setMaDatLich(int maDatLich) {
			this.maDatLich = maDatLich;
		}
		public String getTenKH() {
			return tenKH;
		}
		public void setTenKH(String tenKH) {
			this.tenKH = tenKH;
		}
		public String getSoDT() {
			return soDT;
		}
		public void setSoDT(String soDT) {
			this.soDT = soDT;
		}
		public String getDiaChi() {
			return diaChi;
		}
		public void setDiaChi(String diaChi) {
			this.diaChi = diaChi;
		}
		public String getThoiGian() {
			return thoiGian;
		}
		public void setThoiGian(String thoiGian) {
			this.thoiGian = thoiGian;
		}
		public String getNgayHen() {
			return ngayHen;
		}
		public void setNgayHen(String ngayHen) {
			this.ngayHen = ngayHen;
		}
		public ChiNhanh getChiNhanh() {
			return chiNhanh;
		}
		public void setChiNhanh(ChiNhanh chiNhanh) {
			this.chiNhanh = chiNhanh;
		}
		public Pin getPin() {
			return pin;
		}
		public void setPin(Pin pin) {
			this.pin = pin;
		}
		public String getMoTa() {
			return moTa;
		}
		public void setMoTa(String moTa) {
			this.moTa = moTa;
		}
		
		public int getMaChiNhanh() {
			return maChiNhanh;
		}
		public void setMaChiNhanh(int maChiNhanh) {
			this.maChiNhanh = maChiNhanh;
		}
		public int getMaPin() {
			return maPin;
		}
		public void setMaPin(int maPin) {
			this.maPin = maPin;
		}
		
		public int getSoLuongPinXuLy() {
			return soLuongPinXuLy;
		}
		public void setSoLuongPinXuLy(int soLuongPinXuLy) {
			this.soLuongPinXuLy = soLuongPinXuLy;
		}
		@Override
		public String toString() {
			return "ThongTinDatLich [maDatLich=" + maDatLich + ", tenKH=" + tenKH + ", soDT=" + soDT + ", diaChi="
					+ diaChi + ", thoiGian=" + thoiGian + ", ngayHen=" + ngayHen + ", chiNhanh=" + chiNhanh
					+ ", maChiNhanh=" + maChiNhanh + ", maPin=" + maPin + ", soLuongPinXuLy=" + soLuongPinXuLy
					+ ", chiPhi=" + chiPhi + ", pin=" + pin + ", moTa=" + moTa + "]";
		}
		
		
		
		
	}
