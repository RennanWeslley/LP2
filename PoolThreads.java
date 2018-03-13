import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class PoolThreads {
    static int[][] m1 = {{1, 4, 5}, {2, 3, 4}, {4, 2, 4}};
    static int[][] m2 = {{5, 6, 1}, {4, 3, 5}, {7, 5, 4}};
    static int[][] m3 = new int[3][3];
    
    static class MyThreadPool implements Runnable {
        int x, y;
        
        public MyThreadPool(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public void run() {
            doMath();
        }
        
        public void doMath() {
            for(int i = x, j = y, soma = 0; ; soma = 0, j++) {
                if(j == 3) {
                    if(i == 2)
                        break;
                    
                    j = 0;
                    i++;
                }
                
                for(int i1 = 0, j1 = 0; i1 < 3; i1++, j1++)
                    soma += m1[i][j1] * m2[i1][j];
                
                m3[i][j] = soma;
            }
        }
    }
    
    public static void print() {
        for(int i = 0, j = 0; ; j++) {
            if(j == 3) {
                if(i == 2)
                    break;
                
                System.out.println();
                j = 0;
                i++;
            }
            
            System.out.print(m3[i][j] + " ");
        }
        
        System.out.println("\n");
    }
    
    public static void main(String[] args) {
        Executor e = Executors.newCachedThreadPool();
        
        for(int i = 0, j = 0; ; j++) {
            if(j == 3) {
                if(i == 2)
                    break;
                
                j = 0;
                i++;
            }
            
            e.execute(new MyThreadPool(i, j));
        }
        
        while(((ThreadPoolExecutor) e).getActiveCount() != 0);
        
        print();
    }
    
}
