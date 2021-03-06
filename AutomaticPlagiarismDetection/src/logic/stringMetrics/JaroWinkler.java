package logic.stringMetrics;
import java.util.Arrays;

public class JaroWinkler implements StringMetric{

	private double threshold = 1;
	
	@Override
	public double distance(String a, String b) {
		return 1.0 - similarity(a, b);
	}

	private double similarity(String a, String b) {
		int[] mtp = matches(a, b);
        float matches = mtp[0];
        if (matches == 0) {
            return 0;
        }
        float j = ((matches / a.length() + matches / b.length() + (matches - mtp[1]) / matches)) / 3;
        float jw = j < getThreshold() ? j : j + Math.min(0.1f, 1f / mtp[3]) * mtp[2]
                * (1 - j);
        return jw;
	}
	
	public final void setThreshold(double threshold) {
        this.threshold = threshold;
    }
	
	private float getThreshold() {
		return (float) threshold;
	}

	private int[] matches(String a, String b) {
		String max, min;
        if (a.length() > b.length()) {
            max = a;
            min = b;
        } else {
            max = b;
            min = a;
        }
        int range = Math.max(max.length() / 2 - 1, 0);
        int[] matchIndexes = new int[min.length()];
        Arrays.fill(matchIndexes, -1);
        boolean[] matchFlags = new boolean[max.length()];
        int matches = 0;
        for (int mi = 0; mi < min.length(); mi++) {
            char c1 = min.charAt(mi);
            for (int xi = Math.max(mi - range, 0),
                    xn = Math.min(mi + range + 1, max.length()); xi < xn; xi++) {
                if (!matchFlags[xi] && c1 == max.charAt(xi)) {
                    matchIndexes[mi] = xi;
                    matchFlags[xi] = true;
                    matches++;
                    break;
                }
            }
        }
        char[] ma = new char[matches];
        char[] mb = new char[matches];
        for (int i = 0, si = 0; i < min.length(); i++) {
            if (matchIndexes[i] != -1) {
                ma[si] = min.charAt(i);
                si++;
            }
        }
        for (int i = 0, si = 0; i < max.length(); i++) {
            if (matchFlags[i]) {
                mb[si] = max.charAt(i);
                si++;
            }
        }
        int transpositions = 0;
        for (int mi = 0; mi < ma.length; mi++) {
            if (ma[mi] != mb[mi]) {
                transpositions++;
            }
        }
        int prefix = 0;
        for (int mi = 0; mi < min.length(); mi++) {
            if (a.charAt(mi) == b.charAt(mi)) {
                prefix++;
            } else {
                break;
            }
        }
        return new int[]{matches, transpositions / 2, prefix, max.length()};
	}

}
