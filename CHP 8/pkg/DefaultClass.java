package pkg;

class DefaultClass {
	int noOfApples;
	int noOfPears;
	private int noOfEdenApples;
	protected int noOfEdenTrees;
	
	public String toString() {
		return noOfApples + " , " + noOfPears + " , " + noOfEdenTrees + " , " + noOfEdenApples;  
	}
}