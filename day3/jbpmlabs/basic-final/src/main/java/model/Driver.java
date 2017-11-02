package model;


public class Driver  implements java.io.Serializable {

static final long serialVersionUID = 1L;
    

    private java.lang.Integer age;
    private java.lang.Integer creditScore;
    private java.lang.String dlNumber;
    private java.lang.String driverName;
    private java.lang.Integer numberOfAccidents;
    private java.lang.Integer numberOfTickets;
    private java.lang.String ssn;

    public Driver() {
    }

    public Driver(java.lang.String driverName, java.lang.Integer age, java.lang.String ssn, java.lang.String dlNumber, java.lang.Integer numberOfAccidents, java.lang.Integer numberOfTickets, java.lang.Integer creditScore) {
        this.driverName = driverName;
        this.age = age;
        this.ssn = ssn;
        this.dlNumber = dlNumber;
        this.numberOfAccidents = numberOfAccidents;
        this.numberOfTickets = numberOfTickets;
        this.creditScore = creditScore;
    }


    
    public java.lang.Integer getAge() {
        return this.age;
    }

    public void setAge(  java.lang.Integer age ) {
        this.age = age;
    }
    
    public java.lang.Integer getCreditScore() {
        return this.creditScore;
    }

    public void setCreditScore(  java.lang.Integer creditScore ) {
        this.creditScore = creditScore;
    }
    
    public java.lang.String getDlNumber() {
        return this.dlNumber;
    }

    public void setDlNumber(  java.lang.String dlNumber ) {
        this.dlNumber = dlNumber;
    }
    
    public java.lang.String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(  java.lang.String driverName ) {
        this.driverName = driverName;
    }
    
    public java.lang.Integer getNumberOfAccidents() {
        return this.numberOfAccidents;
    }

    public void setNumberOfAccidents(  java.lang.Integer numberOfAccidents ) {
        this.numberOfAccidents = numberOfAccidents;
    }
    
    public java.lang.Integer getNumberOfTickets() {
        return this.numberOfTickets;
    }

    public void setNumberOfTickets(  java.lang.Integer numberOfTickets ) {
        this.numberOfTickets = numberOfTickets;
    }
    
    public java.lang.String getSsn() {
        return this.ssn;
    }

    public void setSsn(  java.lang.String ssn ) {
        this.ssn = ssn;
    }
    
    @Override
    public String toString() {
    	StringBuffer sb = new StringBuffer(200);
    	sb.append("Driver => name=" + this.driverName);
    	sb.append(",dl#=" + this.dlNumber);
    	sb.append(",age=" + this.age);
    	sb.append(",ssn=" + this.ssn);
    	sb.append(",#acc=" + this.numberOfAccidents);
    	sb.append(",#tkt=" + this.numberOfTickets);
    	sb.append(",credit=" + this.creditScore);
    	sb.append("\n");
    	
    	return sb.toString();
    }
}