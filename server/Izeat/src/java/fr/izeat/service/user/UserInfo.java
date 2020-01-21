package fr.izeat.service.user;

public class UserInfo {
	
	private int weight;
	private int sizeCentimetre;
	private char sex;
	private String surname;
	private String name;
	private String passWord;
	private int id;
	
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getSizeCentimetre() {
		return sizeCentimetre;
	}
	public void setSizeCentimetre(int sizeCentimetre) {
		this.sizeCentimetre = sizeCentimetre;
	}
	public char getSex() {
		return sex;
	}
	
	
	public void setSex(char sex) throws SexException{
		
		try {
			if(sex == 'F' || sex == 'M') {
				this.sex = sex;
			}
			else {
				throw new SexException(sex);
				
			}
		}
		catch(SexException e) {
			e.print();
		}
		
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
