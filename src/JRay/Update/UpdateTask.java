package JRay.Update;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Comparator;
import java.util.ConcurrentModificationException;

import main.Main;
import JRay.Geometry.Polygon3D;
import JRay.Thread.Task;

public class UpdateTask extends Task {

	int orderNum;
	double percent;
	boolean doneUpating;

	int myWait = 1;

	public UpdateTask() {
		
	}

	@Override
	public void runTask() { // The default task object
		try {
			myWait = Main.display.displayWait;
			if (!Main.display.paused && readyToUpdate()) { // Runs once for every time the rasterizers run
				Main.display.repaint(); // Makes the screen call the paintComponent method
				Main.sorting = true;
				Main.display.currentScene.updateDistances();
				ArrayList<Polygon3D> failBuffer = Main.display.currentScene.getCurrent();
				ArrayList<Polygon3D> toSort = Main.display.currentScene.getCurrent();
				try {
					Collections.sort(toSort, new Comparator<Polygon3D>() {
						@Override
						public int compare(Polygon3D poly1, Polygon3D poly2) {
							return Double.compare(poly1.distanceFromCamera, poly2.distanceFromCamera);
						}
					});
				} catch (ConcurrentModificationException ex) {
					toSort = failBuffer;
				}
				Main.display.currentScene.toRender = toSort;
				Main.sorting = false;
				for (int i = 0; i < Main.display.rasterizers.length; i++) { // Tells the rasterizers to start
					Main.display.rasterizers[i].isDone = false;
				}
			}
		} catch (IndexOutOfBoundsException ex) {

		}
	}

	public boolean readyToUpdate() {
		for (int i = 0; i < Main.display.rasterizers.length; i++) {
			if (Main.display.rasterizers[i].isDone == false) {
				return false;
			}
		}
		return true;
	}

	// Most methods will be overriden
	@Override
	public Boolean returnRunnable() {
		return true;
	}

	@Override
	public int getWait() {
		return myWait; // 1000 / wiat + 1 = FPS
	}

	@Override
	public int[] getData() {
		return new int[]{0};
	}

	@Override
	public int getCPULoad() {
		return 2; // 0 is no load, 3 is maximum load
	}
}