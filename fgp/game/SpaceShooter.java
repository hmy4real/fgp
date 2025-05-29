package fgp.game;

import java.awt.Color;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.swing.JFrame;

import fgp.engine.GameEngine;
import fgp.engine.GameMode;
import fgp.engine.ISupportsImage;
import fgp.engine.Layer;
import fgp.engine.LoadImage.ResizeMode;
import fgp.engine.Sprite;
import fgp.engine.Waiter;
import fgp.engine.bodies.Body;
import fgp.engine.bodies.IBodyPart;
import fgp.engine.inputs.Notification;
import fgp.game.layers.BackdropLayer;
import fgp.game.layers.BackgroundLayer;
import fgp.game.layers.CharactersLayer;
import fgp.game.layers.StatusLayer;
import fgp.ui.DisplayItem;
import fgp.ui.FrmLauncher;
import fgp.ui.HorizontalAlignment;

/**⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⢶⣤⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⠾⠋⣡⡄⢷⣻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣠⣤⣤⣤⡾⠛⠁⢀⡼⠁⠀⠈⣯⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⡟⠀⠀⡀⠺⢷⡄⠀⠀⠸⡿⣧⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⡷⣤⡾⢻⡟⣸⣿⠶⠀⣠⣿⣿⣿⣿⣿⣶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⠀⠹⣧⣠⣾⡿⠋⢀⡞⣵⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⡿⠀⢀⣿⠋⠉⠀⣠⣯⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⡇⠀⣸⠃⠀⢀⣾⣵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣷⣦⣥⣀⣴⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⣠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣟⠛⣿⡿⠻⢿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣳⡋⠻⣿⠞⣻⡎⢻⣿⣿⣿⣿⣿⣿⣦⡀⠀⠀⣞⣉⠆⠀
⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⡿⠁⡈⢉⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢉⡿⠷⠄⠋⣴⣿⣶⢿⣿⣿⣿⠿⢿⣏⣘⣷⡴⢊⣷⣋⣤⡀
⠀⠀⠀⠀⠀⠀⠀⠹⣿⡿⠛⠋⣹⠏⢁⡿⠉⡉⠙⠛⠛⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠙⠦⣶⡟⢸⣦⣆⣿⣠⣿⣿⣿⣦⣏⠛⠼⣟⢻⣏⠉⠙⠛⠁
⠀⠀⠀⠀⠀⠀⠀⢸⡟⠀⠀⡼⠃⢀⣾⡟⠀⠙⢦⣀⠀⠀⠀⠉⠛⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣛⣷⣾⣿⣦⣿⡿⠿⢛⣻⡟⠧⣆⣶⣿⣿⣿⡄⠀⠀⠀
⠀⠀⠀⠀⠀⠀⢀⡟⠀⠀⡾⠁⢀⣼⠁⠀⢤⠀⠀⠀⡖⣸⡗⠘⠲⣦⠀⠙⡻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⣿⠿⣿⣷⡶⣾⣿⣿⣷⣾⣿⣵⣿⣿⣿⡇⠀⠀⠀
⠀⠀⠀⠀⠀⢀⣼⠃⠀⣼⣇⡷⣟⣸⠀⠀⠈⠙⠒⠀⢡⡏⣧⠀⠀⢸⣿⠀⠀⠘⣿⢻⣿⣿⣿⣿⣟⣿⠧⠙⢛⣿⣿⣿⣾⣿⣿⣿⣉⣿⣿⣿⡿⠟⠃⠀⠀⠀
⠀⠀⢀⣠⡴⠛⠁⠀⢠⣿⠋⠀⠈⢻⡄⠀⠀⣠⠀⠀⣼⠀⢹⡄⠀⠀⢿⠀⠀⠀⠸⡆⠛⢿⣿⣿⣄⣛⣶⢠⣌⣿⢹⣿⣿⣿⣿⣿⣿⡿⠿⣿⡆⠀⠀⠀⠀⠀
⠀⣿⡉⠁⠀⣠⡶⠀⢸⣿⣿⣷⣝⠺⡇⠀⠀⣿⠀⠀⣏⣀⣀⣷⣀⣀⠘⡇⠀⠀⠀⣿⠀⠀⣻⣿⡿⣿⣝⡋⣿⣿⣾⣿⣿⣿⣿⠛⠿⠃⠀⠀⠀⠀⠀⠀⠀⠀
⠀⣈⣿⡷⠟⢹⡇⠀⢸⡟⠁⣿⣿⣧⣿⠀⠀⢻⡆⠀⡏⠀⣀⣈⢿⡉⠉⢻⡄⠀⢠⣿⠀⠀⢻⣿⣤⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠈⢿⣦⣤⣤⣬⣿⡀⠘⣇⢸⣿⣀⣿⠘⣇⠀⠈⣿⢦⣽⣉⣡⣤⣴⣿⣦⣀⢷⣤⣿⡇⠀⠀⠀⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⢟⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠈⠉⠉⠉⠘⢿⣦⣽⡄⢧⡽⠃⠀⠘⢧⡀⠹⣤⡶⢿⣿⡿⠟⣟⠻⣿⣿⣿⡿⠀⠀⠀⣤⣤⣿⣿⣿⢻⡏⠙⣿⡿⠃⣼⣏⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠘⣧⠀⠀⠀⠀⡄⠀⠀⠀⠉⠙⠛⠁⢸⡾⢦⣤⠟⣴⠃⢹⣿⠃⠀⠀⠀⢛⣦⣍⠛⠳⠾⣿⣴⠟⠀⣰⣿⢿⣯⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⣨⣷⣄⠀⠀⢶⠦⠤⢄⡀⠀⠀⠀⠘⠳⠦⢿⣋⣁⣤⠟⠁⠀⠀⠀⢀⣾⣿⣿⡷⢦⣄⠀⠀⠀⠀⣿⣛⣠⣿⣿⣷⣄⡀⠀⠀⠀⠀⠀⠀
⠀⢠⡤⣄⣠⣤⣶⠟⠋⠀⠉⢳⣦⣘⡆⠀⣰⠃⠀⠀⠀⠀⠀⢠⡟⠁⠈⢻⣆⣀⣀⣤⠞⢋⣙⣻⣿⡃⠀⢸⠇⠀⣀⡀⠈⠙⠻⣿⣿⣿⣿⣿⣷⣤⣀⡀⠀⠀
⠀⠸⣿⣿⣿⡅⠀⠀⠀⢀⣴⡟⠁⠛⠛⣶⣷⣦⣤⣤⣤⣤⣤⣼⣷⠀⠀⠀⣿⣿⡿⠛⠉⠉⠀⠀⠉⠙⢳⡏⠀⡴⠛⢿⣿⣶⢤⣈⡙⢿⣿⣿⣿⣿⠿⠃⠀⠀
⠀⠀⠙⢿⣿⣿⣦⣤⣴⠟⠋⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣳⣤⡾⣭⣷⣄⠀⠀⠀⠀⣀⣀⣠⡼⢀⣾⣧⣄⣀⣉⣛⣶⡰⠛⠛⠉⠉⠉⠀⠀⠀⠀⠀
⠀⣠⣤⣤⣬⠿⠟⠋⠁⠀⢀⣤⢠⣿⠿⢟⣿⡟⠛⠛⠉⢡⣿⡟⠋⣹⡇⠘⡿⣘⣽⠉⣾⠿⠛⢛⡿⢿⣶⠿⠿⠿⠛⠋⠉⠉⠁⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠹⢷⣧⣼⣤⣤⣤⣴⣶⣟⣡⣾⣿⠀⢸⣿⣧⡀⠀⠀⢸⡿⢠⣾⣿⠍⠛⠛⠷⠏⢀⣿⡴⠀⣿⠃⠀⠹⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠉⠀⠀⠈⠉⠉⠙⣿⣿⣿⠏⠉⠈⠛⠛⠛⢻⣇⠀⠿⠷⠤⣀⡀⠀⠀⣼⣿⠁⢠⡇⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣏⠀⠀⠀⠀⠀⠀⠈⢿⣷⣤⣘⠻⠚⢉⣠⣾⣿⠇⠀⣼⠃⠀⠀⣼⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⢶⣤⣄⣀⣀⣠⣤⠴⠻⣿⣿⣿⣿⣿⣿⠟⢁⣠⠞⢁⣀⣤⠾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠁⠀⠀⠀⠀⢽⣟⣉⣉⣤⠶⠛⠛⠛⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
Hu Tao is here to bless no Bugs no Errors no Exceptions :D

@apiNote
@version 1.0.0
 */
public class SpaceShooter extends GameEngine {

	public static void main(String[] args) throws Exception {
		FrmLauncher.launch(new SpaceShooter());
	}
	
	public SpaceShooter() throws Exception {
		super();
		super.setDarkMode(true);
	}

	@Override
	public int getTargetFps() {
		return 60;
	}

	@Override
	public String getGameTitle() {
		// TODO Auto-generated method stub
		return "Space Shooter";
	}

	@Override
	protected int getLevels() {
		return 5;
	}

	@Override
	protected String getImagesFolder() {
		return "images";
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected boolean shouldCreateBackdropLayer() {
		// TODO Auto-generated method stub
		return super.shouldCreateBackdropLayer();
	}

	@Override
	protected boolean shouldCreateStatusLayer() {
		// TODO Auto-generated method stub
		return super.shouldCreateStatusLayer();
	}

	@Override
	protected void addLayer(Layer l) {
		// TODO Auto-generated method stub
		super.addLayer(l);
	}

	@Override
	public int getXWorldSize() {
		// TODO Auto-generated method stub
		return super.getXWorldSize();
	}

	@Override
	public int getYWorldSize() {
		// TODO Auto-generated method stub
		return super.getYWorldSize();
	}

	@Override
	public Sprite getSprite(ISupportsImage b) {
		// TODO Auto-generated method stub
		return super.getSprite(b);
	}

	@Override
	public void checkGameOver() {
		// TODO Auto-generated method stub
		super.checkGameOver();
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		super.gameOver();
	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub
		super.newGame();
	}

	@Override
	public void restartLevel() {
		// TODO Auto-generated method stub
		super.restartLevel();
	}

	@Override
	public void nextLevel() {
		// TODO Auto-generated method stub
		super.nextLevel();
	}

	@Override
	public void changeLevel(int level) {
		// TODO Auto-generated method stub
		super.changeLevel(level);
	}

	@Override
	public int getCurrentLevel() {
		// TODO Auto-generated method stub
		return super.getCurrentLevel();
	}

	@Override
	public List<Layer> getLayers() {
		// TODO Auto-generated method stub
		return super.getLayers();
	}

	@Override
	public int convertXGridToPixel(int x) {
		// TODO Auto-generated method stub
		return super.convertXGridToPixel(x);
	}

	@Override
	public int convertYGridToPixel(int y) {
		// TODO Auto-generated method stub
		return super.convertYGridToPixel(y);
	}

	@Override
	public int convertXPixelToGrid(int x) {
		// TODO Auto-generated method stub
		return super.convertXPixelToGrid(x);
	}

	@Override
	public int convertYPixelToGrid(int y) {
		// TODO Auto-generated method stub
		return super.convertYPixelToGrid(y);
	}

	@Override
	public void advance() {
		// TODO Auto-generated method stub
		super.advance();
	}

	@Override
	public void addWaiter(Waiter waiter) {
		// TODO Auto-generated method stub
		super.addWaiter(waiter);
	}

	@Override
	protected int getAdvanceDelay() {
		// TODO Auto-generated method stub
		return super.getAdvanceDelay();
	}

	@Override
	protected void advance2() {
		// TODO Auto-generated method stub
		super.advance2();
	}

	@Override
	public List<IBodyPart> search(int i, int j) {
		// TODO Auto-generated method stub
		return super.search(i, j);
	}

	@Override
	public <T extends IBodyPart> List<T> searchParts(Class<T> cls) {
		// TODO Auto-generated method stub
		return super.searchParts(cls);
	}

	@Override
	public <T extends Body<?>> List<T> searchBodies(Class<T> cls) {
		// TODO Auto-generated method stub
		return super.searchBodies(cls);
	}

	@Override
	public List<IBodyPart> searchParts(Predicate<IBodyPart> p) {
		// TODO Auto-generated method stub
		return super.searchParts(p);
	}

	@Override
	public Stream<IBodyPart> findParts(Predicate<IBodyPart> p) {
		// TODO Auto-generated method stub
		return super.findParts(p);
	}

	@Override
	public List<Body<?>> searchBodies(Predicate<Body<?>> p) {
		// TODO Auto-generated method stub
		return super.searchBodies(p);
	}

	@Override
	public Stream<Body<?>> findBodies(Predicate<Body<?>> p) {
		// TODO Auto-generated method stub
		return super.findBodies(p);
	}

	@Override
	protected boolean addSprite(String id, int i, String folder, String filename, ResizeMode resize,
			boolean showErrors) {
		// TODO Auto-generated method stub
		return super.addSprite(id, i, folder, filename, resize, showErrors);
	}

	@Override
	public void remove(Collection<Body<?>> c) {
		// TODO Auto-generated method stub
		super.remove(c);
	}

	@Override
	public void remove(Body<?> b) {
		// TODO Auto-generated method stub
		super.remove(b);
	}

	@Override
	public void notify(Notification notification) {
		// TODO Auto-generated method stub
		super.notify(notification);
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return super.getScore();
	}

	@Override
	public void addScore(int points) {
		// TODO Auto-generated method stub
		super.addScore(points);
	}

	@Override
	public int getLives() {
		// TODO Auto-generated method stub
		return super.getLives();
	}

	@Override
	public void lifeLost() {
		// TODO Auto-generated method stub
		super.lifeLost();
	}

	@Override
	public GameMode getGameMode() {
		// TODO Auto-generated method stub
		return super.getGameMode();
	}

	@Override
	public void setGameMode(GameMode gameMode) {
		// TODO Auto-generated method stub
		super.setGameMode(gameMode);
	}

	@Override
	public void setDebugEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		super.setDebugEnabled(enabled);
	}

	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return super.isDebugEnabled();
	}

	@Override
	public int getXOffset() {
		// TODO Auto-generated method stub
		return super.getXOffset();
	}

	@Override
	public int getYOffset() {
		// TODO Auto-generated method stub
		return super.getYOffset();
	}

	@Override
	public void moveOffset(int dx, int dy) {
		// TODO Auto-generated method stub
		super.moveOffset(dx, dy);
	}

	@Override
	public void setOffset(int x, int y) {
		// TODO Auto-generated method stub
		super.setOffset(x, y);
	}

	@Override
	public List<String> getBodyTypeNames() {
		// TODO Auto-generated method stub
		return super.getBodyTypeNames();
	}

	@Override
	public void addDisplayItem(HorizontalAlignment align, DisplayItem<?> value) {
		// TODO Auto-generated method stub
		super.addDisplayItem(align, value);
	}

	@Override
	public List<DisplayItem<?>> getDisplayItemList(HorizontalAlignment align) {
		// TODO Auto-generated method stub
		return super.getDisplayItemList(align);
	}

	@Override
	public void setDarkMode(boolean dark) {
		// TODO Auto-generated method stub
		super.setDarkMode(dark);
	}

	@Override
	public Color getDefaultBack() {
		// TODO Auto-generated method stub
		return super.getDefaultBack();
	}

	@Override
	public Color getDefaultFore() {
		// TODO Auto-generated method stub
		return super.getDefaultFore();
	}

	@Override
	public Color getDefaultShadow() {
		// TODO Auto-generated method stub
		return super.getDefaultShadow();
	}

	@Override
	public JFrame createDebugFrame() {
		// TODO Auto-generated method stub
		return super.createDebugFrame();
	}

	@Override
	public int getSpriteCount(Class<?> cls) {
		// TODO Auto-generated method stub
		return super.getSpriteCount(cls);
	}

	@Override
	public void createLayers() {
		//addLayer(new BackdropLayer());
		addLayer(new BackgroundLayer());
		addLayer(new CharactersLayer());
		//addLayer(new StatusLayer());
	}
	
	/**
	 * @apiNote
	 * 32 : 20 = 16 : 10
	 */
	@Override
	public int getXViewSize() {
		return 32;
	}

	@Override
	public int getYViewSize() {
		return 20;
	}

	@Override
	public int getTileSize() {
		return 32;
	}

	@Override
	protected boolean checkGameOver2() {
		// TODO Auto-generated method stub
		return false;
	}

}
