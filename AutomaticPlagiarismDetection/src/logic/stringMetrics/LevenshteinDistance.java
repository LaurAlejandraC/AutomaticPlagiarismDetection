package logic.stringMetrics;

/*
 * Edit distance returns the minimum amount of operations required to
 * change one string into another, using operations like insertions,
 * deletions or substitutions.
 * */
public class LevenshteinDistance implements StringMetric{
	
	double previousDistance[], distance[];
	
	void initDistance(int n){
		
		if( distance == null || distance.length < n ){
			distance = new double[n];
			previousDistance = new double[n];
			return ;
		}
		
		for(int j=0; j<n; j++){
			previousDistance[j] = 0.0;
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
					distance[j] = j;
				else if (j == 0)
					distance[j] = i;
				else if (str1.charAt(i-1) == str2.charAt(j-1))
					distance[j] = previousDistance[j-1];
				else
					distance[j] = 1 + Math.min(distance[j-1], Math.min(previousDistance[j], previousDistance[j-1]));
			}
			
			for(j=0; j<previousDistance.length; j++)
				previousDistance[j] = distance[j];
		}
		
		return distance[n] / ((double) str2.length());
	}

}
