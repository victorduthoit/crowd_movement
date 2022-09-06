
public class WayOut {
	
	/**Cette classe a été introduite pour permettre le choix de la sortie la plus proche. Un objet de type WayOut
	 * contient la porte qui est visée, la distance à cette porte et le vecteur qui y amène.
	 */
	
	// Attributes :
	private int exitId;
	private double dist;
	private Vector v;
	
	// Constructors :
	public WayOut(int exitId, double dist, Vector v) {
		super();
		this.exitId = exitId;
		this.dist = dist;
		this.v = v;
	}


	// Methods :
	
			// Getters & Setters :
	public int getExitId() {
		return exitId;
	}
	public void setExitId(int exitId) {
		this.exitId = exitId;
	}
	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}
	public Vector getV() {
		return v;
	}
	public void setV(Vector v) {
		this.v = v;
	}

			//Others :
	public void shortestWayOut(WayOut wo) {
		double woDist = wo.getDist();
		if (this.dist > woDist) {
			exitId = wo.getExitId();
			dist = woDist;
			v = wo.getV();
		}
	}
	public void print() {
		System.out.println(dist);
		v.print();
	}


}
