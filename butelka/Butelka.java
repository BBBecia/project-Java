package pl.project.butelka;

import java.util.Random;
import java.util.Scanner;

public class Butelka {
    private double ileLitrow;
    private double pojemnosc;

    Butelka (double ileLitrow, double pojemnosc)
    {
        this.ileLitrow=ileLitrow;
        this. pojemnosc=pojemnosc;
    }

    double getIleLitrow(){
        return ileLitrow;
    }

    boolean wlej(double ilosc){
        if(ileLitrow+ilosc<=pojemnosc){
            this.ileLitrow+=ilosc;;
        } else{
            ilosc = pojemnosc - ileLitrow;
            this.ileLitrow+=ilosc;
            System.out.println("nie wszystko zostało wlane");
            return false;
        }
        return true;
    }

    boolean wylej (double ilosc){
        if(ilosc <= ileLitrow) {
            this.ileLitrow -= ilosc;
        }
        else{
            return false;
        }
        return true;
    }

    void przelej(double ilosc,Butelka gdziePrzelac) {
        if (ilosc <= ileLitrow && gdziePrzelac.pojemnosc-gdziePrzelac.ileLitrow>=ilosc) {
            this.wylej(ilosc);
            gdziePrzelac.wlej(ilosc);
        } else if (ilosc > this.ileLitrow ) {
            double max_ilosc = this.ileLitrow;
            this.wylej(max_ilosc);
            gdziePrzelac.wlej(max_ilosc);
            System.out.println("W butelce nie ma tyle wody. Przelano maksymalną możliwą ilość.");
        } else {
            this.wylej(gdziePrzelac.pojemnosc-gdziePrzelac.ileLitrow);
            gdziePrzelac.wlej(gdziePrzelac.pojemnosc-gdziePrzelac.ileLitrow);
            System.out.println("Nie wszystko zostało przelane bo w butelce nie ma tyle miejsca. Przelano maksymalnie możliwą ilość wody.");
        }
    }

    public static void main(String[] args) {
        Butelka[] butelka = new Butelka[3];
        Butelka[] butelka_losowa = new Butelka[50];
        Random generator = new Random();
        Scanner wpisz = new Scanner(System.in);

        System.out.println("Podaj pojemność butelki pierwszej i drugiej");
        double pojemność1=wpisz.nextDouble();
        double pojemność2=wpisz.nextDouble();
        butelka[0]=new Butelka(0,pojemność1);
        butelka[1]=new Butelka(0,pojemność2);
        System.out.println("Pierwsza butelka jest pusta. Ile wody chcesz do niej wlać?");
        double wlej = wpisz.nextDouble();
        butelka[0].wlej(wlej);
        System.out.println("W pierwszej butelce jest "+butelka[0].ileLitrow+" litrów wody.");
        System.out.println("Druga butelka jest pusta. Ile wody chcesz do niej wlać?");
        wlej = wpisz.nextDouble();
        butelka[1].wlej(wlej);
        System.out.println("W drugiej butelce jest "+butelka[1].ileLitrow+" litrów wody.");
        System.out.println("Czy chcesz przelać wodę z jednej butelki do drugiej?");
        String czyPrzelac = wpisz.next();
        if(czyPrzelac.equals("tak")){
            System.out.println("Z której butelki chcesz przelać wodę?");
            int zKtórejWylac = wpisz.nextInt();
            if(zKtórejWylac!=1 && zKtórejWylac!=2){
                System.out.println("Nie ma takiej butelki!");
            } else {
                System.out.println("Ile litrów chcesz przelać?");
                double ileWody=wpisz.nextDouble();
                butelka[(zKtórejWylac-1)].przelej(ileWody,butelka[(zKtórejWylac%2)]);
                System.out.println("W pierwszej butelce jest "+butelka[0].ileLitrow+" litrów wody.");
                System.out.println("W drugiej butelce jest "+butelka[1].ileLitrow+" litrów wody.");
            }
        }

        /*for (int i =0; i< butelka_losowa.length;i++){
            butelka_losowa[i]=new Butelka(generator.nextInt(100),generator.nextInt());
        }
        System.out.println("Losowa butelka:");
        System.out.println(butelka_losowa[2].getIleLitrow());*/
    }
}
