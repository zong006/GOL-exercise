import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        String fileName = args[0];
        int numIter = Integer.parseInt(args[1]);

        File file = new File("." + File.separator + "data" + File.separator + fileName);

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String lineRead = "";

        int gridXSize = 0;
        int gridYSize = 0;
        int startX = 0;
        int startY = 0;
        boolean readData = false;
        ArrayList<String[]> initialConfig = new ArrayList<>();

        while ((lineRead = br.readLine())!=null){
            if (!readData){
                String[] lineContent = lineRead.toLowerCase().split(" ");
                if (lineRead.contains("#")){
                    continue;
                }
                else {
                    if (lineContent[0].equals("grid")){
                        gridXSize = Integer.parseInt(lineContent[1])+2;
                        gridYSize = Integer.parseInt(lineContent[2])+2;
                    }
                    
                    else if (lineContent[0].equals("start")){
                        startX = Integer.parseInt(lineContent[1])+1;
                        startY = Integer.parseInt(lineContent[2])+1;
                        }
                    else if (lineContent[0].equals("data")){
                        readData = true;
                    }
                }
            }
            else {
                String[] position = new String[lineRead.length()];
                position = lineRead.split("");
                initialConfig.add(position);
            }
        }
        
        // initialize a matrix of zeroes, size girdX by girdY.
        // from the starting position, set the initial config
        boolean[][] env = new boolean[gridXSize][gridYSize];
        boolean[][] envCopy = new boolean[gridXSize][gridYSize];

        for (int i = 0 ; i < initialConfig.size() ; i++){
            for (int j = 0 ; j <  initialConfig.get(i).length ; j ++){
                if (initialConfig.get(i)[j].equals("*")){
                    env[startX+i][startY+j] = true;
                    envCopy[startX+i][startY+j] = true;
                }
            }
        }

        printGrid(gridXSize, gridYSize, env);
        
        for (int k = 0 ; k < numIter ; k++){
            envCopy = copyMatrix(env);
            for (int i = 1 ; i < gridXSize-1 ; i++){
                for (int j = 1 ; j < gridYSize-1 ; j++){
            
                    int activeCounter = countActiveCells(i, j, envCopy);
                
                    if (envCopy[i][j]){
                        if (activeCounter < 2 || activeCounter>3){
                            env[i][j] = false;
                        }
                        else if (activeCounter==3 || activeCounter==2){
                            continue;
                        }
                    }
                    else {
                        if (activeCounter==3){
                            env[i][j]=true;
                        }
                    }
                }
            }
            
            System.out.println("===================================");
            printGrid(gridXSize, gridYSize, env);
        }
    }

    public static int countActiveCells(int i, int j, boolean[][] envCopy){
        // check cells around env[i][j]
        int activeCounter = 0;
        // first, count the cells around env[i][j] that are active, excl env[i][j] itself
        for (int m = i-1 ; m < i+2 ; m++){
            for (int n = j-1 ; n < j+2 ; n++){
                if (!(m==i && n==j)){
                    
                    // System.out.printf("%d, %d,  %b \n ", m ,n , envCopy[m][n]);
                    if (envCopy[m][n]==true){
                            activeCounter += 1;
                    }
                }
            }
        }
        return activeCounter;
    }

    public static boolean[][] copyMatrix(boolean[][] mat){

        int rows = mat.length;
        int cols = mat[0].length;
        boolean[][] copiedMat = new boolean[rows][cols];

        for (int i = 0 ; i < rows ; i ++){
            for (int j = 0 ; j < cols ; j++){
                copiedMat[i][j] = mat[i][j];
            }
        }
        return copiedMat;
    }

    public static void printGrid(int gridXSize, int gridYSize, boolean[][] mat){
        System.out.printf("\n ");
        // check if env is initialized correctly
        for (int i = 1 ; i < gridXSize-1 ; i++){
            for (int j = 1 ; j < gridYSize-1 ; j ++){
                if (mat[i][j]==true){
                    System.out.printf("%2s", "*");
                }
                else {
                    System.out.printf("%2s", ".");
                }   
            }
            System.out.printf("\n ");
            
        }
    }
}