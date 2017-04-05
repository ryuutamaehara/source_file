package fw;

/**
 * 単純にviewに転送する用action。
 * @author tsuhtan
 *
 */
@SuppressWarnings ("serial")
public class DummyAction extends AbstractDBAction
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
