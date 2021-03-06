package miat.pathfinding.shortestpath;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import miat.pathfinding.shortestpath.coderodde.CodeRoddDijstra;
import miat.pathfinding.shortestpath.coderodde.CodeRoddAStar;
import miat.pathfinding.shortestpath.coderodde.CodeRoddNBAStar;
import miat.pathfinding.shortestpath.graphstream.GraphStreamAStar;
import miat.pathfinding.shortestpath.graphstream.GraphStreamBellmanFord;
import miat.pathfinding.shortestpath.graphstream.GraphStreamDijkstra;
import miat.pathfinding.shortestpath.grph.GrphDijkstra;
import miat.pathfinding.shortestpath.grph.GrphStackBasedBellmanFord;
import miat.pathfinding.shortestpath.jgrapht.JGraphtALTAstar;
import miat.pathfinding.shortestpath.jgrapht.JGraphtAStar;
import miat.pathfinding.shortestpath.jgrapht.JGraphtBellmanFord;
import miat.pathfinding.shortestpath.jgrapht.JGraphtBidirectionalDijkstra;
import miat.pathfinding.shortestpath.jgrapht.JGraphtDijkstra;
import miat.pathfinding.shortestpath.jgrapht.JGraphtFloydWarshall;
import miat.pathfinding.shortestpath.jgrapht.JGraphtJohnsonShortestPaths;
import miat.pathfinding.shortestpath.jung.JungDijkstra;

public class ShortestPathAlgorithms {

	private final List<ShortestPathAlgorithm> algorithms1path;
	private final List<ShortestPathAlgorithm> algorithmsAllpaths;
	
	
	public ShortestPathAlgorithms(){
		algorithms1path = new ArrayList<>();
		algorithms1path.add(new JGraphtDijkstra());
		algorithms1path.add(new JGraphtBidirectionalDijkstra());
		algorithms1path.add(new JGraphtAStar());
		algorithms1path.add(new JGraphtALTAstar(0.01, new Random()));
		//algorithms1path.add(new JGraphtBellmanFord());
		algorithms1path.add(new GraphStreamDijkstra());
		algorithms1path.add(new GraphStreamAStar());
		//algorithms1path.add(new GraphStreamBellmanFord());

		//algorithms1path.add(new GrphDijkstra());
		//algorithms1path.add(new GrphStackBasedBellmanFord());
		
		//algorithms1path.add(new JungDijkstra());
		

		algorithms1path.add(new CodeRoddAStar());
		algorithms1path.add(new CodeRoddDijstra());
		algorithms1path.add(new CodeRoddNBAStar());
		
		algorithmsAllpaths = new ArrayList<>();
		algorithmsAllpaths.add(new JGraphtJohnsonShortestPaths());
		algorithmsAllpaths.add(new JGraphtFloydWarshall());
				
	}

	public List<ShortestPathAlgorithm> getAlgorithms1Path() {
		return algorithms1path;
	}

	public List<ShortestPathAlgorithm> getAlgorithmsAllpaths() {
		return algorithmsAllpaths;
	}
	
	
}
