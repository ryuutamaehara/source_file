package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fw.DBConnectionHolder;

/**
 * daoクラスにおけるfetch処理。
 * @param <T>
 */
interface Fetcher<T> {
	public	abstract	T	fetch (DBConnectionHolder ch, ResultSet rs)	throws	SQLException;
}
