package logic.clustering;

import java.io.Serializable;

/*
 * Defines a simple inferface for clustering data 
 * given a matrix distance.
 * 
 * @author: Andres Felipe Cruz
 */
public interface ClusteringAlgorithm extends Serializable {
	/*
	 * Performs a clustering routine given a matrix distance
	 * and returns an array where the ith element corresponds
	 * to the cluster label of the ith element.
	 */
	int[] cluster( double distanceMatrix[][] );
}
