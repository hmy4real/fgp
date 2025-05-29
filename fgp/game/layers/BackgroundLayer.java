package fgp.game.layers;

import fgp.engine.Layer;
import fgp.engine.LoadImage;
import fgp.game.SpaceShooter;
import fgp.game.bodies.Void;

@LoadImage("void.png")
public class BackgroundLayer extends Layer {
	
	public BackgroundLayer() {
	}
	
	@Override
	protected void changeLevel(int level) {
		clearBodies();
		
		
		for (int i = -1; i <= game.getXWorldSize(); i++) {
			addBody(new Void(this, i, -1));
			addBody(new Void(this, i, game.getYWorldSize()));
		}
		
		for (int j = -1; j <= game.getYWorldSize(); j++) {
			addBody(new Void(this, -1, j));
			addBody(new Void(this, game.getXWorldSize(), j));
		}
		/**
		if (level == 1) {
			for (int i = 0; i <= 32; i++) {
				for (int j = 0; j <= 20; j++) {
					addBody(new Void(this, i, j));
				}
			}
		}
		*/
	}
}