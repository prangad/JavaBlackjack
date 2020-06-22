class DVector {
    private double x;
    private double y;

    public DVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public DVector add(DVector other) {
        return new DVector(x + other.x, y + other.y);
    }

    public DVector sub(DVector other) {
        return new DVector(x - other.x, y - other.y);
    }
    
    public double getX()
    {
    	return this.x;
    }
    
    public double getY()
    {
    	return this.y;
    }
    
    public double getDistanceSquared(DVector other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return dx*dx+dy*dy;
    }
    
    public double getDirection()
    {
    	return Math.toDegrees(Math.atan2(y,x));
    }
    
    public double getMagnitude()
    {
    	return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    
    public void setMagnitude(double magnitude)
    {
    	this.x = (magnitude * Math.cos(Math.toRadians(this.getDirection())));
    	this.y = (magnitude * Math.sin(Math.toRadians(this.getDirection())));
    }
}