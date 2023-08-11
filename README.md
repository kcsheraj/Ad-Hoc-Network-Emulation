# Ad-Hoc-Network-Emulation
Ad Hoc Network emulation using custom graph data structure in Java.

In my completed project, I have successfully designed a graph-based data structure that accurately emulates the behavior of an Ad Hoc wireless network. This simulation closely mirrors the wireless networks we encounter in our daily lives, enabling seamless data exchange without the need for physical cables.

The foundation of my project involved creating a graph using a modified version of the adjacency list approach, a concept we extensively discussed in our class. Within this wireless network simulation, each wireless device is symbolized as a vertex within the weighted graph. Notably, the set of adjacent vertices for each device corresponds to the wireless nodes that fall within its broadcasting range. This ensures that devices can directly communicate with others within their wireless reach, accurately reflecting real-world wireless network behavior.

Two crucial functions further enhance the capabilities of this simulated wireless network:

minSpanningTree(): By implementing the Minimum Spanning Tree (MST) using Prim's Algorithm, I've established a mechanism to create the most efficient connections between wireless devices. This MST guarantees that all devices remain interconnected while minimizing the total length of connections required. This strategic optimization greatly enhances network efficiency and overall reliability.

getShortestPath(NetNode node1, NetNode node2): Determines the shortest path between any two specified nodes within the network. Leveraging Dijkstra's Algorithm, this function calculates the quickest and most direct route for data transmission between the designated nodes.
