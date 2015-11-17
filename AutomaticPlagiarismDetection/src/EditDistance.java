/*
 * Edit distance returns the minimum amount of operations required to
 * change one string into another, using operations like insertions,
 * deletions or substitutions.
 * */
public class EditDistance implements StringMetric{
	
	//double[][] distance;
	double prevDistance[], distance[];
	
	void initDistance(int n){
		
		if( distance == null || (distance.length < n || prevDistance.length < n) ){
			distance = new double[n];
			prevDistance = new double[n];
		}
		
		
		
		
		for(int j=0; j<n; j++){
			prevDistance[j] = 0.0;
			distance[j] = 0.0;
		}
	}
	
	@Override
	public double distance(String str1, String str2) {
		int i, j, m, n;
		m = str1.length();
		n = str2.length();
		
		initDistance(n+1);
		
		for (i = 0; i <= m; i++){
			for (j = 0; j <= n; j++ ){
				if (i == 0)
					//distance[i][j] = j;
					distance[j] = j;
				else if (j == 0)
					//distance[i][j] = i;
					distance[j] = i;
				else if (str1.charAt(i-1) == str2.charAt(j-1))
					//distance[i][j] = distance[i-1][j-1];
					distance[j] = prevDistance[j-1];
				else
					//distance[i][j] = 1 + Math.min(distance[i][j-1], Math.min(distance[i-1][j], distance[i-1][j-1]));
					distance[j] = 1 + Math.min(distance[j-1], Math.min(prevDistance[j], prevDistance[j-1]));
			}
			
			for(j=0; j<prevDistance.length; j++)
				prevDistance[j] = distance[j];
		}
		
		return distance[n];
	}

}
