package com.istyleglobalnetwork.floatingmarkets.data;

/**
 * Created by Sung on 13/12/2017 AD.
 */

public class DataRating {

    private float starAll;
    private int userAll;
    private int star1;
    private int star2;
    private int star3;
    private int star4;
    private int star5;

    public DataRating() {
        this.starAll = 0;
        this.userAll = 0;
        this.star1 = 0;
        this.star2 = 0;
        this.star3 = 0;
        this.star4 = 0;
        this.star5 = 0;
    }

    public float getStarAll() {
        return starAll;
    }

    public void addStarAll(float star){
        starAll += star;
    }

    public int getUserAll() {
        return userAll;
    }

    public void addUserAll(){
        userAll += 1;
    }

    public int getStar1() {
        return star1;
    }

    public void addStar1(){
        star1 += 1;
    }


    public int getStar2() {
        return star2;
    }

    public void addStar2(){
        star2 += 1;
    }

    public int getStar3() {
        return star3;
    }

    public void addStar3(){
        star3 += 1;
    }

    public int getStar4() {
        return star4;
    }

    public void addStar4(){
        star4 += 1;
    }

    public int getStar5() {
        return star5;
    }

    public void addStar5(){
        star5 += 1;
    }

    public float getStarAvg(){
        if (starAll == 0)
            return 0;
        return starAll/userAll;
    }
}
