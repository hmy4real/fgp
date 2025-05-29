package fgp.game.bodies;

import fgp.engine.Direction;
import fgp.engine.Layer;
import fgp.engine.LoadImage;
import fgp.engine.bodies.BodySimple;
import fgp.engine.bodies.IBodyPart;
import fgp.game.constants.ZIndexes;

/**
 * @author steve.han
 * 
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡒⠀⠀⠀⢠⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡄⣇⠀⠀⠀⠙⢦⠘⢿⣆⠀⠘⢷⡀⢀⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⡆⠀⠀⠀⠀⢳⡀⠻⣦⠀⠀⢳⡀⢦⠀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠏⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣯⠸⡄⠀⠀⠀⠀⠹⡄⠙⣇⠀⠀⢳⡀⢳⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⢠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡄⠹⡄⠀⠀⠀⠀⠘⣆⠘⣆⠀⠀⢳⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⣤⠀⠀⢇⣀⣠⠖⠋⠀⠀⠀⢀⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡄⠀⠀⠀⠀⢰⡏⢻⡄⠹⡄⠀⠀⠀⠀⠈⢧⡘⡄⠀⠀⢻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⢀⠇⠀⠀⠈⢹⠁⠀⠀⠀⠀⠀⡾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⠀⠀⠀⢀⣀⣼⣀⣀⣹⣄⡹⣄⠀⠀⠀⠀⠈⢷⣹⡄⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠘⠀⠀⠀⠀⣼⠀⠀⠀⠀⠀⢸⠃⠀⠀⠀⢠⠀⠀⠀⠀⠀⠀⠀⢸⣃⡠⠶⠛⠋⢹⠏⠁⠀⠉⠙⢏⠙⢶⠦⢤⣀⠀⠀⢷⣧⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⢰⠀⠀⠀⡸⡛⠀⠀⠀⠀⢸⡎⠀⠀⠀⢀⡾⠀⠀⠀⠀⠀⠀⢠⠇⠀⠀⠀⣰⢀⡟⠀⠀⠀⠀⠀⠈⢷⡀⠀⠀⠈⠙⠂⠈⢿⠀⠀⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⣴⢳⠇⠀⠀⠀⠀⣿⠃⠀⠀⢀⣸⡇⠀⠀⠀⠀⠀⣰⠋⠀⠀⠀⢰⠃⣼⢀⣀⣀⠀⠀⠀⠀⠀⠙⢦⡀⠀⠀⠀⠀⠘⣧⠀⢹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠰⠋⢠⠇⢸⠀⠀⠀⠀⠀⠈⠀⢀⡴⢟⣯⣷⡀⠀⠀⠀⣴⡟⠀⠀⠀⣰⠏⣰⠻⢿⣿⣷⣶⣶⣤⣤⣄⣀⠀⠙⣆⡀⠀⠀⠀⢹⡄⠈⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⢈⣾⡀⡎⠀⠀⠀⠀⠀⣷⢠⡞⠁⡾⢸⠀⢣⡀⠀⣼⠟⠀⠀⢠⣾⠃⣰⠁⢠⢿⣿⠿⠛⠛⠛⠛⠻⣿⣿⣿⣯⡳⢤⡀⠀⠘⡇⠀⣻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠎⠀⣠⠏⠙⠧⡇⠀⠀⠀⠀⠀⢹⠀⠀⡸⠃⣎⠀⠬⢧⡾⣿⠀⠀⢀⡾⠃⡼⠃⠀⠠⠛⠁⠀⢠⣾⣿⣷⢦⡀⠉⠻⣿⣿⣷⣿⣶⣂⠇⠀⠘⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⢀⠜⡿⠀⠀⠀⡇⠀⠀⠀⠀⠀⢸⠀⢠⠇⣠⢻⣿⣷⣯⡀⡏⠀⢀⡾⣡⠎⠀⠀⠀⠀⠀⠀⢀⣿⣥⣿⣿⡇⣱⠀⠀⠀⠻⣿⣷⣦⡉⡃⠀⠀⠹⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠎⣸⠃⠀⠀⢸⡇⠀⠀⠀⠀⠀⠈⡆⣿⣶⣿⣿⠟⣹⣿⣿⠁⣠⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⣾⡟⢃⣂⠀⠛⣿⡆⠀⠀⠀⠙⣿⣿⣿⣧⣤⣀⡀⢻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⢠⡇⠀⠀⡀⢸⢳⠀⠀⠀⠀⠀⠀⣿⣵⣿⣿⠏⢀⣿⣷⣧⡼⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⠷⣿⣿⡦⢤⣿⡏⠀⠀⠀⠀⢹⣿⣿⠏⠉⠉⠁⠁⢻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀
⢸⠀⠀⣰⠃⢸⢸⡄⠀⠀⠀⠀⠀⢸⣶⣿⣿⠀⢸⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣴⡙⠛⢀⠞⣿⠀⠀⠀⠀⠀⣼⠏⣾⠀⠀⠀⠀⠀⠈⠳⡄⠀⠀⠀⠀⠀⠀⠀⠀
⡜⠀⠀⡟⠀⠈⡏⣇⠀⠀⠀⠀⠰⡌⢧⠘⢿⠀⠸⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣏⠧⠀⢉⡶⠃⠀⠀⠀⠀⠀⠁⢰⠃⠀⠀⠀⠀⠀⠀⠀⡽⣦⠀⠀⠀⠀⠀⠀⠀
⡇⠀⢸⣇⠀⠀⡇⢸⡀⠀⠀⠀⠀⠹⣮⡷⠼⠇⠠⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠚⠋⠀⠀⠀⠀⢀⣀⠀⢠⠏⠀⠀⠀⠀⠀⠀⠀⡼⠁⠈⠳⣄⠀⠀⠀⠀⠀
⠃⠀⢸⢿⠀⠀⢳⠀⢇⠀⠀⣄⠀⠀⠹⣧⠀⢠⡤⠟⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⣠⠤⠤⠞⠋⠉⠁⢠⠏⠀⠀⢀⡼⢁⡄⠀⡼⠃⠀⠀⠀⠈⠳⣄⠀⠀⠀
⠀⠀⡇⢸⡄⠀⠘⡇⠘⣇⠀⢸⣄⠀⠀⠹⣧⠘⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠁⠀⠀⠀⡀⠀⠀⢀⣴⠋⠀⢀⣴⠋⢠⠎⢀⡞⠁⠀⠀⠀⠀⠀⢰⣯⠓⢦⡀
⡇⠀⡇⠀⣇⠀⠀⠀⠀⠘⣆⠀⢿⡷⢤⣬⡿⠀⠸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢷⣶⣖⣋⣡⠤⠞⢩⢇⡴⠋⠐⠋⠀⠀⠀⠀⠀⠀⣠⣿⠏⠀⢀⡼
⢷⠀⡇⠀⢸⡀⠀⠀⠀⠀⠘⢷⣌⢯⠙⡏⠀⠀⠀⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⢟⡟⣀⣴⠿⠋
⢸⠀⡇⠀⢀⢷⠀⠀⠀⠠⡀⠈⢿⣿⣮⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣀⠀⠀⠀⢀⣠⠞⠉⠀⠀⠀⠀⠀⠀⠀⠀⣠⠾⡿⢻⠛⡟⣥⠸⣆⠀
⠀⡇⢹⠤⠈⠈⣇⠀⠀⠀⠹⣆⠀⠈⢷⣷⠀⠀⠀⠀⠀⢰⠛⠓⠢⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⢶⣯⡭⠤⠤⠤⠤⠖⠂⠀⣀⡠⠖⢋⠁⣸⠁⡜⢰⢱⠁⠀⢸⢇
⠀⠹⡼⡄⠐⢀⠘⢧⠀⠀⠀⠈⠓⢦⣄⠙⢧⡀⠀⠀⠀⠘⡆⠀⠀⠈⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠓⠒⠒⠒⣶⠚⠉⠁⠀⣰⡏⢰⠇⢸⠇⣾⡎⠀⠀⠈⠆
⠀⠀⠽⣻⡠⡀⠁⠄⠳⢄⠀⠀⠀⢆⠈⠉⠳⣟⠓⠂⠀⠀⠙⢦⣀⣀⡽⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡼⠁⠀⠀⠀⣰⠏⢠⡇⠀⡞⢠⠃⠃⠀⠀⠀⠁
⠀⠀⡀⠙⢳⣄⣂⣀⠘⠀⠑⠦⣄⡈⠳⢤⣀⡈⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⡼⠃⢀⡞⠀⢰⠁⣼⠀⠠⠛⠇⠀⠀
⠠⠀⠀⠤⠀⠀⠄⠀⠤⠄⠀⠀⠀⠈⠛⢻⠛⠢⣤⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⡾⠁⠀⠀⠀⢠⠞⢀⣖⡞⠁⠀⠀⢸⠃⢀⠁⠠⠐⠀⠀
⢠⡀⠂⣀⠐⢀⡀⠃⣴⠀⠀⠀⠀⠀⠀⡏⠀⠀⢸⠉⣽⣝⠢⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣴⡿⣻⠟⠀⠀⠀⢀⡴⠋⢠⣮⡿⠁⠀⠀⢀⣾⣤⡃⠂⢀⠘⠁⠠
⠀⠠⠆⠀⠰⠀⠀⠆⡈⠀⠀⠀⠀⠀⣸⠁⠀⠀⡜⢠⡇⠀⠶⠈⢣⡀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣶⣾⣿⡟⠋⡴⠃⠀⠀⠀⠀⠈⢀⣴⡿⣻⠁⠀⠀⠀⡼⠀⢿⠀⠂⠀⠰⠀⠀
⠈⠁⡀⠉⢀⠈⠁⣄⡇⠀⠀⠀⠀⣤⡟⣰⠂⡸⠃⣼⡀⠉⣀⠉⢁⡉⢦⣀⣠⢤⡶⠖⣏⠹⣿⣿⣿⠟⠁⢀⡞⠀⠀⠀⠀⠀⠀⢀⣾⠋⢠⠇⠀⠀⠀⣸⠃⠀⠸⠁⡀⠈⢀⠀⠁
⠰⠂⠀⠖⠀⠀⠆⣀⡇⠀⠀⠀⠀⡆⣷⠃⠀⠃⡰⠃⡀⠆⠀⠲⢀⠀⠖⢀⠰⢆⡀⠶⣘⣼⢋⣻⡁⠀⢀⡞⠀⠀⠀⠀⠀⢀⣴⡿⠁⢀⠟⠀⠀⠀⢀⡇⢀⣠⠤⠆⠀⠰⠀⠀⠆
⢠⡌⠁⣤⠈⠠⡄⠛⠇⠀⠀⠀⠀⢻⡁⠀⢀⡼⣡⠈⠁⡄⢉⢤⡈⠁⣄⣉⣠⣌⢉⡤⠉⣡⡌⡟⢳⢀⡜⠀⠀⠀⠀⠀⣠⣾⡟⠀⠀⡼⠀⠀⠀⢀⣼⠗⠋⡁⠀⠀⠀⠀⠈⠁⡄
 */
@LoadImage("primogem.png")
public class Primogem extends BodySimple {
	 
	public Primogem(Layer l, int x, int y) {
		super(l, x, y, ZIndexes.ITEM);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean collision(IBodyPart myPart, IBodyPart otherPart, int dx, int dy) {
		
		if (otherPart instanceof BulletHero) {
			this.hit();
			otherPart.getParent().hit();
		}
		return false;
		
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return super.getX();
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return super.getY();
	}

	@Override
	public int getZIndex() {
		// TODO Auto-generated method stub
		return super.getZIndex();
	}

	@Override
	public int getWorldX() {
		// TODO Auto-generated method stub
		return super.getWorldX();
	}

	@Override
	public int getWorldY() {
		// TODO Auto-generated method stub
		return super.getWorldY();
	}

	@Override
	public void advance2() {
		// TODO Auto-generated method stub
		super.advance2();
	}

	@Override
	protected int getAdvanceDelay() {
		// TODO Auto-generated method stub
		return super.getAdvanceDelay();
	}

	@Override
	public void forceLocation(int x, int y) {
		// TODO Auto-generated method stub
		super.forceLocation(x, y);
	}

	@Override
	public void forceLocation(Direction d) {
		// TODO Auto-generated method stub
		super.forceLocation(d);
	}

	@Override
	public boolean move(Direction d) {
		// TODO Auto-generated method stub
		return super.move(d);
	}

	@Override
	public boolean move(int dx, int dy) {
		// TODO Auto-generated method stub
		return super.move(dx, dy);
	}

	@Override
	public boolean isMarkedForRemoval() {
		// TODO Auto-generated method stub
		return super.isMarkedForRemoval();
	}

	@Override
	public void markForRemoval() {
		// TODO Auto-generated method stub
		super.markForRemoval();
	}

	@Override
	public void clearRemovalMark() {
		// TODO Auto-generated method stub
		super.clearRemovalMark();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public String getPrettyLocation() {
		// TODO Auto-generated method stub
		return super.getPrettyLocation();
	}

	@Override
	public int getHealthMax() {
		return 4;
	}

	@Override
	public int getHealthCurrent() {
		// TODO Auto-generated method stub
		return super.getHealthCurrent();
	}

	@Override
	public boolean hit() {
		// TODO Auto-generated method stub
		return super.hit();
	}

	@Override
	public Direction getHealthLocation() {
		// TODO Auto-generated method stub
		return super.getHealthLocation();
	}

	@Override
	public int getHealthBarWidth() {
		// TODO Auto-generated method stub
		return super.getHealthBarWidth();
	}

	@Override
	public boolean shouldShowFullHealthBar() {
		// TODO Auto-generated method stub
		return super.shouldShowFullHealthBar();
	}

	@Override
	public boolean shouldShowHealthBorder() {
		// TODO Auto-generated method stub
		return super.shouldShowHealthBorder();
	}

	@Override
	public void setInGame(boolean b) {
		// TODO Auto-generated method stub
		super.setInGame(b);
	}

	@Override
	public boolean isInGame() {
		// TODO Auto-generated method stub
		return super.isInGame();
	}
	
	
}
