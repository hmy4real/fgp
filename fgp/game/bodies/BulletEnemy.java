package fgp.game.bodies;

import fgp.engine.Direction;
import fgp.engine.Layer;
import fgp.engine.LoadImage;
import fgp.engine.bodies.BodySimple;
import fgp.engine.bodies.IBodyPart;
import fgp.game.constants.ZIndexes;

@LoadImage({"bullet.png"})
public class BulletEnemy extends BodySimple {
    public BulletEnemy(Layer l, int x, int y) {
        super(l, x, y, ZIndexes.ENEMY);
    }

	@Override
	protected boolean collision(IBodyPart myPart, IBodyPart otherPart, int dx, int dy) {
		// TODO Auto-generated method stub
		return false;
	}

}
