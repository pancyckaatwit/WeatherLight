package Software.src;

import java.awt.Color;

public class LightEffect {
    
    //Variables
    double temperature=0;
    Color tempColor;

    //Will get a base color depending on the temp (As of now it changes every 3 degrees ranging from 10 to 98)
    public Color setTemperatureColor() {
        temperature=API.getTemperature();
        if(temperature<4) {
            tempColor=Color.BLUE;
        }else if(temperature>4 && temperature<=7) {
            tempColor=new Color(8, 0, 247);
        }else if(temperature<7 && temperature<=10) {
            tempColor=new Color(16, 0, 239);
        }else if(temperature>10 && temperature<=13){
            tempColor=new Color(24, 0, 231);
        }else if(temperature>13 && temperature<=16) {
            tempColor=new Color(32, 0, 223);
        }else if(temperature>16 && temperature<=19) {
            tempColor=new Color(40, 0, 215);
        }else if(temperature>19 && temperature<=22) {
            tempColor=new Color(48, 0, 207);
        }else if(temperature>22 && temperature<=25) {
            tempColor=new Color(56, 0, 199);
        }else if(temperature>25 && temperature<=28) {
            tempColor=new Color(64, 0, 191);
        }else if(temperature>28 && temperature<=31) {
            tempColor=new Color(72, 0, 183);
        }else if(temperature>31 && temperature<=34) {
            tempColor=new Color(80, 0, 175);
        }else if(temperature>34 && temperature<=37) {
            tempColor=new Color(88, 0, 167);
        }else if(temperature>37 && temperature<=40) {
            tempColor=new Color(96, 0, 159);
        }else if(temperature>40 && temperature<=43) {
            tempColor=new Color(104, 0, 151);
        }else if(temperature>43 && temperature<=46) {
            tempColor=new Color(112, 0, 143);
        }else if(temperature>46 && temperature<=49) {
            tempColor=new Color(120, 0, 135);
        }else if(temperature>49 && temperature<=52) {
            tempColor=new Color(128, 0, 127);
        }else if(temperature>52 && temperature<=55) {
            tempColor=new Color(135, 0, 120);
        }else if(temperature>55 && temperature<=58) {
            tempColor=new Color(143, 0, 112);
        }else if(temperature>58 && temperature<=61) {
            tempColor=new Color(151, 0, 104);
        }else if(temperature>61 && temperature<=64) {
            tempColor=new Color(159, 0, 96);
        }else if(temperature>64 && temperature<=67) {
            tempColor=new Color(167, 0, 88);
        }else if(temperature>67 && temperature<=70) {
            tempColor=new Color(175, 0, 80);
        }else if(temperature>70 && temperature<=73) {
            tempColor=new Color(183, 0, 72);
        }else if(temperature>73 && temperature<=76) {
            tempColor=new Color(191, 0, 64);
        }else if(temperature>76 && temperature<=79) {
            tempColor=new Color(199, 0, 56);
        }else if(temperature>79 && temperature<=82) {
            tempColor=new Color(207, 0, 48);
        }else if(temperature>82 && temperature<=85) {
            tempColor=new Color(215, 0, 40);
        }else if(temperature>85 && temperature<=88) {
            tempColor=new Color(223, 0, 32);
        }else if(temperature>88 && temperature<=91) {
            tempColor=new Color(231, 0, 24);
        }else if(temperature>91 && temperature<=94) {
            tempColor=new Color(239, 0, 16);
        }else if(temperature>94 && temperature<=97) {
            tempColor=new Color(247, 0, 8);
        }else if(temperature>97) {
            tempColor=Color.RED;
        }
        return tempColor;
    }
}
