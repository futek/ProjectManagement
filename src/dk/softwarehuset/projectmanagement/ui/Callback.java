package dk.softwarehuset.projectmanagement.ui;

import java.io.PrintWriter;

public interface Callback<T> {
	public abstract void callback(Screen source, PrintWriter out, T argument);
}