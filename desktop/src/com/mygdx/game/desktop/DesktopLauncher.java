package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.PizzaSaborZumbi;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.height = PizzaSaborZumbi.ALTURATELA;
                config.width = PizzaSaborZumbi.LARGURATELA;
                config.title = "Pizza Sabor Zumbi";
                //config.fullscreen = true;
                config.resizable = false;
               
		new LwjglApplication(new PizzaSaborZumbi(), config);
	}
}
