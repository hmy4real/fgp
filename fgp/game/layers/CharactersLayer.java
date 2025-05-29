package fgp.game.layers;

import fgp.engine.Layer;
import fgp.engine.Vector;
import fgp.game.bodies.BulletHero;
import fgp.game.bodies.Door;
import fgp.game.bodies.Koopa;
import fgp.game.bodies.ShipHero;
import fgp.game.bodies.Mushroom;
import fgp.game.bodies.Primogem;
import fgp.game.bodies.Meteor;

public class CharactersLayer extends Layer {
	private ShipHero hero;
	@Override
	protected void changeLevel(int level) {
		// TODO Auto-generated method stub
		clearBodies();
		
		hero = new ShipHero(this, game.getXWorldSize() / 2 - 1, game.getYWorldSize() - 1);
		addBody(hero);
			
		addBody(new Mushroom(this, 7, 11));
		
		//int rx = (int) (Math.random() * game.getXWorldSize());
		//int ry = (int) (Math.random() * game.getYWorldSize());
		
		for (int i = 0; i < 5; i++) {
			int rx = (int) (Math.random() * game.getXWorldSize());
			int ry = (int) (Math.random() * game.getYWorldSize());
			this.addBody(new Primogem(this, rx, ry));
		}
	}
	@Override
	protected int getAdvanceDelay() {
		return 50 + (int) (Math.random() * 100);
	}
	
	@Override
	protected void advance2() {
		// int rightEdge = game.getXWorldSize() - 1;
		int topEdge = 0;
		int randomX = (int) (game.getXWorldSize() * Math.random());
		Meteor pm = new Meteor(this, randomX, topEdge);
		addBody(pm);
	}
	
	public void shootBullet() {
		BulletHero bulletHero = new BulletHero(this, hero.getX(), hero.getY() - 1);
		
		addBody(bulletHero);
	}

}
 