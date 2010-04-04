package biz.vj.android.application.activity.menu;

import android.app.Activity;

/**
 * The factory identifies the invoker and returns the visitor implementation for the menu UI tied to the activity
 * 
 * @author viswanathj
 * 
 */
public class ApplicationMenuFactory
{
	public static ApplicationMenu getApplicationMenu(UICategoryTagA activity)
	{
		return new ConcreteApplicationMenu((Activity) activity);
	}

	/*
	 * public static ApplicationMenuVisitor getApplicationMenuVisitor(UICategoryTagB activity)
	 * {
	 * return new AnotherConcreteApplicationMenuVisitor();
	 * }
	 */

}
