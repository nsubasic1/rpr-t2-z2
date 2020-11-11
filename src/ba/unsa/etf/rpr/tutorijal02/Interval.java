package ba.unsa.etf.rpr.tutorijal02;

import static java.lang.Math.min;

public class Interval {
private double lijevi_kraj;
private double desni_kraj;
private boolean lijevi_pripada;
private boolean desni_pripada;
public Interval(double x, double y, boolean l, boolean d)throws IllegalArgumentException{
    if(x>y) throw new IllegalArgumentException();
    lijevi_kraj = x;
    desni_kraj = y;
    lijevi_pripada = l;
    desni_pripada = d;
}
public Interval(){
    lijevi_kraj = desni_kraj = 0;
    lijevi_pripada = desni_pripada = false;
}

    public static Interval intersect(Interval i, Interval i2) {
        double novi_lijevi=0, novi_desni=0;
        boolean lp,dp;
        if(i.lijevi_kraj > i2.desni_kraj || i2.lijevi_kraj > i.desni_kraj) return new Interval();
        else if(i.lijevi_kraj == i2.desni_kraj && (!i.lijevi_pripada || !i2.desni_pripada)) return  new Interval();
        else if(i2.lijevi_kraj == i.desni_kraj && (!i2.lijevi_pripada || !i.desni_pripada)) return  new Interval();
        else if(i.lijevi_kraj == i2.desni_kraj) return new Interval(i2.lijevi_kraj, i.desni_kraj, i2.lijevi_pripada, i.desni_pripada);
        else if(i2.lijevi_kraj == i.desni_kraj) return new Interval(min(i.lijevi_kraj, i2.lijevi_kraj), i2.desni_kraj, i.lijevi_pripada, i2.desni_pripada);
        else {
            if (i.lijevi_kraj > i2.lijevi_kraj) {
                novi_lijevi = i.lijevi_kraj;
                lp = i.lijevi_pripada;
            } else {
                novi_lijevi = i2.lijevi_kraj;
                lp = i2.lijevi_pripada;
            }
            if (i.desni_kraj < i2.desni_kraj) {
                novi_desni = i.desni_kraj;
                dp = i.desni_pripada;
            } else {
                novi_desni = i2.desni_kraj;
                dp = i2.desni_pripada;
            }
        }
        return new Interval(novi_lijevi, novi_desni,lp,dp);
    }

    public boolean isNull(){
    if(lijevi_kraj == 0 && desni_kraj == 0 && !lijevi_pripada && !desni_pripada) return true;
    return false;
}
public boolean isIn(double p){
    if(lijevi_pripada && p>=lijevi_kraj){
        if(desni_pripada && p<=desni_kraj) return true;
        else if(p<desni_kraj) return true;
    }
    else if(p>lijevi_kraj){
        if(desni_pripada && p<=desni_kraj) return true;
        else if(p<desni_kraj) return true;
    }
    return false;
}
public Interval intersect(Interval d){
    double novi_lijevi=0, novi_desni=0;
    boolean lp,dp;
    if(lijevi_kraj > d.desni_kraj || d.lijevi_kraj > desni_kraj) return new Interval();
    else if(lijevi_kraj == d.desni_kraj && (!lijevi_pripada || !d.desni_pripada)) return  new Interval();
    else if(d.lijevi_kraj == desni_kraj && (!d.lijevi_pripada || !desni_pripada)) return  new Interval();
    else if(lijevi_kraj == d.desni_kraj) return new Interval(d.lijevi_kraj, desni_kraj, d.lijevi_pripada, desni_pripada);
    else if(d.lijevi_kraj == desni_kraj) return new Interval(min(lijevi_kraj, d.lijevi_kraj), d.desni_kraj, lijevi_pripada, d.desni_pripada);
    else {
        if (lijevi_kraj > d.lijevi_kraj) {
            novi_lijevi = lijevi_kraj;
            lp = lijevi_pripada;
        } else {
            novi_lijevi = d.lijevi_kraj;
            lp = d.lijevi_pripada;
        }
        if (desni_kraj < d.desni_kraj) {
            novi_desni = desni_kraj;
            dp = desni_pripada;
        } else {
            novi_desni = d.desni_kraj;
            dp = d.desni_pripada;
        }
    }
    return new Interval(novi_lijevi, novi_desni,lp,dp);
}

    @Override
    public String toString() {
        if(lijevi_pripada && desni_pripada)return "[" + lijevi_kraj + "," + desni_kraj + "]";
        else if(lijevi_pripada && !desni_pripada) return "[" + lijevi_kraj + "," + desni_kraj + ")";
        else if(!lijevi_pripada && desni_pripada) return "(" + lijevi_kraj + "," + desni_kraj + "]";
        return "(" + lijevi_kraj + "," + desni_kraj + ")";
    }
}
