package frederickk.api.flickr;

/*
 *  Frederickk.api.flickr 001
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a library for easier use of the flickr api
 *  http://code.google.com/p/frederickk/
 *  
 *  multi-threading insight via marius watz
 *  http://processing.org/discourse/yabb2/YaBB.pl?board=Integrate;action=display;num=1204990614
 *
 *  and ben fry
 *  http://processing.org/discourse/yabb2/YaBB.pl?num=1119238260
 * 
 */


import processing.core.*;
import java.util.ArrayList;

public class FFlickrLoader implements Runnable {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	private static PApplet p5;
	private Thread thread;

	private ArrayList<String> imageList;
	private ArrayList<PImage> images = new ArrayList();
	private int num = 0;
	private int remaining = 0;

	private boolean COMPLETE = false;

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	public FFlickrLoader(PApplet papplet) {
		p5 = papplet;
		p5.registerDispose(this);
	}

	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	/**
	 * run() is a required method
	 */
	public void run() {
		int loadID = 0;

		while(!COMPLETE) {
			if(num > 0) {
				while(images.size() < num) {
				//do {
					String src = (String) imageList.get(loadID);
					PImage tmp = p5.loadImage( src );
					images.add( tmp );

					System.out.print( src + "\t" ); 
					//System.out.println(images.size() + "\t" + num + "\tloaded");
					System.out.println( "images[" + loadID + "] loaded" );
					
					loadID++;
					remaining--;
					break;
				} //while(images.size() < num);
			}

			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}	
		}

		System.out.println("FFlickrLoader thread exiting");
	}

	/**
	 * @param _imageList
	 * 			the list of url's to load
	 */ 
	public void toLoad(ArrayList _imageList) {
		imageList = _imageList;
		remaining = imageList.size();
		num = imageList.size();
	}

	/**
	 * let the threading begin
	 */
	public void start() {
		thread = new Thread(this);
		thread.start();
	}	

	/**
	 * let the threading end
	 */
	public void stop() {
		COMPLETE = true;
		thread = null;
	}

	/**
	 * this will magically be called by the parent once the user hits stop
	 * this functionality hasn't been tested heavily so if it doesn't work, file a bug
	 */
	public void dispose() {
		stop();
	}

	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	public PImage getImage(int w) {
		PImage image = (PImage) images.get(w);
		/*
		// return null if incorrect ID, image not loaded or error occurred
		if(image == null || !image.loaded || image.error) {
			return null;
		}
		 */

		return image;
	}

	/**
	 * return images loaded
	 * 
	 */ 
	public ArrayList<PImage> getImages() {
		return images;
	}


}
