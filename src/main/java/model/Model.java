package model;

import java.util.ArrayList;

public interface Model {
	public void create(Object bean);
	public void update(Object bean);
	public void delete(Object bean);
	public Object read(Object primaryKey);
	public ArrayList<Object> list();
	public ArrayList<Object> list(Object restricao);
}
