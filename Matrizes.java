public class Matrizes {
    public static void main(String[] args) {
        int soma = 0;
        
        int[][] m1 = {{1, 4, 5}, {2, 3, 4}, {4, 2, 4}};
        int[][] m2 = {{5, 6, 1}, {4, 3, 5}, {7, 5, 4}};
        int[][] m3 = new int[3][3];
        
        for(int i1 = 0, j1 = 0; ; soma = 0, j1++) {
            if(j1 == 3) {
                if(i1 == 2)
                    break;
                
                i1++;
                j1 = 0;
            }
            
            for(int i = 0, j = 0; i < 3; i++, j++)
                soma += m1[i1][j] * m2[i][j1];
        
            m3[i1][j1] = soma;
        }
        
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
    
}
