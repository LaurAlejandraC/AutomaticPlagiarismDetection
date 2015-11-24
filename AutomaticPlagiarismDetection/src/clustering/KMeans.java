package clustering;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class KMeans implements ClusteringAlgorithm {

	private int k;
	private double points[][];
	private int iterations;
	double x[], y[];
	
	public KMeans(int k, double points[][], int iterations) {
		this.k = k;
		this.points = points;
		this.iterations = iterations;
	}



	public KMeans(int numberOfClusters, double[][] output) {
		this(numberOfClusters,output,1000);
	}



	private double distance(double x1, double y1, double x2, double y2){
		return (x1-x2)*(x1-x2) - (y1-y2)*(y1-y2);
	}
	
	public double avgDistanceFromCentroid(int clusters[]){
		int n = points[0].length;
		double avg = 0.0;
		for(int i=0; i<n; i++){
			int c = clusters[i];
			avg += Math.sqrt(distance(points[0][i], points[1][i], x[c], y[c]));
		}
		
		return avg / ((double)n);
	}
	
	@Override
	public int[] cluster(double[][] distanceMatrix) {
		k = Math.min(distanceMatrix.length, k);
		x = new double[k];
		y = new double[k];
		
		
		double auxX[] = new double[k];
		double auxY[] = new double[k];
		
		int n = distanceMatrix.length;
		
		for(int i=0; i<k; i++){
			x[i] = points[0][ ThreadLocalRandom.current().nextInt(n) ];
			y[i] = points[1][ ThreadLocalRandom.current().nextInt(n) ];
		}
		
		int cluster[] = new int[n];
		
		for(int it=0; it<iterations; it++){
			for(int i=0; i<n; i++){
				int aux = 0;
				double d = distance( x[aux], y[aux], points[0][i], points[1][i] );
				for(int j=1; j<k; j++){
					double tmp = distance( x[j], y[j], points[0][i], points[1][i] );
					if( tmp < d ){
						d = tmp;
						aux = j;
					}
				}
				
				cluster[i] = aux;
			}
			
			
		}
		
		Arrays.fill(auxX, 0.0);
		Arrays.fill(auxY, 0.0);
		
		for(int i=0; i<n; i++){
			auxX[ cluster[i] ] += points[0][i];
			auxY[ cluster[i] ] += points[1][i];
		}
		
		for(int i=0; i<n; i++){
			x[ cluster[i] ] = auxX[ cluster[i] ] / ((double)n);
			y[ cluster[i] ] = auxY[ cluster[i] ] / ((double)n);
		}
		
		return cluster;
	}

}
