package finalproject;


import java.util.ArrayList;
import java.util.LinkedList;

import finalproject.system.Tile;

public class SafestShortestPath extends ShortestPath {
	public int health;
	public Graph costGraph;
	public Graph damageGraph;
	public Graph aggregatedGraph;

	//TODO level 8: finish class for finding the safest shortest path with given health constraint
	public SafestShortestPath(Tile start, int health) {
		super(start);
		generateGraph();
		this.health = health;
	}


	public void generateGraph() {
		// TODO Auto-generated method stub
		ArrayList<Tile> a = GraphTraversal.BFS(super.source);
		costGraph = new Graph(a);
		for (Tile each : a) {
			for (Tile elt : each.neighbors) {
				if (elt.isWalkable()) {
					if (elt instanceof finalproject.tiles.MetroTile && each instanceof finalproject.tiles.MetroTile) {
						((finalproject.tiles.MetroTile) elt).fixMetro(each);
						costGraph.addEdge(each, elt, ((finalproject.tiles.MetroTile) elt).metroDistanceCost);
					} else {
						costGraph.addEdge(each, elt, elt.distanceCost);
					}

				}
			}
		}


		ArrayList<Tile> a2 = GraphTraversal.BFS(super.source);
		damageGraph = new Graph(a2);
		for (Tile each : a2) {
			for (Tile elt : each.neighbors) {
				if (elt.isWalkable()) {
					if (elt instanceof finalproject.tiles.MetroTile && each instanceof finalproject.tiles.MetroTile) {
						((finalproject.tiles.MetroTile) elt).fixMetro(each);
					}
					damageGraph.addEdge(each, elt, elt.damageCost);
				}
			}
		}

		ArrayList<Tile> a3 = GraphTraversal.BFS(super.source);
		aggregatedGraph = new Graph(a3);
		for (Tile each : a3) {
			for (Tile elt : each.neighbors) {
				if (elt.isWalkable()) {
					if (elt instanceof finalproject.tiles.MetroTile && each instanceof finalproject.tiles.MetroTile) {
						((finalproject.tiles.MetroTile) elt).fixMetro(each);
					}
					aggregatedGraph.addEdge(each, elt, elt.damageCost);
				}
			}
		}

	}

	public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints) {
		super.g=costGraph;
		ArrayList<Tile> Pc= super.findPath(start,waypoints);
		double Pccost = g.computePathCost(Pc);

		if(damageGraph.computePathCost(Pc)<health){
			return Pc;
		}

		//damage

		g=damageGraph;

		ArrayList<Tile> Pd= super.findPath(start,waypoints);
		double Pdcost = g.computePathCost(Pd);

		if(Pdcost>health){
			return null;
		}

		//Aggregate

		while(true){
			double cpc=costGraph.computePathCost(Pc);
			double cpd=costGraph.computePathCost(Pd);;
			double dpc=damageGraph.computePathCost(Pc);
			double dpd=damageGraph.computePathCost(Pd);
			double lambda= (cpc-cpd)/(dpd-dpc);
			for (Graph.Edge each: aggregatedGraph.getAllEdges()){
				each.weight= each.destination.distanceCost+lambda*each.destination.damageCost;
			}
			g=aggregatedGraph;
			ArrayList<Tile> Pr= super.findPath(start, waypoints);
			double Prcost= g.computePathCost(Pr);


			if(Prcost==g.computePathCost(Pc)){
				return Pd;
			}else if(damageGraph.computePathCost(Pr)<=health){
				Pd=Pr;
			}else{
				Pc=Pr;
			}

		}



	}




}
