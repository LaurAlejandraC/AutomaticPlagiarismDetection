package clustering;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ClusteringTest {
	
	static double[][] randomEuclideanPoints(int n, double upperBound){
		double points[][] = new double[2][n];
		for(int i=0; i<n; i++){
			points[0][i] = ThreadLocalRandom.current().nextDouble(0.0, upperBound);
			points[1][i] = ThreadLocalRandom.current().nextDouble(0.0, upperBound);
		}
		
		return points;
	}
	
	static double[][] buildDistance( double points[][] ){
		
		int n = points[0].length;
		double dist[][] = new double[n][n];
		
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				double x1 = points[0][i],
					   x2 = points[0][j],
					   y1 = points[1][i],
					   y2 = points[1][j];
				
				dist[i][j] = Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) );
			}
		}
		
		return dist;
	}
	
	public static void main(String[] args) {
		double points[][] = randomEuclideanPoints(100, 600);
		double distanceMatrix[][] = buildDistance(points);
		int k = 7; //Number of clusters to be found
		ClusteringAlgorithm algorithm = new SingleLinkClustering(k); //Builds the clustering algorithm object
		int clusters[] = algorithm.cluster(distanceMatrix); //Executes the algorithm and return a mapping of each object to each cluster
		BasicClusteringWindow gui = new BasicClusteringWindow(points, clusters,k,3.0); //Shows a testing gui
	}
}
