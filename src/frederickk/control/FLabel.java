package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FLabel.java
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
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PConstants;



public class FLabel implements PConstants, FControlConstants {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 004L;
	protected static PApplet p5;

	protected String text = "";
	public int width,height;
	public float x,y;
	protected boolean bContainer = true;
	
	// constants
	protected static PFont TYPEFACE_REG = new PFont();
	protected static PFont TYPEFACE_BOLD = new PFont();
	protected static int TYPEFACE_SIZE = 0;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	FLabel(PApplet thePApplet) {
		p5 = thePApplet;
		setText(" ");
	}

	FLabel(PApplet thePApplet, float _x, float _y, PFont _typefaceLabel) {
		p5 = thePApplet;
		setText(" ");
		setTypeface(_typefaceLabel);
		setCoord(_x, _y);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	public void draw(String _text) {
		setText(_text);

		p5.pushStyle();
		p5.textFont(TYPEFACE_BOLD);
		//System.out.println( "FLabel " + text );
		//System.out.println( "w: " + Integer.toString(width) + " h: " + Integer.toString(height) );
		//System.out.println( "x: " + Float.toString(x) + " y: " + Float.toString(y) );
		if(bContainer) {
			p5.textAlign(LEFT, CENTER);
			p5.text(text, x+5,y, width,height);
		} else {
			p5.textAlign(LEFT);
			p5.text(text, x+5,y+TYPEFACE_SIZE);
		}
		p5.popStyle();
	}

	public void draw(String _text, int TEXT_ALIGNMENT) {
		setText(_text);

		p5.pushStyle();
		p5.textFont(TYPEFACE_BOLD);
		//System.out.println( "FLabel " + text );
		//System.out.println( "w: " + Integer.toString(width) + " h: " + Integer.toString(height) );
		//System.out.println( "x: " + Float.toString(x) + " y: " + Float.toString(y) );
		if(bContainer) {
			p5.textAlign(TEXT_ALIGNMENT, CENTER);
			p5.text(text, x+5,y, width,height);
		} else {
			p5.textAlign(TEXT_ALIGNMENT);
			p5.text(text, x+5,y+TYPEFACE_SIZE);
		}
		p5.popStyle();
	}

	public void draw(String _text, int TEXT_ALIGNMENT, int TYPE_STYLE) {
		setText(_text);

		p5.pushStyle();
		if(TYPE_STYLE == BOLD) p5.textFont(TYPEFACE_BOLD);
		else p5.textFont(TYPEFACE_REG);
		//System.out.println( "FLabel " + text );
		//System.out.println( "w: " + Integer.toString(width) + " h: " + Integer.toString(height) );
		//System.out.println( "x: " + Float.toString(x) + " y: " + Float.toString(y) );
		if(bContainer) {
			p5.textAlign(TEXT_ALIGNMENT, CENTER);
			p5.text(text, x+5,y, width,height);
		} else {
			p5.textAlign(TEXT_ALIGNMENT);
			p5.text(text, x+5,y+TYPEFACE_SIZE);
		}
		p5.popStyle();
	}

	
	
	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	public void set(float _x, float _y, PFont _typefaceLabel) {
		setTypeface(_typefaceLabel);
		setCoord(_x, _y);
		setSize(100,10);
	}

	public void set(float _x, float _y, PFont _typefaceLabel, PFont _typefaceLabelBold) {
		setTypeface(_typefaceLabel,_typefaceLabelBold);
		setCoord(_x, _y);
		setSize(100,10);
	}

	
	//-----------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	protected void setTypeface(PFont _typefaceLabel) {
		TYPEFACE_REG = _typefaceLabel;
		TYPEFACE_BOLD = _typefaceLabel;
		
		// broke in Processing 2.0bX
		TYPEFACE_SIZE = (TYPEFACE_REG.getFont().getSize())+1;
		
	}

	@SuppressWarnings("deprecation")
	protected void setTypeface(PFont _typefaceLabel, PFont _typefaceLabelBold) {
		TYPEFACE_REG = _typefaceLabel;
		TYPEFACE_BOLD = _typefaceLabelBold;

		// broke in Processing 2.0bX
		TYPEFACE_SIZE = (TYPEFACE_REG.getFont().getSize())+1;
	}
	

	//-----------------------------------------------------------------------------
	public void setText(String _text) {
		text = _text;
	}

	
	//-----------------------------------------------------------------------------
	public void setSize(int _w, int _h) {
		this.width = _w;
		this.height = _h;
		
		if(this.width < TYPEFACE_SIZE || this.height < TYPEFACE_SIZE) {
			bContainer = false;	
		} else {
			bContainer = true;	
		}
	}
	public void setCoord(float _x, float _y) {
		this.x = _x;
		this.y = _y;
	}

	//-----------------------------------------------------------------------------
	public void uncontained() {
		bContainer = false;
	}
	public void contained() {
		bContainer = true;
	}
	
	

	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public float getTypeSize() {
		float val = TYPEFACE_SIZE;
		return val;
	}

	public PFont getTypeface() {
		return TYPEFACE_REG;
	}
	
}
