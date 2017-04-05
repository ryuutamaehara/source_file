package fw;

import java.security.MessageDigest;

/** 文字列をハッシュ化するためのユーテリティ */
public class HashUtil
{
	private	final	static	String	DEFAULT_ALGORITHM = "MD5";

	private	static	HashUtil	instance = null;

	public	synchronized	static	HashUtil	getInstance()
	{
		if (instance != null)
			return instance;

		instance = new HashUtil();
		instance.init (DEFAULT_ALGORITHM);
		return instance;
	}

	private	String	algorithm = null;

	private	HashUtil(){}

	private	void	init (String algorithm)
	{
		this.algorithm = algorithm;
	}

	/** 指定された文字列に対応するハッシュ値を生成する。 */
	public	String	generateHash (String plane_string)
	{
		try {
			MessageDigest md = MessageDigest.getInstance (algorithm);
			md.update (plane_string.getBytes());
			String s = StringUtil.toHexString (md.digest());
			return s;
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
	}

	public	static	void	main (String[] args)
	{
		if (args.length != 1)
		{
			System.out.println ("ハッシュ化する文字列をパラメタとして渡してください。");
			System.exit(1);
		}
		else
		{
			String password = args[0];
			String hashed_password = HashUtil.getInstance().generateHash(password);
			System.out.println (hashed_password);
			System.exit(0);
		}
	}
}
