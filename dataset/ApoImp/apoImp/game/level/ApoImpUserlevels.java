package apoImp.game.level;

import java.util.ArrayList;

import apoImp.game.ApoImpPanel;

public class ApoImpUserlevels {
	
	private ArrayList<Integer> sortByUpload;
	
	private ApoImpUserlevelsLoad userlevels;
	
	protected final ApoImpPanel game;
	
	public ApoImpUserlevels(final ApoImpPanel game) {
		this.game = game;
		this.sortByUpload = new ArrayList<Integer>();
	}
	
	public void loadUserlevels() {
		try {
			this.userlevels = ApoImpUserlevelsLoad.getInstance();
			this.userlevels.load();
			
			if (this.userlevels.getLevels().size() > 0) {
				this.sortByUpload();
				
				ApoImpLevel.editorLevels = this.getAllLevelsSorted();
				
				this.game.setUserlevelsVisible();
			}
		} catch (Exception ex) {
			this.sortByUpload = new ArrayList<Integer>();
		}
	}
	
	public int getMaxLevel() {
		return this.userlevels.getLevels().size();
	}
	
	private String[] getAllLevelsSorted() {
		if (this.userlevels.getLevels().size() <= 0) {
			return null;
		}
		int size = this.sortByUpload.size();
		String[] levels = new String[size];
		for (int level = 0; level < levels.length; level++) {
			String curLevel = this.userlevels.getLevels().get(this.sortByUpload.get(level));
			levels[level] = curLevel;
		}
		return levels;
	}
	
	private void sortByUpload() {
		this.sortByUpload.clear();
		for (int i = 0; i < this.userlevels.getLevels().size(); i++) {
			if (!ApoImpLevel.isIn(this.userlevels.getLevels().get(i))) {
				float time = this.userlevels.getTimes().get(i);
				boolean bAdd = false;
				for (int k = 0; k < this.sortByUpload.size(); k++) {
					float sortTime = this.userlevels.getTimes().get(this.sortByUpload.get(k));
					if (time > sortTime) {
						this.sortByUpload.add(k, i);
						bAdd = true;
						break;
					}
				}
				if (!bAdd) {
					this.sortByUpload.add(i);
				}
			}
		}
	}

	public ArrayList<Integer> getSortByUpload() {
		return this.sortByUpload;
	}
	
	public boolean addLevel(String level) {
		for (int i = 0; i < this.userlevels.getLevels().size(); i++) {
			if (this.userlevels.getLevels().get(i).equals(level)) {
				return false;
			}
		}
		return ApoImpUserlevelsLoad.getInstance().save(level);
	}
	
	public float getStars(int level) {
		if (this.userlevels.getCount().get(this.sortByUpload.get(level)) <= 0) {
			return 0;
		}
		return (float)((float)this.userlevels.getAll().get(this.sortByUpload.get(level)) / (float)this.userlevels.getCount().get(this.sortByUpload.get(level)));
	}
	
	public String getStatistics(int level) {		
		int[] values = new int[4];
		values[0] = this.userlevels.getFun().get(this.sortByUpload.get(level));
		values[1] = this.userlevels.getCreative().get(this.sortByUpload.get(level));
		values[2] = this.userlevels.getEasy().get(this.sortByUpload.get(level));
		values[3] = this.userlevels.getHard().get(this.sortByUpload.get(level));
		
		int max = this.getHighest(-1, values);
		int maxTwo = this.getHighest(max, values);
		String result = "";
		for (int i = 0; i < values.length; i++) {
			if ((i != max) && (i != maxTwo)) {
				result += "0";
			} else {
				if (values[i] > 0) {
					result += "1";
				} else {
					result += "0";
				}
			}
		}

		return result;
	}
	
	private int getHighest(int not, int[] values) {
		int curValue = 0;
		if (not == 0) {
			curValue = 1;
		}
		int max = values[curValue];
		for (int i = 0; i < values.length; i++) {
			if ((max < values[i]) && (i != not)) {
				max = values[i];
				curValue = i;
			}
		}
		return curValue;
	}
	
	public int getID(int level) {
		return this.userlevels.getId().get(this.sortByUpload.get(level));
	}
}
