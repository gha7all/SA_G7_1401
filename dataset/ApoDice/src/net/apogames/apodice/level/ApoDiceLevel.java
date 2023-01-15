package net.apogames.apodice.level;

public class ApoDiceLevel {

	/**
	 * 0 = empty
	 * 1 = goal
	 * 2 = dice with no moves
	 * 3 = dice with 1 moves
	 * 4 = dice with 2 moves
	 * 5 = dice with 3 moves
	 * 6 = dice with 4 moves
	 * 7 = dice with 5 moves
	 * 8 = dice with 6 moves
	 * a = dice with no moves and goal down
	 * b = dice with 1 moves and goal down
	 * c = dice with 2 moves and goal down
	 * d = dice with 3 moves and goal down
	 * e = dice with 4 moves and goal down
	 * f = dice with 5 moves and goal down
	 * g = dice with 6 moves and goal down
	 */
	private static final String[] levelsString = new String[] {	
		"00000000"+
		"00000000"+
		"04000050"+
		"00100000"+
		"00000100"+
		"00310000"+
		"00000000"+
		"00000000",
		
		"0000000000000000000000000040000000311000000000000000000000000000",
		
		"00000000" +
		"00000000" +
		"00000000" +
		"00040000" +
		"00103000" +
		"00010000" +
		"00000000" +
		"00000000",
		
		"0000000000000000000000000013100000000000004150000000000000000000",
		
		"00000000"+
		"00000000"+
		"00031000"+
		"00041000"+
		"00015000"+
		"00016000"+
		"00000000"+
		"00000000",
		
		"00000000"+
		"00000000"+
		"00000000"+
		"00041400"+
		"00000000"+
		"00015100"+
		"00000000"+
		"00000000",
		
		"00000000"+
		"00000000"+
		"00005000"+
		"00611000"+
		"00011400"+
		"00030000"+
		"00000000"+
		"00000000",
		
		"00000000"+
		"00000000"+
		"00014100"+
		"00004000"+
		"00311130"+
		"00005000"+
		"00000000"+
		"00000000",
		
		"00000000"+
		"00000000"+
		"00016100"+
		"00041000"+
		"00001400"+
		"00006000"+
		"00000000"+
		"00000000",
		
		"00500300"+
		"00100100"+
		"00064000"+
		"00011000"+
		"04000040"+
		"03111130"+
		"00000000"+
		"00000000",
		
		"0000000000000000000000000001100000311300043113400000000000000000",
		
		"0000000000000000000110000031150000400400005003000001100000000000",
		
		"00000000"+
		"00503050"+
		"00011100"+
		"00310130"+
		"00011100"+
		"00503050"+
		"00000000"+
		"00000000",
		
		"00000000"+
		"00000000"+
		"00000000"+
		"00010500"+
		"00061000"+
		"00010500"+
		"00000000"+
		"00000000",
		
		"00000000000000000005000000040000011b1100000400000005000000000000",
		
		"00000000"+
		"00000000"+
		"00054000"+
		"00411500"+
		"00145100"+
		"00011000"+
		"00000000"+
		"00000000",
		
		"0000000000000000000300000031100000010140000015000000400000000000",
		
		"0000000000000000004131000010040000300100001415000000000000000000",
		
		"0000000000000000000400000031300004111400000100000000000000000000",
		
		"0000000000431000003110000011330003311000001130000013400000000000",
		
		"0000000000000000003b0c40000101000004430000b000b00001110000000000",
		
		"0000000000014000001315000131514004151310005131000004100000000000",
		
		"0000000000000000041814000161610008101800016161000418140000000000",
		
		"0000000000140000005100000013150000416100000014000000510000000000",
		
		"00000000"+
		"00013400"+
		"00151540"+
		"00111440"+
		"00113050"+
		"00011300"+
		"00000000"+
		"00000000",
		
		"00000000"+
		"00413160"+
		"00100010"+
		"00361030"+
		"00100010"+
		"00613140"+
		"00000000"+
		"00000000",
		
		"00000000"+
		"00000000"+
		"00050100"+
		"00001500"+
		"00015000"+
		"00100000"+
		"00000000"+
		"00005000",
		
		"00000000"+
		"01030000"+
		"00500100"+
		"00107000"+
		"06001000"+
		"00010400"+
		"00000000"+
		"00000000",
		
		"00000000"+
		"00000000"+
		"10401030"+
		"0d0e0d00"+
		"30104010"+
		"00000000"+
		"00000000"+
		"00000000",
		
		"00040000"+
		"00000000"+
		"00d1d000"+
		"301b1030"+
		"00e1e000"+
		"00000000"+
		"00040000"+
		"00000000",
	};
	
	public static String[] editorLevels = null;
	
	public static final String getLevel(int level) {
		if ((level < 0) || (level >= levelsString.length)) {
			return null;
		}
		return levelsString[level];
	}
	
	public static final int MAX_LEVELS = levelsString.length;
	
	public static boolean isIn(String level) {
		for (int i = 0; i < levelsString.length; i++) {
			if (level.equals(levelsString[i])) {
				return true;
			}
		}
		return false;
	}
}
