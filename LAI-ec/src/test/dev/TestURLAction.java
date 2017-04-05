package test.dev;

import fw.AbstractAction;

/**
 * URLをテストするアクション。
 */
public class TestURLAction extends AbstractAction
{
	public	String	execute()
	{
		System.out.println ("URI : " + super.getRequestURI());
		System.out.println ("URL : " + super.getRequestURL());
		return SUCCESS;
	}
}
