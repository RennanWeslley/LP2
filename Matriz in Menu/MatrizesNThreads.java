import java.util.Random;
import java.util.Scanner;

public class MatrizesThread {
    static int[][] m3;

    static class Matriz {
        int tam;
        int[][] m;
        Random r = new Random();

        public Matriz(int tam) {
            this.tam = tam;
            m = new int[tam][tam];
            geraMatriz(tam);
        }

        public Matriz(int tam, int[][] m) {
            this.tam = tam;
            this.m = m;
        }

        public void setIndx(int i, int j, int value) {
            m[i][j] = value;
        }

        public int getIndx(int i, int j) {
            return m[i][j];
        }

        public void geraMatriz(int tam) {
            for(int i = 0, j = 0; ; j++) {
                if(j == tam) {
                    if(i == (tam-1))
                        break;

                    i++;
                    j = 0;
                }

                m[i][j] = r.nextInt(10)+1;
            }

        }

        public void show() {
            for(int i = 0, j = 0; ; j++) {
                if(j == tam) {
                    if(i == (tam-1))
                        break;

                    System.out.println();
                    j = 0;
                    i++;
                }

                System.out.printf(" %5d", m[i][j]);
            }

            System.out.println("\n");
        }
    }

    static class MyThread implements Runnable {
        int x, tam;
        Matriz m1, m2;

        public MyThread(int x, int tam, Matriz m1, Matriz m2) {
            this.x = x;
            this.tam = tam;
            this.m1 = m1;
            this.m2 = m2;
        }

        public void run() {
            calcMatriz();
        }

        public void calcMatriz() {
            int soma = 0;

            for(int i1 = x, j1 = 0; ; soma = 0, j1++) {
                if(j1 == tam) {
                    if(i1 == (tam-1))
                        break;

                    i1++;
                    j1 = 0;
                }

                for(int i = 0, j = 0; i < tam; i++, j++)
                    soma += m1.getIndx(i1, j) * m2.getIndx(i, j1);

                m3[i1][j1] = soma;
            }
        }
    }

    public static void menu() {
        System.out.println("Tamanho da Matriz");
        System.out.println("1 - 12x12");
        System.out.println("2 - 20x20");
        System.out.println("3 - 40x40");
        System.out.println("4 - 80x80");
        System.out.println("5 - 100x100");
        System.out.println("6 - 120x120");
        System.out.println("7 - 160x160");
        System.out.println("8 - 200x200");
    }

    public static boolean bounds(int n, int l1, int l2) {
        return n > l1 && n < l2;
    }

    public static int setNThreads(int op, int tam) {
        int n = 0;

        switch(op) {
            case 1: n = 2;   break;
            case 2: n = 4;   break;
            case 3: n = tam; break;
        }

        return n;
    }

    public static int setTam(int op) {
        int n = 0;

        switch(op) {
            case 1: n =  12; break;
            case 2: n =  20; break;
            case 3: n =  40; break;
            case 4: n =  80; break;
            case 5: n = 100; break;
            case 6: n = 120; break;
            case 7: n = 160; break;
            case 8: n = 200; break;
        }

        return n;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Thread[] t;
        Matriz m1, m2, res;
        int tam, nThreads, op;

        while(true) {
            menu();

            System.out.print("\nDigite o tamanho desejado: ");
            op = s.nextInt();

            if(bounds(op, 0, 9))
                break;

            System.out.println("\nDígito inválido, por favor digite um valor entre "
                    +          "1 e 8.\n");
        }

        tam = setTam(op);

        System.out.println();

        while(true) {
            System.out.println("1 - 2 Threads\n"
                    +          "2 - 4 Threads\n"
                    +          "3 - N Threads");

            System.out.print("Digite a opção: ");
            op = s.nextInt();

            if(bounds(op, 0, 4))
                break;

            System.out.println("\nDígito inválido, por favor digite um valor entre "
                    +          "1 e 3.\n");
        }

        System.out.println();

        nThreads = setNThreads(op, tam);

        t = new Thread[nThreads];

        m1 = new Matriz(tam);
        m2 = new Matriz(tam);

        m3 = new int[tam][tam];

        for(int i = 0, j = 0; i < nThreads; i++, j += tam/nThreads) {
            t[i] = new Thread(new MyThread(j, tam, m1, m2));
            t[i].start();
        }

        res = new Matriz(tam, m3);

        System.out.println("M1:");
        m1.show();
        System.out.println("M2:");
        m2.show();
        System.out.println("Resultado");
        res.show();
    }
}
