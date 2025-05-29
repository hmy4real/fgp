package fgp.game.bodies;

import fgp.engine.Direction;
import fgp.engine.Layer;
import fgp.engine.LoadImage;
import fgp.engine.bodies.BodySimple;
import fgp.engine.bodies.IBodyPart;
import fgp.game.constants.ZIndexes;

@LoadImage({"bullet.png"})
public class BulletHero extends BodySimple {
	
	public BulletHero(Layer l, int x, int y) {
		super(l, x, y, ZIndexes.POISON);
	}
	
	public BulletHero(Layer l, int x, int y, int hp) {
		super(l, x, y, ZIndexes.POISON);
	}

	@Override
	protected boolean collision(IBodyPart myPart, IBodyPart otherPart, int dx, int dy) {
		if (otherPart instanceof Void) {
			this.markForRemoval();
		}
		if (otherPart instanceof Meteor) {
			this.hit();
			otherPart.getParent().hit();
			this.hit();
			otherPart.getParent().hit();
			this.hit();
			otherPart.getParent().hit();
			//move(Direction.Up);
			return true;
		}
		if (otherPart instanceof Mushroom) {
			
		}
		
		if (otherPart instanceof Primogem) {
			this.hit();
			otherPart.getParent().hit();
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public int getHealthMax() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getHealthCurrent() {
		// TODO Auto-generated method stub
		return super.getHealthCurrent();
	}

	@Override
	public Direction getHealthLocation() {
		// TODO Auto-generated method stub
		return super.getHealthLocation();
	}

	@Override
	public int getHealthBarWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	@Override
	public void advance2() {
		move(Direction.Up);
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
		return 5;
	}
}