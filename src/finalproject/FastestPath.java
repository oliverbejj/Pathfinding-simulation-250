package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;

public class FastestPath extends PathFindingService {
    //TODO level 6: find time prioritized path
    public FastestPath(Tile start) {
        super(start);
        generateGraph();
    }

	@Override
	public void generateGraph() {
		// TODO Auto-generated method stub
        ArrayList<Tile> a = GraphTraversal.BFS(super.source);
        super.g= new Graph(a);
        for(Tile each: a){
            for(Tile elt: each.neighbors){
                if (elt.isWalkable()){
                    if(elt instanceof finalproject.tiles.MetroTile && each instanceof finalproject.tiles.MetroTile){
                        ((finalproject.tiles.MetroTile) elt).fixMetro(each);
                        super.g.addEdge(each,elt,((finalproject.tiles.MetroTile) elt).metroTimeCost);
                    }else{
                        super.g.addEdge(each,elt,elt.timeCost);
                    }

                }
            }
        }
    }
		


}
