package map;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;


/**
 * @author Martins
 *
 */
public class Interaction {
	/**
	 * Flag to indicate something was selected - this tracks both shapes(!) see
	 * notes above
	 */
	public boolean selected = false;
	/**
	 * Flag to indicate something is being dragged - this tracks both shapes(!)
	 * see notes above
	 */
	public boolean dragging = false;

	/** Constructor
	 * @param o
	 * @param event -
	 * @param list  - list of map's positions
	 * @param re -?
	 */
	public Interaction(final Object o, final String event, final List<Position> list, final boolean re){
		if (o != null) {
			// have a look at what object was selected.  We're only
			// interested in the SurfaceEllipses we drew..
			System.out.println("interact: " + o.getClass().getName());
			if(re){
				System.out.println("Happy days");
				final PointPlacemarkAttributes  pointPlacemarkAttrs = new PointPlacemarkAttributes();
				pointPlacemarkAttrs.setScale(2.0);
				((PointPlacemark) o).setAttributes(pointPlacemarkAttrs);

				final Thread restoreObjThread = new Thread(new Runnable() {

					// is finished, we create a new thread that sleeps
					// for a while and then wakes up and changes the
					// colour, as long as the user didn't just select
					// or start dragging the object.

					@Override
					public void run() {
						try {
							Thread.sleep(4000);
						}
						catch (final InterruptedException ex) {
							Logger.getLogger(Interaction.class.getName()).log(Level.SEVERE, null, ex);
						}
						// prevent race condition.. if the user clicked or is dragging... ;-)
						if (!event.equals(SelectEvent.DRAG) ) {
							pointPlacemarkAttrs.setScale(1.0);
							((PointPlacemark) o).setAttributes(pointPlacemarkAttrs);
						}
					}
				});
				restoreObjThread.setPriority(Thread.MIN_PRIORITY);
				restoreObjThread.start();
			}//ends second if
		}//ends constructor Interaction
	}//ends cosntructor
}//ends class
