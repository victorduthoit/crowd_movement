public class Vector {
	
	/**On aurait pu choisir la classe déjà implémentée Vector2d, prochaine fois, c'est ce que je ferai.
	 */
	
	// Attributes :
	private double vx;
	private double vy;

	
	// Constructors :
	public Vector () {
		this.vx=0;
		this.vy=0;
	}
	public Vector(double vx, double vy) {
		super();
		this.vx = vx;
		this.vy = vy;
	}
	public Vector(int vx, int vy) {
		this.vx = (double) vx;
		this.vy = (double) vy;
	}
	
	// Methods :
		// Getters & Setters :
		public double getVx() {
				return vx;
			}
		public void setVx(double vx) {
				this.vx = vx;
			}
		public double getVy() {
				return vy;
			}
		public void setVy(double vy) {
				this.vy = vy;
			}
			
		//Others :
		public void add(Vector v) {
				vx+=v.getVx();
				vy+=v.getVy();
			}
		public void substract(Vector v) {
				vx-=v.getVx();
				vy-=v.getVy();
			}
		public void multi(int lambda) {
				vx*=lambda;
				vy*=lambda;
			}
		public Vector sum(Vector v1, Vector v2) {
				double vx1 = v1.getVx();
				double vx2 = v2.getVx();
				double vy1 = v1.getVy();
				double vy2 = v2.getVy();
				Vector v = new Vector(vx1+vx2,vy1+vy2);
				return v;
			}
		public double norm() {
				double n2 = vx*vx+vy*vy;
				double n = (double) Math.sqrt(n2);
				return n;
			}
		public void multi(double lambda) {
				vx= lambda*vx;
				vy= lambda*vy;
			}
		public void print() {
			System.out.print(vx);
			System.out.print(",");
			System.out.println(vy);
		}

	  	public static void main(String[] args) {
	  		int x = 2;
	  		int y = 3;
	  		Vector v = new Vector(x,y);
	  		v.print();
	  	}
}

