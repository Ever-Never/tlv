package skymobi.tlv;

import skymobi.tlv.annotation.TLVAttribute;

public class Man {

	@TLVAttribute(tag = 112)
	private int age;
	@TLVAttribute(tag = 113)
	private Integer delete;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getDelete() {
		return delete;
	}

	public void setDelete(Integer delete) {
		this.delete = delete;
	}
}
