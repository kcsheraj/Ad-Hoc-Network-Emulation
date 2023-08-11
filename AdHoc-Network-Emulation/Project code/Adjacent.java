//This class is complete. Don't modify it
/**
 * This class represents the adjacent of a AdjListHead. It includes its weight.
 */
public class Adjacent {
    /**
     * this represents the actual adj NetNode.
     */
    private NetNode neighbor;
    /**
     * this represents the weight of the adj NetNode.
     */
    private double weight; 
    /**
     * This constructor creates Adj object with a given NetNode and a weight.
     * @param neighbor the NetNode you want as the Adj
     * @param weight the weight of the connection
     */
    public Adjacent(NetNode neighbor,double weight){
        this.neighbor=neighbor;
        this.weight=weight;
    }
    /**
     * Setter method that sets the NetNode of this neighbor object.
     * @param neighbor the NetNode you want to use to set.
     */
    public void setNeighbor(NetNode neighbor){
        this.neighbor=neighbor; 
    }
    /**
     * Setter method that sets the weight of the connection.
     * @param weight the weight of the connection.
     */
    public void setWeight(double weight){
        this.weight=weight;
    }
    /**
     * Getter method that return the NetNode of the this Adjcent object.
     * @return return the NetNode of the this Adjcent object
     */
    public NetNode getNeighbor(){
        return neighbor; 
    }
    /**
     * Getter method that returns the weight of the connection.
     * @return a double representing the weight of the connection
     */
    public double getWeight(){
        return weight;
    }  
} 
