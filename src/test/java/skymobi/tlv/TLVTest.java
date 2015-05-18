package skymobi.tlv;

public class TLVTest {

	/**
	 * @author shaokai.yang
	 * @date 2015-5-5
	 * @param args
	 * @return void
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException {
		Person person = new Person();
		person.setName("杨少凯");
		person.setAge(10);
		person.setAddress("杭州斯凯网络");
		person.setDelete(1);

		byte[] data = TLVUtil.encode(person);

		System.out.println(data.length);

		Person cp = TLVUtil.decode(data, Person.class);

		System.out.println(cp);

		System.exit(0);
	}

}
