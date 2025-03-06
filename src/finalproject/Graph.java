package finalproject;


import java.util.ArrayList;
import java.util.HashSet;

import finalproject.system.Tile;
import finalproject.system.TileType;
import finalproject.tiles.*;

public class Graph {
	ArrayList<Tile> vertices = new ArrayList<Tile>();
    ArrayList<Edge> edges = new ArrayList<Edge>();



    // TODO level 2: initialize and assign all variables inside the constructor
	public Graph(ArrayList<Tile> vertices) {
		this.vertices=vertices;
	}
	
    // TODO level 2: add an edge to the graph
    public void addEdge(Tile origin, Tile destination, double weight){
        this.edges.add(new Edge(origin,destination,weight));
    	
    }
    
    // TODO level 2: return a list of all edges in the graph
	public ArrayList<Edge> getAllEdges() {
        return this.edges;
	}
  
	// TODO level 2: return list of tiles adjacent to t
	public ArrayList<Tile> getNeighbors(Tile t) {
        ArrayList<Tile> adj = new ArrayList<Tile>();
        for (Edge each: this.edges){
            if(each.getStart().equals(t)){
                adj.add(each.getEnd());
            }
        }
    	return adj;
    }
	
	// TODO level 2: return total cost for the input path
	public double computePathCost(ArrayList<Tile> path) {
        double sum=0.0;
        for(int i = 0; i<path.size()-1;i++){
            for (Edge each: this.edges){
                if(each.getStart().equals(path.get(i))&& each.getEnd().equals(path.get(i+1))){
                    sum+= each.weight;
                    break;
                }
            }
        }
		return sum;
	}
	
   
    public static class Edge{
    	Tile origin;
    	Tile destination;
    	double weight;

        // TODO level 2: initialize appropriate fields
        public Edge(Tile s, Tile d, double cost){
        	this.origin=s;
            this.destination=d;
            this.weight=cost;
        }
        
        // TODO level 2: getter function 1
        public Tile getStart(){
            return this.origin;
        }

        
        // TODO level 2: getter function 2
        public Tile getEnd() {
            return this.destination;
        }
        
    }
    
}