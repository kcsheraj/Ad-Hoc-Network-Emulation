import java.util.*;
/**
 * This class represents a network of nodes on a grid.
 * The nodes are connected if they are 20sqrt(2) away from eachother
 * @author Sheraj KC
 */
public class Network {
    /**
     * The NetGraph object that represents the connected nodes in the grid.
     */
    public NetGraph networkGraph;//said make public
    /**
     * No-pramater constructor. Creates a network  of 1000 nodes in a 200x200 grid.
     */
    public Network(){
	       //Default constructor creates an Network consisting of 1000 nodes located in an area of 200x200
        Network sampleNetwork=new Network(1000,200);
    }
    /**
     * Creates a network of numNodes in a side x side grid.
     * @param numofNodes number of nodes in the network
     * @param side the length and with of the graph
     */
    public Network(int numofNodes,double side){//create network by joining nodes based on distance        
        
        ArrayList<AdjacencyListHead> nodesList=new ArrayList<AdjacencyListHead>();
        for (int i = 0; i < numofNodes; i++)//create arrayList to be inputed to create NetGraph
		{
			double coordinateX = side*Math.random();  
			double coordinateY = side*Math.random();  
			int id=i;
            String name="node "+i; 
			NetNode node=new NetNode(id,name,coordinateX,coordinateY);
            nodesList.add(new AdjacencyListHead(node));       
		}

        networkGraph=new NetGraph(nodesList);//create netGraph with the nodes: no links yet

        final double tranmissionRange = 20*Math.sqrt(2);//Tranmission range
        
        //Iterate over all the node pairs in the graph and connect the nodes with a distance <=20root2  with links
        for(AdjacencyListHead head: networkGraph.getNodesList()){//for each head
            NetNode node1 = head.getNetNode();
            for(AdjacencyListHead headTraverser: networkGraph.getNodesList()){//check node pairs
                NetNode node2 = headTraverser.getNetNode();
                if(node1!=node2){//DONT check itself tho

                    double distanceCalculated = euclideanDistance(node1, node2);  

                    if(distanceCalculated <= tranmissionRange){//in range ->create undirected link
                        networkGraph.addLink(node1, node2, distanceCalculated);
                    }

                }
            }

        }

    }
    /**
     * Calculates the distance between two nodes.
     * @param node1 the first node in the graph
     * @param node2 the second node in the graph
     * @return the distance between two nodes as a double using the distance formula
     */
    private double euclideanDistance(NetNode node1,NetNode node2){//CHECK IF CORRECT
	       //Implement this method
        //returns the Euclidean distance between two nodes node1 and node2
        //Calculate distance
        double node1x = node1.getX_coordinate();
        double node1y = node1.getY_coordinate();
        double node2x = node2.getX_coordinate();
        double node2y = node2.getY_coordinate();
        //calculate distance between nodes ->edge distance
        double euclideanDistanceCalculated = Math.sqrt(Math.pow((node2x-node1x), 2) + Math.pow((node2y-node1y), 2));   
        
        return euclideanDistanceCalculated;
    }
  
    /**
     * This method retuns a MST created from node at index 0 of the networkGraph.
     * @return a two-d array representing MST as a adjancy matrix
     */
    public int[][] minSpanningTree()
	{	
        //the running time should be <= O(n*n) where n is the number of vertices in the graph
        int numberVertices = networkGraph.getNumNodes();
        int[][] ansMinSpanningTree = new int[numberVertices][numberVertices];//create 2d arr spanning tree

        double key[] = new double[numberVertices];//key
        for(int i=0; i<key.length; i++){
            key[i] = Double.MAX_VALUE;
        }

        AdjacencyListHead pi[] = new AdjacencyListHead[numberVertices];//pi

        boolean hasVisit[] = new boolean[numberVertices];//hasVisit

        //given a node you want to get its indexpositon in the nodes list
        //store the real-index positon at index - FIX
        HashMap<Integer,Integer> idToIndexMap = new HashMap<>();
        int index = 0;
        for(AdjacencyListHead head: networkGraph.getNodesList()){//map ID -> index
            idToIndexMap.put(head.getNetNode().getId(), index);//key(ID),value(index)
            index++;
        }

        //First step
        AdjacencyListHead firstHead = networkGraph.getNodesList().get(0);//start prim from index 0 node
        for(Adjacent x: firstHead.getAdjacencyList()){//for each adj in first node
            int indexOfAdjInArr = idToIndexMap.get(x.getNeighbor().getId());//networkGraph.getNodeIndex(x.getNeighbor());//find index to alter
            key[indexOfAdjInArr] = x.getWeight();//set weight for vertex in key
            pi[indexOfAdjInArr] = firstHead;// say what set it in pi
        }
        hasVisit[0] = true;//first head visited
        key[0] = 0;//first head has 0 value
        int numberVisited = 1;//set how many visited - increases every time boolean is true

        //now for cycle
        while(numberVisited<numberVertices){//(O(n))
            //visit smallest node based on key
            int indexOfSmallestNotVisit = findSmallestIndexNotVisitInKey(key,hasVisit);//O(n)
            AdjacencyListHead currHead = networkGraph.getNodesList().get(indexOfSmallestNotVisit);//get node
            //update its adjcents
            for(Adjacent adj: currHead.getAdjacencyList()){
                int indexOfAdjNode = idToIndexMap.get(adj.getNeighbor().getId());//networkGraph.getNodeIndex(adj.getNeighbor());//CHECK BIG O//can change if id=index
                if(hasVisit[indexOfAdjNode] == false){//dont visit dequed nodes
                    //update node value if lesser found
                    if(adj.getWeight()<key[indexOfAdjNode]){
                        key[indexOfAdjNode] = adj.getWeight();
                        pi[indexOfAdjNode] = currHead;
                    }
                }
            }

            hasVisit[indexOfSmallestNotVisit] = true;//remove from queue
            numberVisited++;

            //set MST in matrix
            //AdjacencyListHead node1Head = currHead;
            AdjacencyListHead node2Head = pi[indexOfSmallestNotVisit];//what set currNode
            int node1Index = indexOfSmallestNotVisit;//curr node
            int node2Index = idToIndexMap.get(node2Head.getNetNode().getId());//prev node that set currNode//CHECK BIG O
            //undirected graph
            ansMinSpanningTree[node1Index][node2Index] = 1;
            ansMinSpanningTree[node2Index][node1Index] = 1;
        }



        return ansMinSpanningTree;
	}
    
    /**
     * This method gives the index in a netGraph's nodesList of the smallest value.
     * @param key the list of values of each vertex at a certain point
     * @param hasVisit the list of boolean values that represnts visited nodes
     * @return the index of the smallest value of a node in nodesList
     */
    private int findSmallestIndexNotVisitInKey(double[] key, boolean[] hasVisit) {//0(n)
        int indexOfSmallest = 0;//one index is garuntted to have new min
        double min = Double.MAX_VALUE;
        for(int i=0; i<key.length; i++){//CHECK->CHOSING BETWEEN TIES SHOULD NOT MATTER//i chose first of the smallest
            if( hasVisit[i]!=true && key[i] < min){
                min = key[i];
                indexOfSmallest = i;
            }
        }

        return indexOfSmallest;
    }

    /**
     * Returns the shortest path from node1 to node2 in a arrayList of nodes.
     * @param node1 the node you want to run Dijkstra’s algorithm on
     * @param node2 the node you wnat the shortest path to from node1
     * @return an arrayList of nodes that represent the shortest path from node2 to node2
     */
    public ArrayList<NetNode> getShortestPath(NetNode node1, NetNode node2){
        //the running time should be <= O(n*n) where n is the number of vertices in the graph

        int numberVertices = networkGraph.getNumNodes();

        double key[] = new double[numberVertices];//key
        for(int i=0; i<key.length; i++){//set keys to infinity - O(V)
            key[i] = Double.MAX_VALUE;
        }

        AdjacencyListHead pi[] = new AdjacencyListHead[numberVertices];//pi

        boolean hasVisit[] = new boolean[numberVertices];//hasVisit


        //given a node you want to get its indexpositon in the nodes list
        //store the real-index positon at index - FIX
        HashMap<Integer,Integer> idToIndexMap = new HashMap<>();
        int index = 0;
        for(AdjacencyListHead head: networkGraph.getNodesList()){//map ID -> index
            idToIndexMap.put(head.getNetNode().getId(), index);//key(ID),value(index)
            index++;
        }

        //START FROM NODE1 SPECIFIED
        //Starting step - visit node1 adjs and update values
        int indexOfnode1 = idToIndexMap.get(node1.getId());
        AdjacencyListHead firstHead = networkGraph.getNodesList().get(indexOfnode1);//get node1 adjListHead

        for(Adjacent x: firstHead.getAdjacencyList()){//for each adj in first node
            int indexOfAdjInArr = networkGraph.getNodeIndex(x.getNeighbor());//find index to alter//BIG O
            key[indexOfAdjInArr] = x.getWeight();//set weight for vertex in key
            pi[indexOfAdjInArr] = firstHead;// say what set it in pi
        }
        hasVisit[indexOfnode1] = true;//first head visited
        key[indexOfnode1] = 0;//first head has 0 value
        int numberVisited = 1;//set how many visited - increases every time boolean is true

        //NOW FOR CYCLE
        while(numberVisited < numberVertices){//while all vertices are not dequed ->O(n)
            //visit smallest node based on key
            int indexOfSmallestNotVisit = findSmallestIndexNotVisitInKey(key,hasVisit);//O(n)//USE PRIORITY QUEUE?
            AdjacencyListHead currHead = networkGraph.getNodesList().get(indexOfSmallestNotVisit);//get node
            int currHeadIndex = indexOfSmallestNotVisit;
            //update its adjcents
            for(Adjacent adj: currHead.getAdjacencyList()){//O(n)
                int indexOfAdjNode = idToIndexMap.get(adj.getNeighbor().getId());//networkGraph.getNodeIndex(adj.getNeighbor());//CHECK BIG O//USEHASHMAP
                if(hasVisit[indexOfAdjNode] == false){//dont visit dequed nodes
                    //update node value if lesser found
                    double costOfPath = adj.getWeight() + key[currHeadIndex];//edge weight + currVertex current weight //currHead.getNetNode().getId();////CHECK BIG O
                    if(costOfPath < key[indexOfAdjNode]){
                        key[indexOfAdjNode] = costOfPath;//adj.getWeight();
                        pi[indexOfAdjNode] = currHead;
                    }
                }
            }

            hasVisit[currHeadIndex] = true;//remove from queue
            numberVisited++;//update number of vertices visited/dequed
        }



        //put path in array list and return
        ArrayList<NetNode> shortestPath = new ArrayList<>();
        //from node 2 travel backwards//remeber dsyxtra onyly gets shortest for specicf node
        int curNodeHeadIndex = idToIndexMap.get(node2.getId());
        AdjacencyListHead curNodeHead = networkGraph.getNodesList().get(curNodeHeadIndex);//curr=node2//BIG(N)
        int node1HeadIndex = idToIndexMap.get(node1.getId());
        while(curNodeHeadIndex!=node1HeadIndex){
            shortestPath.add(curNodeHead.getNetNode());

            curNodeHead = pi[idToIndexMap.get(curNodeHead.getNetNode().getId())];
            curNodeHeadIndex = idToIndexMap.get(curNodeHead.getNetNode().getId());
        } 
        //add node 1
        shortestPath.add(node1);//IN REVERSE ORDER

        ArrayList<NetNode> shortestPathReversed = new ArrayList<>();
        //reverse arrayList
        for(int i=shortestPath.size()-1; i>=0; i--){
            shortestPathReversed.add(shortestPath.get(i));
        }


        return shortestPathReversed;
    }
 
    
	
}
