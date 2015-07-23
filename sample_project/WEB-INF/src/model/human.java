package model;

public class human {
	String id;
	String name;
	String seibetsu;
	String birthday;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSeibetsu() {
		return seibetsu;
	}
	public void setSeibetsu(String seibetsu) {
		this.seibetsu = seibetsu;
	}
	
	public String getSeibetsu_str(){
		String[] sei = {"param.man","param.woman"};
		
		return sei[Integer.parseInt(getSeibetsu())-1];
	}
		
	

}
