package biz.vj.android.application.activity.menu;

import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.MenuItem;

/**
 * The interface to centralise the code, for operations related to application's menu. Usually the client i.e. the android system
 * invokes methods of this interface through call-back methods to accomplish the tasks related to application menu.
 * 
 * @author viswanathj
 * 
 */
public interface ApplicationMenu
{

	/**
	 * The invoker's information is obtained and stored locally to carry operations in the future. We are retaining this method
	 * merely as a back-up mechanism to tie the activity to the menu's operations, in case the parameterised constructor is not
	 * utilised.
	 * 
	 * @param activity
	 */
	public void registerActivity(Activity activity);

	/**
	 * populate the menu by inflating a menu resource that was defined in XML.
	 * 
	 * @param applicationMenu
	 * @return
	 */
	public boolean populateMenu(Menu applicationMenu);

	public boolean handleMenuItemSelection(MenuItem menuItemSelected);

	public Dialog instantiateDialog(int dialogId);
}
