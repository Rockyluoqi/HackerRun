package RunningHacker_Java;

public class Run {

	public Run() {
		// TODO Auto-generated constructor stub
		
	}
	static welcome welcome = new welcome();
	static level1 level1 = new level1();
	static level2 level2 = new level2();
	static level3 level3 = new level3();
	static level4 level4 = new level4();
	static level5 level5 = new level5();
	static level6 level6 = new level6();
	static level7 level7 = new level7();
	static level8 level8 = new level8();
	static level9 level9 = new level9();
	static level10 level10 = new level10();
	static Level11 level11 = new Level11();
	static Level12 level12 = new Level12();
	static Out out = new Out();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int level_number=0;
		System.out.println("欢迎来到·奔跑吧黑客·游戏系统");
		System.out.println("输入 \"h (help)获得帮助\"");

		while(true){
			switch (level_number) {
			case -1:
				System.out.println("您已退出游戏，祝生活开心");
				return;
	        case 0:
	            level_number=welcome.welcome();
	            break;
	        case 1:
	            level_number=level1.level1();
	            break;
	        case 2:
	            level_number=level2.level2();
	            break;
	        case 3:
	            level_number=level3.level3();
	            break;
	        case 4:
	            level_number=level4.level4();
	            break;
	        case 5:
	            level_number=level5.level5();
	            break;
	        case 6:
	            level_number=level6.level6();
	            break;
	        case 7:
	            level_number=level7.level7();
	            break;
	        case 8:
	            level_number=level8.level8();
	            break;
	        case 9:
	            level_number=level9.level9();
	            break;
	        case 10:
	            level_number=level10.level10();
	            break;
	        case 11:
	            level_number=level11.level11();
	            break;
	        case 12:
	            level_number=level12.level12();
	            break;
	        default:
	            out.out_err();
	            return;
			}
		}
	}

}
