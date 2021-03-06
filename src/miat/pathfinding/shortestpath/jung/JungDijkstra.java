package miat.pathfinding.shortestpath.jung;


import org.jgraph.graph.DefaultEdge;

import com.google.common.base.Function;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.SparseGraph;
import miat.pathfinding.graph.BenchmarkGraph;
import miat.pathfinding.graphGeneration.Translation;
import miat.pathfinding.results.Result;
import miat.pathfinding.shortestpath.AbstractShortestPathAlgorihm;

public class JungDijkstra<V,E> extends AbstractShortestPathAlgorihm<SparseGraph<V,E>> {

	@SuppressWarnings("unchecked")
	@Override
	public Result shortestPathComputation(BenchmarkGraph<Integer, DefaultEdge> graph, Integer source, Integer target) {
		SparseGraph<Integer, Integer> gsg = getCache() == null ? Translation.benchmarkGraphToJung(graph) : (SparseGraph<Integer, Integer>) getCache();
		DijkstraShortestPath<Integer, Integer> dijkstra = new DijkstraShortestPath<>(gsg, new WeightFunction1(), false);
		
		dijkstra.enableCaching(true);
		long t = System.currentTimeMillis();
		Number num = dijkstra.getDistance(source, target);
		t = System.currentTimeMillis() - t;
		return new Result(t, num.doubleValue());
	}
	
	@Override
	public String getLibrary() {
		return "Jung";
	}

	@Override
	public String getName() {
		return "Dijkstra";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result spatialShortestPathComputation(BenchmarkGraph<Coordinate, DefaultEdge> graph, Coordinate source, Coordinate target) {
		SparseGraph<Coordinate, Geometry> gsg = getCache() == null ? Translation.benchmarkGraphToJungSpatial(graph) : (SparseGraph<Coordinate, Geometry>) getCache();
		DijkstraShortestPath<Coordinate, Geometry> dijkstra = new DijkstraShortestPath<>(gsg, new WeightFunctionSpatial(), false);
		dijkstra.enableCaching(true);
		long t = System.currentTimeMillis();
		Number num = dijkstra.getDistance(source, target);
		t = System.currentTimeMillis() - t;
		return new Result(t, num.doubleValue());
	}
	
	public class WeightFunction1 implements Function<Integer,Integer> {
		@Override
		public Integer apply(Integer input) {
			return 1;
		}
	}
	
	public class WeightFunctionSpatial implements Function<Geometry,Integer> {
		@Override
		public Integer apply(Geometry input) {
			return (int)Math.round(input.getLength());
		}
	}

}
