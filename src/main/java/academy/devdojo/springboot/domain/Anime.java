package academy.devdojo.springboot.domain;

public class Anime {
	
	//Attributes
	private String name;
	
	//Constructors
	public Anime() {
	
	}
	public Anime(String name) {
		super();
		this.setName(name);
	}

	//Getter and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
