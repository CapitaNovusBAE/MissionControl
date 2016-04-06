package map;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLJPanel;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.util.BasicDragger;


/**
 * @author Martins
 *
 */
public class MapView  {

	private RenderableLayer layer;
	private List<Position> mousePositionOnMap;
	private Position pickedPosition;
	private WorldWindowGLJPanel worldWindPanel;
	/**
	 * Flag to indicate something was selected
	 */
	public static boolean selected = false;

	private Object lock = new Object();

public Object getLock(){
	return this.lock;
}
	/**
	 * Constructor
	 */
	public MapView() {
		this.mousePositionOnMap = new LinkedList<>();
	}

	/**
	 * @return all postions on the map
	 */
	public List<Position> getPositions(){
				return this.mousePositionOnMap;

	}
	/**
	 * @return JPanel with {@link WorldWindowGLJPanel}
	 */
	public JPanel getMap(){

		System.out.println("Started");

		// create a WorldWind main object
		this.worldWindPanel = new WorldWindowGLJPanel();
		// setting model for now Basic model
		this.worldWindPanel.setModel(new BasicModel());
		// adding for dragging
		this.worldWindPanel.getInputHandler().addSelectListener(new BasicDragger(this.worldWindPanel));
		// for now layer shared with others so screen has only one layer on top
		// Create one set of layers.

		layer = new RenderableLayer();
		this.worldWindPanel.getModel().getLayers().add(layer);
		// adding mouse listener to window
		// create an instance of the dragger and give it a WWJ instance to use

		this.worldWindPanel.getInputHandler().addMouseListener(getMouseListener());// ends mouse Listener

		return this.worldWindPanel;


	}// ends first view Constructor

	/**
	 * @return Mouse listener
	 */
	private MouseAdapter getMouseListener(){
		return new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent pE) {
				// getting position from current mouse click

				final Position mouseCurrentPosition = MapView.this.worldWindPanel.getCurrentPosition();

				// Making sure that not adding null positions and the same
				// position again
				if (mouseCurrentPosition != null && !(MapView.this.mousePositionOnMap.contains(mouseCurrentPosition))&& pE.getButton() == MouseEvent.BUTTON1) {
					// after null check to make sure coordinates are not null
					// avoiding null point exceptions
					synchronized (lock) {
						// creating list of position from map
						mousePositionOnMap.add(mouseCurrentPosition);
					}


					// adding select listener to window
					MapView.this.worldWindPanel.getInputHandler().addSelectListener(new SelectListener() {
						// running method if for selection
						@Override
						public void selected(final SelectEvent event) {

							// checking what is selected
							if (event.getEventAction().equals(SelectEvent.HOVER) && event.hasObjects()
									&& event.getTopObject() instanceof PointPlacemark) {
								// logic what to do after selection is finished
								final Object topObject = event.getTopObject();
								final String	 giveEvent = event.getEventAction();
								final boolean give = MapView.this.mousePositionOnMap.contains(MapView.this.pickedPosition);
								new Interaction(topObject,giveEvent,MapView.this.mousePositionOnMap,give);

								final PointPlacemark hoveredPointPlacemark = (PointPlacemark) event.getTopObject();

								MapView.this.pickedPosition = hoveredPointPlacemark.getPosition();
								System.out.println(MapView.this.mousePositionOnMap.contains(MapView.this.pickedPosition));

							} // ends logic for first type (if) statement

							if (event.getEventAction().equals(SelectEvent.DRAG_END)) {
								// it's possible to have a stack of objects at a given lat lon,
								// this ensures you get the one that was really clicked on

								final Position dragEndPosition = MapView.this.worldWindPanel.getCurrentPosition();
								removeFromListAfterAction(MapView.this.mousePositionOnMap, MapView.this.pickedPosition, dragEndPosition);
								Object dragObject = event.getTopObject();

								dragObject = null;

								System.out.println("Drag start   " + MapView.this.pickedPosition);
								layer.dispose();
								updateView();
								MapView.this.worldWindPanel.getModel().getLayers().add(layer);
							}

							if (event.getEventAction().equals(SelectEvent.RIGHT_CLICK)) {

								removeFromListAfterScreen(MapView.this.mousePositionOnMap, MapView.this.pickedPosition);
								layer.dispose();
								updateView();
								System.out.println("Removed succ");
							}


							// adding one more selection criteria
							else if (event.getEventAction().equals(SelectEvent.DRAG) && event.hasObjects()
									&& event.getTopObject() instanceof PointPlacemark) {

								// TODO

							} // end second if (else if) logic

						}// ends selected method

					});// ends select listeners

					// adding to window

					System.out.println("Current Pos= " + mouseCurrentPosition);
					updateView();

					//MapView.this.worldWindPanel.getModel().getLayers().add(layer);

				} // ends if loop if position is not null

				else {

					System.out.println("NOTHING added this position is on list, or other reason");

				} // ends else loop it position is null

			}// ends click event

		};
	}
	private void drawPolyLines() {
		if (this.mousePositionOnMap.size() > 1) {
			final Polyline polyline = new Polyline(this.mousePositionOnMap);

			polyline.setColor(Color.RED);
			polyline.setLineWidth(2);
			layer.addRenderable(polyline);
		}

	}

	private void drawMarkers() {
		System.out.println(mousePositionOnMap.size());
		for (final Position pos : this.mousePositionOnMap) {
			final PointPlacemark pointPlacemarkOnMapList = new PointPlacemark(pos);
			layer.addRenderable(pointPlacemarkOnMapList);


		}
	}

	/**
	 * Draw Marker and polylines
	 */
	public void updateView(){
		drawMarkers();
		drawPolyLines();
		if(!this.worldWindPanel.getModel().getLayers().contains(layer)){
			this.worldWindPanel.getModel().getLayers().add(layer);
		}
	}

	private List<Position> removeFromListAfterAction(final List<Position> list, final Position pick, final Position end) {
		if (list.contains(pick)) {
			System.out.println("Is found");
			final int indexToReplace = list.indexOf(pick);
			list.set(indexToReplace, end);
		}

		return this.mousePositionOnMap;
	}

	private List<Position> removeFromListAfterScreen(final List<Position> list, final Position pick) {
		if (list.contains(pick)) {
			System.out.println("Is found");
			final int indexToReplace = list.indexOf(pick);
			list.remove(indexToReplace);
		}

		return this.mousePositionOnMap;
	}



}// ends first view class
