package assignment3;

public enum Frequency {

	QUARTERLY(4),
	HALF_YEARLY(2),
	ANNUALY(1);
	
	private int value;
	
	 Frequency (int value) {
		this.value=value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	 
	
	public static Frequency findByValue(int value){
	    for(Frequency v : values()){
	        if( v.getValue()== value){
	            return v;
	        }
	    }
	    return null;
	}
	 
}
