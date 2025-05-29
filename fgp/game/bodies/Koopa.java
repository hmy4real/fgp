package fgp.game.bodies;

import fgp.engine.Direction;
import fgp.engine.Layer;
import fgp.engine.LoadImage;
import fgp.engine.bodies.BodySimple;
import fgp.engine.bodies.IBodyPart;
import fgp.game.constants.ZIndexes;

@LoadImage("koopa_32.png")
public class Koopa extends BodySimple {
	int movement = 0;
	public Koopa(Layer l, int x, int y) {
			super(l, x, y, ZIndexes.ENEMY);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected boolean collision(IBodyPart myPart, IBodyPart otherPart, int dx, int dy) {
		if (otherPart instanceof ShipHero) {
			ShipHero m = (ShipHero) otherPart;
			game.lifeLost();
 
		}
		return false;
	}
	@Override
	public void advance2() {
		if (movement < 4) {
			move(Direction.Right);
		}else if (movement < 6) {
			move(Direction.Down);
		}else if (movement < 10) {
			move(Direction.Left);
		}else {
			move(Direction.Up);
		}
		movement ++;
		if (movement >= 12) {
			movement = 0;
		}
	}
	@Override
	protected int getAdvanceDelay() {
		return 30;
	}
}