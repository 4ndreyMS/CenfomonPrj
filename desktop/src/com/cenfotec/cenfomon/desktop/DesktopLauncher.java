package com.cenfotec.cenfomon.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cenfotec.cenfomon.BE.Cenfomones.abstract_product.Cenfomon;
import com.cenfotec.cenfomon.BE.gestores.CenfomonGestor;
import com.cenfotec.cenfomon.GameInstance;

public class DesktopLauncher {
	public static void main(String[] args){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 896;
		config.height = 504;
		config.title = "Cenfomon";
		new LwjglApplication(new GameInstance(), config);
	}
}
