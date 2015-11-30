
import java.io.IOException;

public class App {
	
	static Model model;
	static MainFrame view;
	static String dir = "../lol";
	static StringMetric metric = new EditDistance();
	static StringMetric metrics[] = {new EditDistance(), new JaroWinkler(), new LongestCommonSubsequence()};
	
	static void initModel() throws IOException{
		model = new Model( dir );
		
	}
	
	static void initView(){
		view = new MainFrame();
		view.setVisible(true);
//		view.setSize(600,600);
	}
	
	
	static void init() throws IOException{
		initModel();
		initView();
	}
	
	public static void main(String[] args) throws IOException {
		init();
	}
	
	public static void setSourceCode(int idx){
		view.setSourceCode(model.getSourceCode(idx));
	}

	public static double getRadius() {
		return model.getRadius();
	}

	public static int[] getClusters() {
		return model.getClusters();
	}

	public static StringMetric getMetric() {
		return metric;
	}

	public static double[][] getPoints() {
		return model.getPoints();	
	}

	public static void setClusters(int clusters) {
		try {
			model.setNumberOfClusters(clusters);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void modelChanged(){
		view.getData();
		view.repaint();
	}

	public static void computeModel() {
		try {
			model.computeModel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getSourceCode(int idx) {
		return model.getSourceCode(idx);
	}

	public static void repaintView() {
		// TODO Auto-generated method stub
		view.repaint();
	}

	public static void selectMetric(int selectedIndex) {
		metric = metrics[selectedIndex];
	}

	public static String[][] getReport(int p) {
		// TODO Auto-generated method stub
		return model.getReport( ((double)p) / 100.0 );
	}

	public static String[] getReportColumns() {
		return new String[]{ "Code A", "Code B", "Percentage" };
	}

	public static String[][] getReportByCluster(Integer cluster) {
		return model.getReportByCluster(cluster);
	}

	public static String[] getClusterReportColumns() {
		// TODO Auto-generated method stub
		return new String[]{ "Cluster", "Code A", "Code B", "Percentage" };
	}

	public static String[][] clusterReport() {
		return model.clusterReport();
	}
}
