
import java.util.*;
/**
 * This class represents a graph datastructure that represents nodes in a network.
 * @author Sheraj KC
 */
public class NetGraph {
    /**
     * this represents the list of nodes in the graph.
     */
    private ArrayList<AdjacencyListHead> nodesList;
    /**
     * Constructor sets the nodesList arrayList.
     * @param nodesList the arrayList of AdjHeads that represent the nodes.
     */
    public NetGraph(ArrayList<AdjacencyListHead> nodesList){
        this.nodesList=nodesList;
        //This method is complete
    }
    /**
     * Getter method return the nodesList.
     * @return nodesList which is an arrayList of AdjHeads.
     */
    public ArrayList<AdjacencyListHead> getNodesList(){
        return nodesList;
        //This method is complete
    }
    /**
     * Getter method returns the size of the nodesList.
     * @return returns the size of the nodesList
     */
    public int getNumNodes(){
        //Implement this method
        //Should return the number of nodes in the Graph
        return nodesList.size();
    }
    /**
     * Getter method returns the number of edges in the entire undirected graph.
     * @return returns the number of edges in the entire undirected graph.
     */
    public int getNumLinks(){
        //Implement this method
        //returns the number of edges in the graph. Remember this is an undirected graph
        int totalEdgesDirected = 0;
        for(AdjacencyListHead x: nodesList){
            totalEdgesDirected += x.getAdjacencyList().size();
        }
        return totalEdgesDirected/2;
    }
    /**
     * This method creates a new NetNode with the information and adds it to the graph.
     * @param id the unique id of the node
     * @param name the name of the node
     * @param coordinateX the x coordinate of the node in the grid
     * @param coordinateY the y coordinate of the node in the grid
     */
    public void insertNetNode(int id,String name,double coordinateX,double coordinateY)
	{   
        //Implement this method
        //Adds a new node to the graph. The node is represented by the NetNode class having id, name, x_coordinate, and y_coordinatte instance variables
        //You should check if the nodes already exists in the graph. If this is the case throw an IllegalArgumentException

        //check if nodes already exists in graph
        for(AdjacencyListHead x: nodesList){//USE ID
            int nodeId = x.getNetNode().getId();
            if(nodeId==id){
                throw new IllegalArgumentException();
            }
        }

        NetNode newNetNode = new NetNode(id, name, coordinateX, coordinateY);//create NetNode
        AdjacencyListHead newAdjacencyListHead = new AdjacencyListHead(newNetNode);//add NetNode to new AdjListHead
        nodesList.add(newAdjacencyListHead);//Add new AdjListHead to graph
	}
    /**
     * This method creates a link (undirected) between node1 and node2 with a weight.
     * @param node1 the first node
     * @param node2 the second node
     * @param weight the weight of the edge
     */
    public void addLink(NetNode node1, NetNode node2, double weight)
	{   
        //Implement this method
        //adds a link in the graph between two  nodes node1 and node2
        //You should check if the nodes exist in the graph and that they are not null or else you should raise an IllegalArgumentException

        if(node1==null || node2==null) throw new IllegalArgumentException();//check not null paramaters
        
        //check if both nodes exist in graph: throw execption if not
        int node1Index = getNodeIndex(node1);
        int node2Index = getNodeIndex(node2);

        //Gather heads
        AdjacencyListHead node1Head = nodesList.get(node1Index);
        AdjacencyListHead node2Head = nodesList.get(node2Index);

        //Dont add link if link already exists in node 1 or node 2
        boolean linkExists = false;
        for(Adjacent x: node1Head.getAdjacencyList()){//go through node1's links and check if node2 is in there
            if(x.getNeighbor().getId()==node2.getId()){
                linkExists = true;
            }
        }

        if(linkExists==false){//if link does not yet exist: create undirected link with node1 & node2
            //Add 
            //create adjecent for node1 & node2
            Adjacent node1NewAdj = new Adjacent(node2, weight);
            Adjacent node2NewAdj = new Adjacent(node1, weight);
            //Add Adjcents to node1 & node 2
            node1Head.getAdjacencyList().add(node1NewAdj);
            if(node1.getId()!=node2.getId()){//dont add two adjs to same node if node1=node2
                node2Head.getAdjacencyList().add(node2NewAdj);
            }
        }
    }
    /**
     * This method deletes a node from the graph and removes its links.
     * @param node the node you want to delete
     */
    public void deleteNetNode(NetNode node){
        //Implement this method
        //deletes a particular node from the NetGraph. Remember to delete all edges containing it from the different adjacency lists
        //You should check if node exists in the graph and that it is not null or else you should raise an IllegalArgumentException

        
        int indexToDeleteFrom = getNodeIndex(node);//Throws exception: gets index in nodesList of a particular NetNode node

        //you should delete any link in the adjacency lists 
        //of the rest of the nodes containing the deleted node.
        for(AdjacencyListHead x: nodesList){//for each head
            //check
            removeLink(x.getNetNode(), node);
        }

        //Delete AdjacenyListHead of node from graph
        //delte node from list after removing links elsewhere
        nodesList.remove(indexToDeleteFrom);
    }
    /**
     * This method removes the links (undirected) between two nodes.
     * @param node1 the first node
     * @param node2 the second node
     */
    public void removeLink(NetNode node1, NetNode node2)
	{   
        //Implement this method
        //deletes a link between two  nodes in the NetGraph. 
        //You should check if the nodes exist in the graph and that they are not null or else you should raise an IllegalArgumentException
    
        //check if not null and exist: will throw exception
        getNodeIndex(node1);
        getNodeIndex(node2);

        //remove node2 edge from node1 adjList
        LinkedList<Adjacent> node1AdjList = getAdjacents(node1);
        int traverser1 = 0;
        for(Adjacent x: node1AdjList){
            if(x.getNeighbor().getId() == node2.getId()){
                node1AdjList.remove(traverser1);
                break;//once found and removed
            }
            traverser1++;
        }

        //remove node1 edge from node2 adjList
        LinkedList<Adjacent> node2AdjList = getAdjacents(node2);
        int traverser2 = 0;
        for(Adjacent x: node2AdjList){
            if(x.getNeighbor().getId() == node1.getId()){
                node2AdjList.remove(traverser2);
                break;//once found and removed
            }
            traverser2++;
        }
            
    }
    /**
     * This method gives a LinkedList containing the Adjacent Objects representing the neighbors of a particular node and the weights of the link.
     * @param node the node you want to get the adjacents from
     * @return a linkList contaning the Adj objects
     */
    public LinkedList<Adjacent> getAdjacents(NetNode node){
        //Implement this method
        //returns a LinkedList containing the Adjacent Objects representing the neighbors of a particular node and the weights of the link

        int indexToGetAdjHeadFrom = getNodeIndex(node);//throws exceptions//returns index in nodesList of a particular NetNode node

        //since node exists -> return its Adjacents
        AdjacencyListHead adjListHeadOfNode = nodesList.get(indexToGetAdjHeadFrom);

        return adjListHeadOfNode.getAdjacencyList(); 
    }
    /**
     * This method gives the index in the nodesList ArrayList of a particular node.
     * @param node the node you want to get the index from
     * @return returns the index in the nodesList ArrayList of a particular node
     */
    int getNodeIndex(NetNode node){ 
        //Implement this method
        //returns the index in the nodesList ArrayList of a particular node.
        //You should check if node exists in the graph and that it is not null or else you should raise an IllegalArgumentException
       
        if(node==null){
            throw new IllegalArgumentException();//check if paramater is not null
        }
        //check that node exists in graph
        boolean nodeExists = false;
        int indexOfNetNode = 0;//if it does exists: utilize loop to get index too
        for(AdjacencyListHead x: nodesList){//USE ID
            int nodeId = x.getNetNode().getId();
            if(nodeId==node.getId()){
                nodeExists = true;
                break;
            }
            indexOfNetNode++;
        }
        if(nodeExists==false) throw new IllegalArgumentException();

        return indexOfNetNode;
    }
    /**
     * This method gives the number of adjacent nodes of a particular  node.
     * @param node the node you want to get the number of adjcent nodes from
     * @return returns the number of adjacent nodes of a particular  node
     */
    public int degree(NetNode node){ 
        //Implement this method
        //returns the number of adjacent nodes of a particular  node
        ////You should check if node exists in the graph and that it is not null or else you should raise an IllegalArgumentException
      
        LinkedList<Adjacent> adjsOfNode = getAdjacents(node);//Throws exception: return adj list of node

        return adjsOfNode.size(); 
    }
    /**
     * This method gives the maximum number of adjacent nodes connected to a particular node.
     * @return returns the maximum number of adjacent nodes connected to a particular node
     */
    public int getGraphMaxDegree()
	{
	       //Implement this method
        //returns the maximum number of adjacent nodes connected to a particular node
        int max = 0;
        for(AdjacencyListHead head: nodesList){//go through each head node
            LinkedList<Adjacent> adjsOfNode = head.getAdjacencyList();
            int numberOfAdjs = adjsOfNode.size();
            if(numberOfAdjs > max){//if a # of adj nodes larger: make new max
                max = numberOfAdjs;
            }
        }
        return max;
	}
    /**
     * This method gives an NetNode object from the index of the node in nodesList ArrayList.
     * @param index the index in nodesList of the NetNode object
     * @return returns an NetNode object from the index of the node in nodesList ArrayList
     */
    public NetNode nodeFromIndex(int index){
        //Implement this method
        //returns an NetNode object from the index of the node in nodesList ArrayList
        if(index>nodesList.size()-1 || index<0) throw new IllegalArgumentException();
        
        AdjacencyListHead adjListAtIndexGiven = nodesList.get(index);

        return adjListAtIndexGiven.getNetNode();
    }
    /**
    * This method prints the graph as an adjencyList format.
    * @return a string that has the graph as an adjencyList format.
    */
    public String printGraph(){//MAKE ONE DECIMAL POINT
        //Implement this method
        //returns a String representation of the network graph in the adjacency list format: e.g.
        /*  NO SPACES
            A:{(B,3),(D,2),(E,0.5)}
            B:{(A,3),(E,2)}
            C:{}
            D:{(A,2)}
            E:{(A,0.5),(B,2)}
        */
        String ans = "";

        for(AdjacencyListHead head: nodesList){//for each head
            ans += head.getNetNode().getName() + ":{";
            int adjListLen = head.getAdjacencyList().size();
            int index = 0;
            for(Adjacent adj: head.getAdjacencyList()){
                ans += "("+adj.getNeighbor().getName()+","+Math.floor(adj.getWeight() * 10) / 10 + ")";//cutt off to one decimal point
                if(index != adjListLen-1){
                    ans += ",";
                }
                index++;
            }

            ans += "}\n";
    
        }

        return ans;
    }

}
