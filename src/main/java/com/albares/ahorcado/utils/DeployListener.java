package com.albares.ahorcado.utils;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class DeployListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        Parameters.match.generateWord();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
       
    }
}
