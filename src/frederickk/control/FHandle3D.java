package frederickk.control;

/*
 *  Frederickk.Control 0.0.4
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a much simpler (for me anyway) processing GUI
 *  http://github.com/frederickk/frederickk
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.*;



public class FHandle3D extends FHandle {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	/*
	 * button in 3d space with help from mustermann
	 * http://processing.org/discourse/yabb2/YaBB.pl?num=1251823003
	 */
	private PGraphics3D g = new PGraphics3D();
	private PGraphics3D p3d = (PGraphics3D)g;
	private PMatrix3D proj = new PMatrix3D();
	private PMatrix3D cam = new PMatrix3D();
	private PMatrix3D modvw = new PMatrix3D();
	private PMatrix3D modvwInv = new PMatrix3D();
	private PMatrix3D screen2Model = new PMatrix3D();

	protected float z, x3D,y3D,z3D;
	PVector loc2D = new PVector();



	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	public FHandle3D(PApplet p5) {
		super(p5);
		setup3D(p5);
	}

	public FHandle3D(PApplet p5, String _name, float _x, float _y, float _z, int _w, int _h, int _labelType) {
		super(p5, _name, _x,_y, _w,_h, _labelType);
		setup3D(p5);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	private void setup3D(PApplet papplet) {
		g.setParent(papplet);
		p3d.setParent(papplet);
	}


	//-----------------------------------------------------------------------------
	protected void update() {
		if( getOver() && PRESSED ) LOCKED = true;

		if(LOCKED) {
			// calculate transformation matrix for projecting mouse coords
			// to the plane where the current selected vertex is
			// this doesn't work!
			screen2Model = modvwInv;
			screen2Model.apply(cam);

			float scrn[] = {MOUSE_X, MOUSE_Y, 0};
			float model[] = new float[3];
			screen2Model.mult(scrn, model);

			x = (int) model[0];
			y = (int) model[1];
			z = model[2];
		}

	}
	

	//-----------------------------------------------------------------------------
	public void draw() {
		update();
		//if(!DRAG_OFF) drag();

		System.out.println("g\t" + g.zbuffer);
		System.out.println("projection\t" + p3d.projection);
		System.out.println("camera\t" + p3d.camera);
		System.out.println("modelview\t" + p3d.modelview);
		System.out.println("modelviewIn\t" + p3d.modelviewInv);
		//proj = p3d.projection.get();
		//cam = p3d.camera.get();
		//modvw = p3d.modelview.get();
		//modvwInv = p3d.modelviewInv.get();
		

		//-----------------------------------------
		val.x = x;
		val.y = y;
		val.z = z;
		
		
		//-----------------------------------------
		// controller
		p5.pushStyle();
		p5.noStroke();

		if( getOver() ) p5.fill( getColorOver() );
		else if( LOCKED ) p5.fill( getColorPressed() );
		else p5.fill( getColorInactive() );
		
		p5.pushMatrix();
		p5.translate(x, y, z);
		//p5.sphere(width);
		p5.box(width);
		p5.popMatrix();

		
		//-----------------------------------------
		// label
		if(showLabels) {
			int a = (getColorInactive() >> 24) & 0xFF;
			p5.fill( white, a );
			if(labelType == LABEL_FLOAT) labelVal.draw( getStrValue(val.x,val.y, 2) );
			else if(labelType == LABEL_INT) labelVal.draw( getStrValue(val.x, val.y) );
		}
		p5.popStyle();
	}



	//-----------------------------------------------------------------------------
	// events
	//-----------------------------------------------------------------------------
	protected boolean getOver() {
		int x2D = (int) p5.screenX(x, y, z);
		int y2D = (int) p5.screenY(x, y, z);

		if(MOUSE_X-width*1.5 > x2D && MOUSE_X < x2D+width*1.5 && 
		   MOUSE_Y-height*1.5 > y2D && MOUSE_Y < y2D+height*1.5) {
			OVER = true;
		} else {
			OVER = false;
		}
		return OVER;
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	public void setCoord(float _x, float _y, float _z) {
		x = (int) _x;
		y = (int) _y;
		z = _z;
	}

}