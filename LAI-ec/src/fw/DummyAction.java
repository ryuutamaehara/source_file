package fw;

/**
 * 単純にviewに転送する用action。
 */
@SuppressWarnings ("serial")
public class DummyAction extends AbstractAction
{
	/**
	 * 何もしない。
	 * @return
	 */
	public	String	execute()
	{
		return SUCCESS;
	}
}
