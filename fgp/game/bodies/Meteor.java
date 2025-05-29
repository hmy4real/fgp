package fgp.game.bodies;

import fgp.engine.Direction;
import fgp.engine.ImageTracker;
import fgp.engine.Layer;
import fgp.engine.LoadImage;
import fgp.engine.bodies.BodySimple;
import fgp.engine.bodies.IBodyPart;
import fgp.game.constants.ZIndexes;

@LoadImage({"lava_rock.png"})
public class Meteor extends BodySimple {
	 
	public Meteor(Layer l, int x, int y) {
		super(l, x, y, ZIndexes.POISON);
		this.hp = (int) (Math.random() * 10) + 1;
		
	}

	@Override
	protected boolean collision(IBodyPart myPart, IBodyPart otherPart, int dx, int dy) {
		if (otherPart instanceof ShipHero) {
			ShipHero m = (ShipHero) otherPart;
			m.hitBadThing(m);
			otherPart.markForRemoval();
			this.markForRemoval();
		}
		if (otherPart instanceof Void) {
			return true;
		}
		if (otherPart instanceof BulletHero) {
			this.hit();
			otherPart.getParent().hit();
			return true;
		}
		return false;
	}
	
	
	@Override
	public int getHealthMax() {
		// TODO Auto-generated method stub
		return super.getHealthMax();
		
	}
	
	@Override
	public boolean shouldShowFullHealthBar() {
		return true;
	}

	@Override
	public void advance2() {
		move(Direction.Down);
		if (getWorldX() == 2) {
			this.markForRemoval();
		}
		
		/**
		ImageTracker tracker;
		tracker = getImageTracker();
		
		if (tracker.spriteIndex == 0) {
			tracker.spriteIndex = 1;
		} else {
			tracker.spriteIndex = 0;
		}
		*/
	}
	
	@Override
	protected int getAdvanceDelay() {
		return (int) (50 * Math.random()) + 20;
	}
}
