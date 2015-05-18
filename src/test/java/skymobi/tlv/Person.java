package skymobi.tlv;

import skymobi.tlv.annotation.TLVAttribute;

public class Person extends Man {

	@TLVAttribute(tag = 110, description = "名称")
	private String name;
	@TLVAttribute(tag = 111, description = "名称")
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return getName() + getAddress() + getAge()+getDelete();
	}

}
