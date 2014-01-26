package org.wintrisstech.vicswagen;

import org.wintrisstech.vicswagen.R;

import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * This is the main activity of the VicsWagen example application.
 * 
 * This class is almost identical to the example code given in MainActivity in the IOIOLib.
 * The differences are:
 * 	- Removed the LED related code
 * 	- Added a logger that displays messages to the Android device screen
 */
public class VicsWagenActivity extends IOIOActivity {
    // private ToggleButton button_;

    /**
     * Text view that contains all logged messages
     */
    private TextView mText;
    private ScrollView scroller;

    /**
     * Called when the activity is first created. Here we normally initialize
     * our GUI.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	/*
	 * Since the android device is carried by the VicsWagen, we want to
	 * prevent a change of orientation, which would cause the activity to
	 * pause.
	 */
	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	// getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	setContentView(R.layout.main);

	mText = (TextView) findViewById(R.id.text);
	scroller = (ScrollView) findViewById(R.id.scroller);
	log(getString(R.string.wait_ioio));
    }

    /**
     * This is the thread on which all the IOIO activity happens. It will be run
     * every time the application is resumed and aborted when it is paused. The
     * method setup() will be called right after a connection with the IOIO has
     * been established (which might happen several times!). Then, loop() will
     * be called repetitively until the IOIO gets disconnected.
     */
    class Looper extends BaseIOIOLooper {

	/**
	 * Called every time a connection with IOIO has been established.
	 * Typically used to open pins.
	 * 
	 * @throws ConnectionLostException
	 *             When IOIO connection is lost.
	 * 
	 * @see ioio.lib.util.AbstractIOIOActivity.IOIOThread#setup()
	 */
	@Override
	protected void setup() throws ConnectionLostException {
	    /*
	     * When the setup() method is called the IOIO is connected.
	     */
	    log(getString(R.string.ioio_connected));

	    // TODO: Initialize

	}

	/**
	 * Called repetitively while the IOIO is connected.
	 * 
	 * @throws ConnectionLostException
	 *             When IOIO connection is lost.
	 * 
	 * @see ioio.lib.util.AbstractIOIOActivity.IOIOThread#loop()
	 */
	@Override
	public void loop() throws ConnectionLostException {
	    //TODO: loop stuff
	}
    }

    /**
     * A method to create our IOIO thread.
     * 
     * @see ioio.lib.util.AbstractIOIOActivity#createIOIOThread()
     */
    @Override
    protected IOIOLooper createIOIOLooper() {
	return new Looper();
    }

    /**
     * Writes a message to the Android screen.
     * 
     * @param msg
     *            the message to write
     */
    public void log(final String msg) {
	runOnUiThread(new Runnable() {

	    public void run() {
		mText.append(msg);
		mText.append("\n");
		scroller.smoothScrollTo(0, mText.getBottom());
	    }
	});
    }
}