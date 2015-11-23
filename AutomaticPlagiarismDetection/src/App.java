
import java.io.IOException;

import javax.swing.JFrame;

public class App {
	
	static Model model;
	static BasicClusteringWindow view;
	static String dir = "../sourceCodes";
	static StringMetric metric = new EditDistance();
	
	static void initModel() throws IOException{
		model = new Model( dir );
		initView();
	}
	
	static void initView(){
		view = new BasicClusteringWindow();
	}
	
	static void init() throws IOException{
		initModel();
	}
	
	
	public static void main(String[] args) throws IOException {
		init();
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
}