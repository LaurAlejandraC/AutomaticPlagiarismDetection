package logic.stringMetrics;

public class LongestCommonSubsequence implements StringMetric{

	int distance[], previousDistance[]; 
	
	@Override
	public double distance(String a, String b) {
		int longestCommonSubsequenceLength = lcs(a, b);
		double aLength = a.length();
		double bLength = b.length();
		double result = (double) longestCommonSubsequenceLength / Math.max(aLength, bLength);
		return 1 - result;
	}

	private int lcs(String a, String b) {
		int i, j;
		int aLength = a.length();
		int bLength = b.length();
		
		initDistance( bLength + 1 );
		
		for ( i = 0; i <= aLength; i++ ){
			for ( j = 0; j <= bLength; j++ ){
				if ( i == 0 || j == 0 )
					distance[j] = 0;
				else if ( a.charAt(i-1) == b.charAt(j-1) )
					distance[j] = previousDistance[j-1] + 1;
				else
					distance[j] = Math.max(distance[j], previousDistance[j-1]);
			}
			for( j= 0; j < distance.length; j++ ){
				previousDistance[j] = distance[j];
			}
		}
		return distance[bLength];
	}

	private void initDistance(int length) {
		if ( distance == null || distance.length < length ){
			distance = new int[length];
			previousDistance = new int[length];
			return ;
		}
		
		for ( int i = 0; i < length; i++ ){
			distance[i] = 0;
			previousDistance[i] = 0;
		}
	}
}
