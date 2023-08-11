//This class is complete. Don't modify it
/**
 * This class represents the NetNode.
 * @author Sheraj KC
 */
public class NetNode {
    /**
     * this represents the unique id of the node.
     */
    private int id;
    /**
     * this represents the name of the node.
     */
    private String name;
    /**
     * this represents the x cordinate in the graph of the node.
     */
    private double coordinateX;
    /**
     * this represents the y cordinate in the graph of the node.
     */
    private double coordinateY;
    
    /**
     * This constructor creates a netNode object with the input.
     * @param id the unique id of the node
     * @param name the name of the node
     * @param coordinateX the x cordinate in the graph of the node
     * @param coordinateY y cordinate in the graph of the node
     */
    public NetNode(int id,String name,double coordinateX,double coordinateY){
        this.id=id;
        this.name=name;
        this.coordinateX=coordinateX;
        this.coordinateY=coordinateY;  
    }
    /**
     * Setter method that sets the id of the NetNode.
     * @param id the id input
     */
    public void setId(int id){
        this.id=id;
    }
    /**
     * Setter method that sets the name of the NetNode.
     * @param name the string name you want to set
     */
    public void setName(String name){
        this.name=name;
    }
    /**
     * Setter method that sets the x codinate of the NetNode.
     * @param coordinateX the double value of the cordinate.
     */
    public void setX_coordinate(double coordinateX){
        this.coordinateX=coordinateX;
    }
    /**
     * Setter method that sets the y codinate of the NetNode.
     * @param coordinateY the doubble value of the cordinate.
     */
    public void setY_coordinate(double coordinateY){
        this.coordinateY=coordinateY;
    }
    /**
     * Getter method that returns the id.
     * @return the id of the node
     */
    public int getId(){
        return id;
    }
    /**
     * Getter method that return the name.
     * @return the name value of the NetNode
     */
    public String getName(){
        return name;
    }
    /**
     * Getter method that returns the x coordinate of the Netnode.
     * @return the double x coordinate
     */
    public double getX_coordinate(){
        return coordinateX;
    }
    /**
     * Getter method that return the y coordinate of the netnode.
     * @return the double y coordinate
     */
    public double getY_coordinate(){
        return coordinateY;
    }
}
