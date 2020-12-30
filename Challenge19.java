import java.util.*;
public class Challenge19 {
    private static Scanner o;
    private static int N;
    private static String[] regions;
    private static int[][] neighbours;
    private static String colors[] = {"Blue","Red","Green", "Black", "Yellow"};
    private static int[] colorassigned;
    private static int colorvalue = 4;
    public static void main(String[] args) {
        o = new Scanner(System.in);
        detailsregion();
        int index = 0;
        int colorused = Color(index);
        if(colorused != 0){
            System.out.println("Total number of colors used: " + colorused);
            System.out.println("The color for each region are as follows:");
            for(int i = 0; i < N; i ++){
                System.out.print(regions[i] + " (" + colors[colorassigned[i]] + ") " + "-> ");
                for(int j = 0; j < neighbours[i].length; j += 1){
                    System.out.print(regions[neighbours[i][j]] + " (" + colors[colorassigned[neighbours[i][j]]] + ") ");
                }
                System.out.println();
            }
        }else{
            System.out.println("Regions can not be coloured with " + colorvalue + " colors.");
        }
    }
 public static void detailsregion(){
        System.out.println("Enter number of regions: ");
        N = o.nextInt();
        neighbours = new int[N][];
        regions = new String[N];
        System.out.println("Enter the names of " + N + " regions:");
        for(int i = 0; i < N; i += 1){
            regions[i] = o.next();
        }
        System.out.println(N + " regions with assigned Region Numbers are: ");
        for(int i = 0; i < N; i ++){
            System.out.println(i + " " + regions[i]);
        }
        for(int i = 0; i < N; i ++){
            System.out.println("Enter the number of neighbours of '" + regions[i] + "' region:");
            int K = o.nextInt();
            neighbours[i] = new int[K];
            System.out.println("Enter the neighbours of " + regions[i] + " region. Provide region number:");
            for(int j = 0; j < K; j ++){
                int H = o.nextInt();
                neighbours[i][j] = H;
            }
        }
        System.out.println("There are "+N+" regions in the map: ");
        for(int i = 0; i < N; i ++){
            System.out.print(regions[i] + " ");
        }
        System.out.println();
        System.out.println("The neighbouring regions for each region are as follows:");
        for(int i = 0; i < N; i ++){
            System.out.print(regions[i] + " -> ");
            for(int j = 0; j < neighbours[i].length; j += 1){
                System.out.print(regions[neighbours[i][j]] + " ");
            }
            System.out.println();
        }
    }
    public static int Color(int index)
    {
        colorassigned = new int[N];
        Arrays.fill(colorassigned, -1);
        if(colormap(index) == false){
            return 0;
        }
        boolean[] availableColors = new boolean[5];
        Arrays.fill(availableColors, true);
        int colorused = 0;
        for(int i = 0; i < N; i += 1){
            System.out.println(i + ": " + colorassigned[i]);
            availableColors[colorassigned[i]] = false;
        }
        for(int i = 0; i < colorvalue; i ++){
            if(availableColors[i] == false){
                colorused += 1;
            }
        }
        return colorused;
    }
    public static boolean colormap(int index){
        if(index == N){
            return true;
        }
        for(int color = 0; color < colorvalue; color += 1) {
            if(colorsafe(index, color)){
                colorassigned[index] = color;
                if(colormap(index + 1)){
                    return true;
                }
                colorassigned[index] = -1;
            }
        }
        return false;
    }
    public static boolean colorsafe(int index, int color){
        for(int i = 0; i < neighbours[index].length; i ++){
            int v = neighbours[index][i];
            if(color == colorassigned[v]){
                return false;
            }
        }
        return true;
    }
}