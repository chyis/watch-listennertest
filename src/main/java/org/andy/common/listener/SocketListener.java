package org.andy.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SocketListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {

		System.err.println("nettyListener Startup!");
		new Thread() {
			@Override
			public void run() {
				try {
					ServiceServer nettyServer = new ServiceServer(8090);
					nettyServer.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();

		System.err.println("nettyListener end!");

	}

	public void contextDestroyed(ServletContextEvent sce) {

		System.err.println("destroy");
	}

}
