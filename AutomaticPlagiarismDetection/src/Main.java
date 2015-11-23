import java.io.IOException;
import java.util.List;

import clustering.ClusteringAlgorithm;
import clustering.ClusteringTest;
import clustering.SingleLinkClustering;
import mdsj.MDSJ;

public class Main {
	
	/*
	 * Scales data in a matrix in the range [minValue,maxValue]
	 */
	static void reScaleData(double minValue, double maxValue, double data[][]){
		double min = Double.MAX_VALUE;
		double max = Double.MIN_NORMAL;
		
		for(int i=0; i<data.length; i++)
			for(int j=0; j<data[i].length; j++){
				min = Math.min(min, data[i][j]);
				max = Math.max(max, data[i][j]);
			}
		//x := (c - a) * (z - y) / (b - a) + y
		for(int i=0; i<data.length; i++)
			for(int j=0; j<data[i].length; j++)
				data[i][j] = (data[i][j] - min) * (maxValue - minValue) / (max - min) + minValue;
	}
	
	/*
	 * Performs MultiDimensional Scaling and rescales the data in a given range
	 */
	private static double[][] scaleDataTo2D( double distanceMatrix[][], double lowerBound, double upperBound ){
		double[][] output = MDSJ.classicalScaling(distanceMatrix); // apply MDS
		reScaleData(lowerBound, upperBound, output);
		return output;
	}

	/*
	 * Aux method to print a matrix
	 */
	private static void printMatrix(double[][] distanceMatrix) {
		for(int i=0; i<distanceMatrix.length; i++){
			for(int j=0; j<distanceMatrix.length; j++){
				System.out.print( distanceMatrix[i][j] + "\t" );
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		long time = System.currentTimeMillis();		
		//Defines some metrics to test
		StringMetric metrics[] = { new EditDistance() }; // IN ORDER TO CHANGE THE METRIC, CHANGE IT HERE :)
		//Reads and parse the source codes into a long string
		ReadSourceFiles reader = new ReadSourceFiles("../sourceCodes");
		List<String> strings = reader.getStrings();
		time = System.currentTimeMillis()-time;
		System.out.println("Time elapsed reading codes: " + time + " ms.");
		time = System.currentTimeMillis();
		
		//Builds the matrix distance according to a specific metric
		//In this case the object stored in metrics[0]
		DistanceMatrixBuilder builder = new DistanceMatrixBuilder(
				metrics[0], 
				strings
			);
		
		//Actually builds the matrix
		double distanceMatrix[][] = builder.buildMatrix();
		
		time = System.currentTimeMillis()-time;
		System.out.println("Time elapsed building Matrix: " + time + " ms.");
		System.out.println("Printing Matrix...");
		printMatrix( distanceMatrix ); //Prints the matrix
		
		double[][] output = scaleDataTo2D( distanceMatrix, 0.0, 500.0 ); // Creates 2 dimensional representation of the documents
													 // and set the coordinates in the range [0,500]
		
		int k = 3; //Number of clusters to be found
		ClusteringAlgorithm algorithm = new SingleLinkClustering(k); //Clustering Algorithm object
		int clusters[] = algorithm.cluster(distanceMatrix); //Actually performs the clustering
//		BasicClusteringWindow gui = new BasicClusteringWindow(output, clusters, k, 10.0); //Shows the GUI with a circle radius of 10.0
//		gui.setSize(800, 600);
	}
}
