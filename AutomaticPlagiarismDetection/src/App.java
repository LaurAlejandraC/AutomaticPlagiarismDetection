
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class App {

	static Model model;
	static MainFrame view;
	static String dir = "../sourceCodes";
	static StringMetric metric = new EditDistance();
	static StringMetric metrics[] = { new JaroWinkler(), new EditDistance(), new LongestCommonSubsequence() };

	static final int NOPARAMETER = 0;
	static final int NOPARAMETERKMEANS = 1;
	static final int SINGLELINK = 2;
	static final int KMEANS = 3;

	static final String[] algorithmNames = { "No parameter clustering (Hierachical Based)",
			"No parameter clustering (KMeans Based)", "Single link clustering", "KMeans Clustering" };

	static int CLUSTERING_ALGORITHM = NOPARAMETERKMEANS;

	static void initModel() throws IOException {
		model = new Model(dir);
	}

	static void selectClusteringAlgorithmId(int id) {
		App.CLUSTERING_ALGORITHM = id;
		System.out.println("Setting " + CLUSTERING_ALGORITHM + " " + getCurrentAlgorithmName());
	}

	static void initView() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		view = new MainFrame();
		view.setVisible(true);
		// view.setSize(600,600);
	}

	static void init() throws IOException {
		initModel();
		initView();
	}

	public static void main(String[] args) throws IOException {
		init();
	}

	public static void setSourceCode(int idx) {
		model.initIfNeeded();
		view.setSourceCode(model.getSourceCode(idx));

	}

	public static double getRadius() {
		return model.getRadius();
	}

	public static int[] getClusters() {
		model.initIfNeeded();
		return model.getClusters();
	}

	public static StringMetric getMetric() {
		return metric;
	}

	public static double[][] getPoints() {
		model.initIfNeeded();
		return model.getPoints();
	}

	public static void setClusters(int clusters) {
		try {
			model.setNumberOfClusters(clusters);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void modelChanged() {
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
		model.initIfNeeded();
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
		model.initIfNeeded();
		return model.getReport(((double) p) / 100.0);
	}

	public static String[] getReportColumns() {
		return new String[] { "Code A", "Code B", "Percentage" };
	}

	public static String[][] getReportByCluster(Integer cluster) {
		model.initIfNeeded();
		return model.getReportByCluster(cluster);
	}

	public static String[] getClusterReportColumns() {
		// TODO Auto-generated method stub
		return new String[] { "Code A", "Code B", "Cluster", "Percentage" };
	}

	public static String[][] clusterReport() {
		model.initIfNeeded();
		return model.clusterReport();
	}

	public static void setSourceCodesDirectory(String selected) {
		dir = selected;
		model.setSourceFolder(selected);
		view.setTitle(getSoureCodeDirectory());
		view.repaint();
	}

	public static String getSoureCodeDirectory() {
		return dir;
	}

	public static String getCurrentAlgorithmName() {
		return algorithmNames[CLUSTERING_ALGORITHM];
	}

	public static int getNumberOfClusters() {
		return model.getNumberOfClusters();
	}
}
