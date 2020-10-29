package gcLab17;

public class Country {
	
	private String name;
	private long population;
	private String continent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPopulation() {
		return population;
	}
	public void setPopulation(long population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", population=" + population + ", continent=" + continent + "]";
	}
	public Country(String name, long population) {
		super();
		this.name = name;
		this.population = population;
		this.continent = "unknown";
	}
	
	public Country(String name, long population, String continent) {
		super();
		this.name = name;
		this.population = population;
		this.continent = continent;
	}
	
	public Country() {
		
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}

	
}
