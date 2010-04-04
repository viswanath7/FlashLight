package biz.vj.android.application.activity.menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import biz.vj.android.application.R;

public class ConcreteApplicationMenu implements ApplicationMenu
{

	private Activity         activity;
	private static final int DIALOG_CONFIRM_QUIT_ID = 0;
	private static final int DIALOG_ABOUT_APP_ID    = 1;

	public ConcreteApplicationMenu()
	{
		super();
	}

	/**
	 * The constructor with argument simplifies the registration of activity
	 * 
	 * @param activity
	 */
	public ConcreteApplicationMenu(Activity activity)
	{
		super();
		this.activity = activity;
	}

	/**
	 * 
	 * @see biz.vj.android.application.activity.menu.ApplicationMenu#registerActivity(android.app.Activity)
	 */
	public void registerActivity(Activity activity)
	{
		this.activity = activity;
	}

	public boolean populateMenu(Menu applicationMenu)
	{
		activity.getMenuInflater().inflate(R.menu.options_menu, applicationMenu);
		// Another method to add items to the menu --> appMenu.add(0, MENU_ITEM_IDENTIFIER, 0, MENU_ITEM_NAME);
		return true;
	}

	public boolean handleMenuItemSelection(MenuItem menuItemSelected)
	{
		// Handle the item selection
		switch (menuItemSelected.getItemId())
		{
			case R.id.menuAbout:
				activity.showDialog(DIALOG_ABOUT_APP_ID);
				break;
			case R.id.menuQuit:
				activity.showDialog(DIALOG_CONFIRM_QUIT_ID);
				break;
		}
		return true;
	}

	public Dialog instantiateDialog(int dialogId)
	{
		Dialog dialog;
		switch (dialogId)
		{
			case DIALOG_ABOUT_APP_ID:
				dialog = new Dialog(activity);
				// Set the custom layout as dialog's content view by passing it the custom layout's resource ID
				dialog.setContentView(R.layout.custom_dialog);

				dialog.setTitle(R.string.dialogAboutTitle);
				// Capture the View objects from the layout and modify their content.
				TextView textView = (TextView) dialog.findViewById(R.id.text);
				textView.setText(R.string.dialogAboutCredit);

				ImageView imageView = (ImageView) dialog.findViewById(R.id.image);
				imageView.setImageResource(R.drawable.android_skate);
				break;

			case DIALOG_CONFIRM_QUIT_ID:
				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setMessage(R.string.dialogConfirmQuitMessage);
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.dialogConfirmYes, new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int id)
						{
							// Kill the application completely.
							android.os.Process.killProcess(android.os.Process.myPid());
							/*
							 * There is no such thing as "closing the application" or "auto exiting the application" in the android
							 * application model.For the user all applications are always running all of the time. Android has been
							 * explicitly designed so that the user does NOT have to ever deal with things like closing applications
							 * to free up resources. The system will take care of killing application processes when their resources
							 * are needed elsewhere. It is strongly recommended to not use System.exit() as it interferes with Android's
							 * handling of the activity life-cycle and results in an awkward user-experience.
							 */
						}
					});
				builder.setNegativeButton(R.string.dialogConfirmNo, new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface dialog, int id)
						{
							dialog.dismiss();
						}
					});
				dialog = builder.create();
				break;
			default:
				dialog = null;
				break;
		}
		return dialog;
	}

}
