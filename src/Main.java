import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args){
//		(new UseFile()).changeMap();
		(new UseFile()).convertEvent();
	}
}

class UseFile{
	int row, col;
	int[][] map;
	final static String fileUrl = "C:/Work/git/MapEditor/マップエディタ2/mapEasy.map";
	final static String eventUrl = "C:/Work/git/MapEditor/マップエディタ2/mapEasy.evt";
	final static String backUrl = "C:/Work/git/MapEditor/マップエディタ2/mapEasy.map.back";
	
	final static String saveMapUrl = "C:/Work/Android/map/map_easy01.map";
	final static String saveEventUrl = "C:/Work/Android/map/event_easy01.evt";
	
	final static int JUMP = 12*16 + 1;
	final static int TOGE = 12*16 + 2;
	final static int KURI = 12*16 + 3;
	public void convertEvent(){
		loadMap(new File(fileUrl));
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				String line = "";
				switch(map[i][j]){
				case JUMP:
					line += "SPRING,";
					break;
				case TOGE:
					line += "NEEDLE,";
					break;
				case KURI:
					line += "ENEMY,";
					break;
				default:
					continue;
				}
				line += j+","+i+"\n";
				sb.append(line);
				map[i][j] = 0;
			}
		}
		saveEvent(new File(saveEventUrl), sb.toString());
		saveMap(new File(saveMapUrl));
	}
	
	private void loadEvent(File file){
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveEvent(File file, String buffer){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(buffer);
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changeMap(){
		loadMap(new File(fileUrl));
		saveMap(new File(backUrl));
		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				map[i][j] = 0;
			}
		}
		for(int i=0; i<100; i++){

			for(int j=29; j>28; j--){
				map[j][i] = 17;
			}
			map[28][i] = 1;
		}
		
		for(int i=100; i<col; i++){
			for(int j=29; j>20; j--){
				map[j][i] = 17;
			}
			map[20][i] = 1;
		}
		saveMap(new File(fileUrl));
	}
	
    public void loadMap(File mapFile) {
        try {
            FileInputStream in = new FileInputStream(mapFile);

            // 行数・列数を読み込む
            row = in.read();
            col = (in.read()<<8)|in.read();

            // マップを読み込む
            map = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    map[i][j] = in.read();
                }
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    public void saveMap(File mapFile) {
        try {
            // マップはバイナリファイルとする
            // マップの1マスを1バイトで表現
            FileOutputStream out = new FileOutputStream(mapFile);

            // 行数・列数を書き込む
            out.write(row);
            out.write(col>>8);
            out.write((byte)col);

            // マップを書き込む
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    out.write(map[i][j]);
                }
            }

            out.close();

            System.out.println(mapFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
