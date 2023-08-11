//This class is complete. Don't modify it
import java.util.*;
/**
 * This method represents a head node in the nodesList of NetGraph.
 * @author Sheraj KC
 */
public class AdjacencyListHead {
    /**
     * node represents the AdjListHead's node value.
     */
    private NetNode node;
    /**
     * this represents the nodes adjacent networkNodes.
     */
    private LinkedList<Adjacent> adjacencyList;
    /**
     * This is the one pramater constructor that sets the NetNode and the adjacenyList to a new linkList.
     * @param node the Node you want to add as the head to represent the AdjListHead
     */
    public AdjacencyListHead(NetNode node){
        this.node=node;
        this.adjacencyList=new LinkedList<Adjacent>();
    }
    /**
     * This is the two pramater constructor that sets the NetNode and the adjacenyList to the one passed in.
     * @param node the Node you want to add as the head to represent the AdjListHead
     * @param adjacencyList the list you want that represents this nodes adjcents.
     */
    public AdjacencyListHead(NetNode node,LinkedList<Adjacent> adjacencyList){
        this.node=node;
        this.adjacencyList=adjacencyList;
    }
    /**
     * Setter method that sets the NetNode of the class.
     * @param node the node you want to use to set the NetNode.
     */
    public void setNetNode(NetNode node){
        this.node=node;
    }
    /**
     * Setter method that sets the AdjList of the class.
     * @param adjacencyList the LinkList of adjcents you want to use to set the adjecents of this node.
     */
    public void setAdjacencyList(LinkedList<Adjacent> adjacencyList){
        this.adjacencyList=adjacencyList;
    }
    /**
     * Getter method that return the NetNode of the AdjListHead object.
     * @return return the NetNode of the AdjListHead object
     */
    public NetNode getNetNode(){
        return node;
    }
    /**
     * Getter method that returns the AdjList of the object.
     * @return return the AdjcentyList of the AdjListHead object.
     */
    public LinkedList<Adjacent> getAdjacencyList(){
        return adjacencyList;
    }
    
    
}
