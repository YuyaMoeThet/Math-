package com.example.myapplication5;

public class RandomQuestion {
    private  int[]arr;

    public RandomQuestion(){
        arr = new int[10];

        for(int i=0 ; i<10 ; i++)
            arr[i] = -1;
    }

    public  int[] getRandomGenerateNoArr(){
        int min = 0 ;
        int max = 9;

        int randomNo = 0;

        int count = 0;

        while(count <10){
            randomNo =  (int)Math.floor(Math.random() * (max - min + 1) + min);

            if(!isExist(randomNo)){
                arr[count++] = randomNo;
            }

        }
        return arr;
    }

    private  boolean isExist(int num){
        for(int i=0 ; i<10 ; i++){
            if(arr[i] == -1)
                return false;
            else if(arr[i] == num)
                return true;
        }
        return false;
    }

}
