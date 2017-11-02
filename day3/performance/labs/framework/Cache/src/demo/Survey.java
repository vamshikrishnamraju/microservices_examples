package demo;
public class Survey {
    private Long id = new Long("1234");
    private String name;
    private String last;
    
    public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}