package map;

import UI.Images;
import UI.Rectangle;
import character.Npc;
import character.Receptionist;
import character.Visitor;
import javafx.scene.canvas.GraphicsContext;

public class MapWelcome extends Map{
	private static final int MAXRECEPTIONIST = 10;
	private int numberOfReceptionist;
	
	public MapWelcome(){
		super();
		super.setWarpUp(new Rectangle(Images.WARP, 190, 0));
		super.setBackground(Images.FLOOR);
		super.addStruct(new Rectangle(Images.TREE, 140, 442));
		super.addStruct(new Rectangle(Images.TREE, 288, 442));
		super.addStruct(new Rectangle(Images.SOFA, 431, 118));
	}
	
	public boolean addVisitor() {
		for(Npc npc: super.getNpcList()) {
			if(npc instanceof Receptionist) {
				Receptionist recep = (Receptionist) npc;
				if(!recep.isBusy()) {
					super.getNpcList().add(new Visitor(Images.PLAYERL, Images.PLAYERR, Images.PLAYERU, Images.PLAYERD, this));
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean addReceptionist(){
		if(this.numberOfReceptionist == this.MAXRECEPTIONIST) {
			return false;
//			throw new Exception or may be pop up message ("Can't add more receptionist");
		}
		double x = 40;
		double y = 250;
		this.numberOfReceptionist += 1;
		if(this.numberOfReceptionist%2 == 0) {
			y = 250 + this.numberOfReceptionist/2 * 32;				
		}
		else{
			y = 250 - this.numberOfReceptionist/2 * 32;				
		}
		Npc receptionist = new Receptionist(Images.PLAYERL, Images.PLAYERR, Images.PLAYERU, Images.PLAYERD, this, x, y);
		super.getNpcList().add(receptionist);
		return true;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		super.getBackground().render(gc);
		super.getWarpUp().render(gc);
		for(Rectangle r: super.getStructList()) {
			r.render(gc);
		}
		for(Npc npc: super.getNpcList()) {
			npc.render(gc);
		}
	}
	
	@Override
	public void setRoom(int position, int level) {}
}
