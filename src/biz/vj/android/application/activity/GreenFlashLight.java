package biz.vj.android.application.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import biz.vj.android.application.R;
import biz.vj.android.application.activity.menu.ApplicationMenu;
import biz.vj.android.application.activity.menu.ApplicationMenuFactory;
import biz.vj.android.application.activity.menu.UICategoryTagA;

/**
 * Activity for the second screen in the flash light application. The application allows the user to toggle between the screens.
 * 
 * @author viswanathj
 * 
 */
public class GreenFlashLight extends Activity implements OnClickListener, UICategoryTagA
{
	private ApplicationMenu visitor;

	/*
	 * Unlike in "RedFlashLight" activity, we are using the more convenient alternative approach of implementing the OnClickListener
	 * as a part of Activity in order to avoid extra class load and object allocation.
	 */
	public void onClick(View v)
	{
		// Implement the OnClickListener callback by performing the intended action when the button is clicked.
		finish(); // close the activity and propagate the ActivityResult to whomsoever launched it via onActivityResult()
	}

	@Override
	protected void onCreate(Bundle savedValues)
	{
		super.onCreate(savedValues);
		setContentView(R.layout.auxiliary);
		Button button = (Button) findViewById(R.id.red_button);
		button.setOnClickListener(this);
		visitor = ApplicationMenuFactory.getApplicationMenu(this);
	}

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
