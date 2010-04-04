package biz.vj.android.application.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import biz.vj.android.application.R;
import biz.vj.android.application.activity.menu.ApplicationMenu;
import biz.vj.android.application.activity.menu.ApplicationMenuFactory;
import biz.vj.android.application.activity.menu.UICategoryTagA;

/**
 * An activity presents a visual user interface for one focused endeavor the user can undertake. An application may contain one or
 * several activity classes. Although activity classes work together to form a cohesive user interface of an application, each
 * activity is independent from the others.
 * <p>
 * Typically, one of the activities is marked as the first one that should be presented to the user when the application is
 * launched. Moving from one activity to another is accomplished by having the current activity start the next one.
 * <p>
 * Each activity is given a default window to draw into, that typically fills the screen. It might also be smaller than the screen
 * and float on top of other windows. The visual content of the window is provided by a hierarchy of 'View' objects. Each view
 * controls a particular rectangular space within the window. Views are where an activity's interaction with the end user takes
 * place. A view hierarchy is placed within an activity's window by the Activity.setContentView() method
 * <p>
 * This activity represents the default welcome screen that is presented to the end-user when application is started. The
 * application consists of two views with a button each to toggle the background colour.
 * 
 * @author viswanathj
 * 
 */
public class RedFlashLight extends Activity implements UICategoryTagA
{

	private ApplicationMenu visitor;

	// Invoked by the Android framework when the Activity is first launched
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/**
		 * XML layout file is compiled into a "View" resource when the application is compiled so load the layout resource by calling
		 * setContentView(), passing it the reference to the layout resource in the form of "R.layout.layout_file_name". In our case,
		 * the layout file is named "main.xml"
		 */
		setContentView(R.layout.main); // create an instance of the view object and capture it from the layout file.
		// Capture the button from layout
		Button greenButton = (Button) findViewById(R.id.green_button);
		/**
		 * EventListener methods will be called by the Android framework when the View to which the EventListener has been registered,
		 * is triggered by user interaction with an item in the UI. onClick(), onLongClick(), onFocusChange(), onKey(), onTouch(),
		 * onCreateContextMenu() are call-back methods included in event listener interfaces. These methods are the sole inhabitants
		 * of their respective interface. To define one of these methods and handle events, one must implement the nested interface in
		 * Activity or define it as an anonymous inner class. Then, pass an instance of the implementation to the respective
		 * View.set...Listener() method. (E.g., call setOnClickListener() and pass it our implementation of the OnClickListener.)
		 */

		/*
		 * Flavour of anonymous Inner class used here - Inner classes declared without any class name within an argument to a method
		 * is an anonymous implementer of specified interface type. We make here both an implementation class and an instance of the
		 * class in the argument to setOnClickListener method
		 */
		greenButton.setOnClickListener(new View.OnClickListener()
			{ // Start a new class definition for the anonymous class that implements the android.view.View.OnClickListener static
				  // interface
				  public void onClick(View v)
				  { // Method of the interface to implement. This method is called when the view has been clicked.
					  /**
					   * Activities are activated by asynchronous messages called intents. An Intent object holds the content of the
					   * message. It names the action being requested and specifies the URI of the data to act on, among other things. An
					   * activity is launched (or given something new to do) by passing an Intent object to Context.startActivity() or
					   * Activity.startActivityForResult(). The responding activity can look at the initial intent that caused it to be
					   * launched by calling its getIntent() method. One activity often starts another.
					   * <p>
					   * The most significant use of Intent is in launching of activities, where it can be thought of as the glue between
					   * activities. It is basically a passive data structure holding an abstract description of an action to be performed.
					   * <p>
					   * There are two primary forms of Intents in use namely; Explicit and Implicit. The former has a specified component
					   * that has an exact class to run.The later doesn't have a specified component and the system determines the best
					   * available component to run for the Intent. IntentResolution maps an Intent to an Activity that can handle it.
					   * <p>
					   * In our case, the intent resolution mechanism basically revolves around matching an Intent against all of the
					   * <intent-filter> descriptions in the installed application packages.
					   */
					  Intent intent = new Intent(RedFlashLight.this, GreenFlashLight.class);
					  startActivity(intent); // Launch an activity
				  }
			  }// Closing the definition of anonymous inner class
		  );
		/*
		 * Everything happened as a part of method's argument so use closing parenthesis to complete the method invocation and
		 * end the statement greenButton.setOnClickListener(..... with a semicolon
		 */

		visitor = ApplicationMenuFactory.getApplicationMenu(this);
	}

	/**
	 * Use Builder pattern : Encapsulate the creation of an object in an "Builder" and let the client invoke the "Builder" construct
	 * the object of its desire. Using builder pattern encapsulates the manner in which a complex object is constructed. Objects can
	 * be constructed in multiple steps and varying process. All the internal implementation is hidden from the client and
	 * implementations can be swapped as the client only sees the abstract interface. Catch : Often used for building composite
	 * structures, usage of builder pattern requires domain knowledge unlike using a factory.
	 */

	/**
	 * When the "menu" button is pressed on the device and the options menu is opened, the android system will invoke this call back
	 * method. Over-riding this method in the activity will populate the Menu object.
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu appMenu)
	{
		super.onCreateOptionsMenu(appMenu);
		return visitor.populateMenu(appMenu);
	}

	/**
	 * When a menu item is selected from the Options Menu, the Android system will invoke this call back function on the Activity
	 * passing the menu-item that has been selected.
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		return visitor.handleMenuItemSelection(item);
	}

	/**
	 * A dialog, usually used for small notifications or short activities is a small window that appears in front of the current
	 * Activity forcing the underlying Activity to loose focus. A dialog is always created and displayed as a part of an activity.
	 * When this call-back method is used, the Android system automatically manages the state of each dialog and hooks them to the
	 * activity making it the owner of the dialog. This method instantiates the Dialog. This call back method invoked only the first
	 * time the dialog is opened, is passed the same ID as that of showDialog(int).
	 * 
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id)
	{
		super.onCreateDialog(id);
		return visitor.instantiateDialog(id);
	}

}
