import java.util.ArrayList;

public class MainTester {
    public static void main(String[] args) {
        //CREATE NETWORK
        Network myNetwork = new Network(1000,200);
        //System.out.println(myNetwork.networkGraph.printGraph());

        //PRIM
        int[][] prim = myNetwork.minSpanningTree();
       // print array
        for(int r=0; r<prim.length; r++){
            for(int c=0; c<prim[0].length; c++){
                System.out.print(prim[r][c]+" ");
            }
            System.out.println();
        }
        System.out.println("prim finished");
        
        //DYXTRAS
        ArrayList<NetNode> dijkstra = myNetwork.getShortestPath(myNetwork.networkGraph.nodeFromIndex(0), myNetwork.networkGraph.nodeFromIndex(3));
        for(NetNode x: dijkstra){
            System.out.print(x.getName()+", ");
        }
        System.out.println();
        System.out.println("dyxtra finished");



        //Testing NETGRAPH DATASTRUCURE
        //insertNode()
        // NetGraph myGraph = new NetGraph(new ArrayList<AdjacencyListHead>());
        // myGraph.insertNetNode(0, "A", 0, 0);
        // myGraph.insertNetNode(1, "B", 0, 0);
        // myGraph.insertNetNode(2, "C", 0, 0);
        // myGraph.insertNetNode(3, "D", 0, 0);
        // myGraph.insertNetNode(4, "E", 0, 0);
        // myGraph.insertNetNode(5, "F", 0, 0);


        // //create links
        // //addLink()//nodeFromIndex()
        // myGraph.addLink(myGraph.nodeFromIndex(0), myGraph.nodeFromIndex(1), 0);
        // myGraph.addLink(myGraph.nodeFromIndex(0), myGraph.nodeFromIndex(3), 0);
        // myGraph.addLink(myGraph.nodeFromIndex(0), myGraph.nodeFromIndex(4), 0);
        // System.out.println(myGraph.printGraph());
        // myGraph.addLink(myGraph.nodeFromIndex(1), myGraph.nodeFromIndex(0), 0);
        // myGraph.addLink(myGraph.nodeFromIndex(1), myGraph.nodeFromIndex(4), 0);
        // System.out.println(myGraph.printGraph());
        // System.out.println("max degree: 3:" + myGraph.getGraphMaxDegree());

        // //deleteNode()/removeLink()
        // myGraph.deleteNetNode(myGraph.nodeFromIndex(1));
        // System.out.println(myGraph.printGraph());
        // //get maxDegree()
        // System.out.println("max degree: 2:" + myGraph.getGraphMaxDegree());
        // //numNodes
        // //numLinks
        // System.out.println(myGraph.getNumNodes());
        // System.out.println(myGraph.getNumLinks());
        // //insertNetNode exception
        // //myGraph.insertNetNode(0, null, 0, 0);

        // //addLink()
        // myGraph.addLink(myGraph.nodeFromIndex(0), myGraph.nodeFromIndex(1), 10);
        // System.out.println(myGraph.printGraph());
        // myGraph.addLink(myGraph.nodeFromIndex(0), myGraph.nodeFromIndex(1), 10);
        // System.out.println("check:"+myGraph.printGraph());

        // //addLink():  add same link:exists -> nothing done
        // myGraph.addLink(myGraph.nodeFromIndex(0), myGraph.nodeFromIndex(1), 10);
        // System.out.println(myGraph.printGraph());

        // //addLink exception: add same link:does not exist -> expect exception//1 becuse we deleted 1
        // // myGraph.addLink(myGraph.nodeFromIndex(0), new NetNode(1, null, 0, 0), 18);
        // // System.out.println(myGraph.printGraph());

        // //removelink()
        // myGraph.removeLink(myGraph.nodeFromIndex(0), myGraph.nodeFromIndex(1));
        // //myGraph.addLink(myGraph.nodeFromIndex(2), myGraph.nodeFromIndex(1), 0);
        // myGraph.addLink(myGraph.nodeFromIndex(2), myGraph.nodeFromIndex(2), 0);
        // System.out.println(myGraph.printGraph());
        // myGraph.addLink(myGraph.nodeFromIndex(2), myGraph.nodeFromIndex(3), 0);
        // System.out.println(myGraph.printGraph());
        // myGraph.removeLink(myGraph.nodeFromIndex(2), myGraph.nodeFromIndex(2));
        // System.out.println(myGraph.printGraph());

        // myGraph.removeLink(myGraph.nodeFromIndex(0), myGraph.nodeFromIndex(1));
        // System.out.println(myGraph.printGraph());
        // myGraph.removeLink(myGraph.nodeFromIndex(0), new NetNode(5, null, 0, 0));
        // System.out.println(myGraph.printGraph());
        //check pizza for updates - no calculate distance
        //test code
        //create tester
        //refactor code
        //style check
        //finiish p1
        //read p2
        //review prims

    }
}
//test work with random id's