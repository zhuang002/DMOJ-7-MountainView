/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mountainview;

import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class MountainView {

    /**
     * @param args the command line arguments
     */
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        // TODO code application logic here
        for (int i=0;i<3;i++) doTestCase();
        
        
        
    }

    private static void doTestCase() {
        int n=sc.nextInt();
        int[] mountains=new int[n];
        for (int i=0;i<n;i++)
            mountains[i]=sc.nextInt();
        int idx=findBestView(mountains);
        System.out.println(idx+1);
    }

    private static int findBestView(int[] mountains) {
        int maxViews=Integer.MIN_VALUE;
        int idx=-1;
        for (int i=0;i<mountains.length;i++) {
            int views=leftViews(mountains,i)+rightViews(mountains,i);
            if (maxViews<views) {
                maxViews=views;
                idx=i;
            }
        }
        return idx;
    }

    private static int leftViews(int[] mountains, int idx) {
        if (idx<=0) return 0;
        int views=0;
        double minTan=tangent(mountains,idx-1,idx);
        for (int i=idx-2;i>=0;i--) {
            double tan=tangent(mountains,i,idx);
            if (tan<minTan) {
                views++;
                minTan=tan;
            }
        }
        return views+1;
    }

    private static int rightViews(int[] mountains, int idx) {
        if (idx>=mountains.length-1) return 0;
        int views=0;
        double maxTan=tangent(mountains,idx,idx+1);
        for (int i=idx+2;i<mountains.length;i++) {
            double tan=tangent(mountains,idx,i);
            if (tan>maxTan) {
                views++;
                maxTan=tan;
            } 
        }
        return views+1;
    }

    private static double tangent(int[] mountains, int i1, int i2) {
        return (double)(mountains[i2]-mountains[i1])/(double)(i2-i1);
    }
    
}
