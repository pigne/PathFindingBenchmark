package miat.pathfinding.shortestpath.jgrapht;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;

import com.vividsolutions.jts.geom.Coordinate;

import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.AbstractShortestPathAlgorihm;

public class JGraphtBellmanFord <V,E> extends AbstractShortestPathAlgorihm<Graph<V,E>>{

	@Override
	public Result shortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target) {
		BellmanFordShortestPath<Integer, DefaultEdge> bf = new BellmanFordShortestPath<>(graph.getGraph());
		long t = System.currentTimeMillis();
		GraphPath<Integer, DefaultEdge> path =bf.getPath(source, target);
		
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgeList().isEmpty()) return new Result(t, 0.0);
		return new Result(t, graph.getGraph().getType().isWeighted() ? path.getWeight() : path.getLength());
	}
	
	@Override
	public String getLibrary() {
		return "JGrapht";
	}

	@Override
	public String getName() {
		return "BellmanFord";
	}

	@Override
	public Result spatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source,
			Coordinate target) {
		BellmanFordShortestPath<Coordinate, DefaultEdge> bf = new BellmanFordShortestPath<>(graph.getGraph());
		long t = System.currentTimeMillis();
		GraphPath<Coordinate, DefaultEdge> path =bf.getPath(source, target);
		t = System.currentTimeMillis() - t;
		if (path == null || path.getEdgeList().isEmpty()) return new Result(t, 0.0);
		return new Result(t, graph.getGraph().getType().isWeighted() ? path.getWeight() : path.getLength());
	}
}