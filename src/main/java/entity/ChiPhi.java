package entity;

public class ChiPhi {
	private int maChiPhi;
	private float chiPhi;
	private ThongTinDatLich thongTinDatLich;
	private Pin pin;
	private int maDatLich;
	private int maPin;
	
	
	
	public ChiPhi(int maChiPhi, float chiPhi, int maDatLich, int maPin) {
		super();
		this.maChiPhi = maChiPhi;
		this.chiPhi = chiPhi;
		this.maDatLich = maDatLich;
		this.maPin = maPin;
	}
	public ChiPhi(float chiPhi, int maDatLich, int maPin) {
		super();
		this.chiPhi = chiPhi;
		this.maDatLich = maDatLich;
		this.maPin = maPin;
	}
	public ChiPhi(int maChiPhi, float chiPhi, ThongTinDatLich thongTinDatLich, Pin pin, int maDatLich, int maPin) {
		super();
		this.maChiPhi = maChiPhi;
		this.chiPhi = chiPhi;
		this.thongTinDatLich = thongTinDatLich;
		this.pin = pin;
		this.maDatLich = maDatLich;
		this.maPin = maPin;
	}
	public ChiPhi() {
		super();
	}
	public ChiPhi(float chiPhi) {
		super();
		this.chiPhi = chiPhi;
	}
	
	public int getMaChiPhi() {
		return maChiPhi;
	}
	public void setMaChiPhi(int maChiPhi) {
		this.maChiPhi = maChiPhi;
	}
	public float getChiPhi() {
		return chiPhi;
	}
	public void setChiPhi(float chiPhi) {
		this.chiPhi = chiPhi;
	}

	public ThongTinDatLich getThongTinDatLich() {
		return thongTinDatLich;
	}

	public void setThongTinDatLich(ThongTinDatLich thongTinDatLich) {
		this.thongTinDatLich = thongTinDatLich;
	}
	

	public int getMaDatLich() {
		return maDatLich;
	}


	public void setMaDatLich(int maDatLich) {
		this.maDatLich = maDatLich;
	}


	public Pin getPin() {
		return pin;
	}
	public void setPin(Pin pin) {
		this.pin = pin;
	}
	public int getMaPin() {
		return maPin;
	}
	public void setMaPin(int maPin) {
		this.maPin = maPin;
	}
	@Override
	public String toString() {
		return "ChiPhi [maChiPhi=" + maChiPhi + ", chiPhi=" + chiPhi + ", thongTinDatLich=" + thongTinDatLich + ", pin="
				+ pin + ", maDatLich=" + maDatLich + ", maPin=" + maPin + "]";
	}
	
	
	
}
