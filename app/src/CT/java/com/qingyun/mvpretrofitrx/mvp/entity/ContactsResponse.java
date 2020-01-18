package com.qingyun.mvpretrofitrx.mvp.entity;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ContactsResponse {
    @SerializedName("A")
    private List<GroupMember> a;
    @SerializedName("B")
    private List<GroupMember> b;
    @SerializedName("C")
    private List<GroupMember> c;
    @SerializedName("D")
    private List<GroupMember> d;
    @SerializedName("E")
    private List<GroupMember> e;
    @SerializedName("F")
    private List<GroupMember> f;
    @SerializedName("G")
    private List<GroupMember> g;
    @SerializedName("H")
    private List<GroupMember> h;
    @SerializedName("I")
    private List<GroupMember> i;
    @SerializedName("J")
    private List<GroupMember> j;
    @SerializedName("K")
    private List<GroupMember> k;
    @SerializedName("L")
    private List<GroupMember> l;
    @SerializedName("M")
    private List<GroupMember> m;
    @SerializedName("N")
    private List<GroupMember> n;
    @SerializedName("O")
    private List<GroupMember> o;
    @SerializedName("P")
    private List<GroupMember> p;
    @SerializedName("Q")
    private List<GroupMember> q;
    @SerializedName("R")
    private List<GroupMember> r;
    @SerializedName("S")
    private List<GroupMember> s;
    @SerializedName("T")
    private List<GroupMember> t;
    @SerializedName("U")
    private List<GroupMember> u;
    @SerializedName("V")
    private List<GroupMember> v;
    @SerializedName("W")
    private List<GroupMember> w;
    @SerializedName("X")
    private List<GroupMember> x;
    @SerializedName("Y")
    private List<GroupMember> y;
    @SerializedName("Z")
    private List<GroupMember> z;
    @SerializedName("#")
    private List<GroupMember> xx;


    public List<GroupMember> getA() {
        if (a==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (a.size()>0){
                a.add(0,new GroupMember("A",1));
            }
            for (int i=0;i<a.size();i++){
                a.get(i).setPinyin("A");
            }
        }
        return a;
    }

    public void setA(List<GroupMember> a) {
        this.a = a;
    }

    public List<GroupMember> getB() {
        if (b==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (b.size()>0){
                b.add(0,new GroupMember("B",1));
            }
            for (int i=0;i<b.size();i++){
                b.get(i).setPinyin("B");
            }
        }
        return b;
    }

    public void setB(List<GroupMember> b) {
        this.b = b;
    }

    public List<GroupMember> getC() {
        Log.e(">>>>>>>>>","1");
        if (c==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (c.size()>0){
                c.add(0,new GroupMember("C",1));
            }
            for (int i=0;i<c.size();i++){
                Log.e(">>>>>>>>>","2");
                c.get(i).setPinyin("C");
            }
        }
        return c;
    }

    public void setC(List<GroupMember> c) {
        this.c = c;
    }

    public List<GroupMember> getD() {
        if (d==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (d.size()>0){
                d.add(0,new GroupMember("D",1));
            }
            for (int i=0;i<d.size();i++){
                d.get(i).setPinyin("D");
            }
        }
        return d;
    }

    public void setD(List<GroupMember> d) {
        this.d = d;
    }

    public List<GroupMember> getE() {
        if (e==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (e.size()>0){
                e.add(0,new GroupMember("E",1));
            }
            for (int i=0;i<e.size();i++){
                e.get(i).setPinyin("E");
            }

        }
        return e;
    }


    public void setE(List<GroupMember> e) {
        this.e = e;
    }

    public List<GroupMember> getF() {
        if (f==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (f.size()>0){
                f.add(0,new GroupMember("F",1));
            }
            for (int i=0;i<f.size();i++){
                f.get(i).setPinyin("F");
            }
        }
        return f;
    }

    public void setF(List<GroupMember> f) {
        this.f = f;
    }

    public List<GroupMember> getG() {
        if (g==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (g.size()>0){
                g.add(0,new GroupMember("G",1));
            }
            for (int i=0;i<g.size();i++){
                g.get(i).setPinyin("G");
            }
        }
        return g;
    }

    public void setG(List<GroupMember> g) {
        this.g = g;
    }

    public List<GroupMember> getH() {
        if (h==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (h.size()>0){
                h.add(0,new GroupMember("H",1));
            }
            for (int i=0;i<h.size();i++){
                h.get(i).setPinyin("H");
            }
        }
        return h;
    }

    public void setH(List<GroupMember> h) {
        this.h = h;
    }

    public List<GroupMember> getI() {
        if (i==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (i.size()>0){
                i.add(0,new GroupMember("i",1));
            }
            for (int j=0;j<i.size();j++){
                i.get(j).setPinyin("i");
            }
        }
        return i;
    }

    public void setI(List<GroupMember> i) {
        this.i = i;
    }

    public List<GroupMember> getJ() {
        if (j==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (j.size()>0){
                j.add(0,new GroupMember("j",1));
            }
            for (int i=0;i<j.size();i++){
                j.get(i).setPinyin("j");
            }
        }
        return j;
    }

    public void setJ(List<GroupMember> j) {
        this.j = j;
    }

    public List<GroupMember> getK() {
        if (k==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (k.size()>0){
                k.add(0,new GroupMember("k",1));
            }
            for (int i=0;i<k.size();i++){
                k.get(i).setPinyin("k");
            }
        }
        return k;
    }

    public void setK(List<GroupMember> k) {
        this.k = k;
    }

    public List<GroupMember> getL() {
        if (l==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (l.size()>0){
                l.add(0,new GroupMember("l",1));
            }
            for (int i=0;i<l.size();i++){
                l.get(i).setPinyin("l");
            }
        }
        return l;
    }

    public void setL(List<GroupMember> l) {
        this.l = l;
    }

    public List<GroupMember> getM() {
        if (m==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (m.size()>0){
                m.add(0,new GroupMember("m",1));
            }
            for (int i=0;i<m.size();i++){
                m.get(i).setPinyin("m");
            }
        }
        return m;
    }


    public void setM(List<GroupMember> m) {
        this.m = m;
    }

    public List<GroupMember> getN() {
        if (n==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (n.size()>0){
                n.add(0,new GroupMember("n",1));
            }
            for (int i=0;i<n.size();i++){
                n.get(i).setPinyin("n");
            }
        }
        return n;
    }

    public void setN(List<GroupMember> n) {
        this.n = n;
    }

    public List<GroupMember> getO() {
        if (o==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (o.size()>0){
                o.add(0,new GroupMember("o",1));
            }
            for (int i=0;i<o.size();i++){
                o.get(i).setPinyin("o");
            }
        }
        return o;
    }

    public void setO(List<GroupMember> o) {
        this.o = o;
    }

    public List<GroupMember> getP() {
        if (p==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (p.size()>0){
                p.add(0,new GroupMember("p",1));
            }
            for (int i=0;i<p.size();i++){
                p.get(i).setPinyin("p");
            }
        }
        return p;
    }

    public void setP(List<GroupMember> p) {
        this.p = p;
    }

    public List<GroupMember> getQ() {
        if (q==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (q.size()>0){
                q.add(0,new GroupMember("q",1));
            }
            for (int i=0;i<q.size();i++){
                q.get(i).setPinyin("q");
            }
        }
        return q;
    }

    public void setQ(List<GroupMember> q) {
        this.q = q;
    }

    public List<GroupMember> getR() {
        if (r==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (r.size()>0){
                r.add(0,new GroupMember("r",1));
            }
            for (int i=0;i<r.size();i++){
                r.get(i).setPinyin("r");
            }
        }
        return r;
    }

    public void setR(List<GroupMember> r) {
        this.r = r;
    }

    public List<GroupMember> getS() {
        if (s==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (s.size()>0){
                s.add(0,new GroupMember("s",1));
            }
            for (int i=0;i<s.size();i++){
                s.get(i).setPinyin("s");
            }
        }
        return s;
    }

    public void setS(List<GroupMember> s) {
        this.s = s;
    }

    public List<GroupMember> getT() {
        if (t==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (t.size()>0){
                t.add(0,new GroupMember("t",1));
            }
            for (int i=0;i<t.size();i++){
                t.get(i).setPinyin("t");
            }
        }
        return t;
    }

    public void setT(List<GroupMember> t) {
        this.t = t;
    }

    public List<GroupMember> getU() {
        if (u==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (u.size()>0){
                u.add(0,new GroupMember("u",1));
            }
            for (int i=0;i<u.size();i++){
                u.get(i).setPinyin("u");
            }
        }
        return u;
    }

    public void setU(List<GroupMember> u) {
        this.u = u;
    }

    public List<GroupMember> getV() {
        if (v==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (v.size()>0){
                v.add(0,new GroupMember("v",1));
            }
            for (int i=0;i<v.size();i++){
                v.get(i).setPinyin("v");
            }
        }
        return v;
    }

    public void setV(List<GroupMember> v) {
        this.v = v;
    }

    public List<GroupMember> getW() {
        if (w==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (w.size()>0){
                w.add(0,new GroupMember("w",1));
            }
            for (int i=0;i<w.size();i++){
                w.get(i).setPinyin("w");
            }
        }
        return w;
    }

    public void setW(List<GroupMember> w) {
        this.w = w;
    }

    public List<GroupMember> getX() {
        if (x==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (x.size()>0){
                x.add(0,new GroupMember("x",1));
            }
            for (int i=0;i<x.size();i++){
                x.get(i).setPinyin("x");
            }
        }
        return x;
    }

    public void setX(List<GroupMember> x) {
        this.x = x;
    }

    public List<GroupMember> getY() {
        if (y==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (y.size()>0){
                y.add(0,new GroupMember("y",1));
            }
            for (int i=0;i<y.size();i++){
                y.get(i).setPinyin("y");
            }
        }
        return y;
    }

    public void setY(List<GroupMember> y) {
        this.y = y;
    }

    public List<GroupMember> getZ() {
        if (z==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (z.size()>0){
                z.add(0,new GroupMember("z",1));
            }
            for (int i=0;i<z.size();i++){
                z.get(i).setPinyin("z");
            }
        }
        return z;
    }

    public void setZ(List<GroupMember> z) {
        this.z = z;
    }

    public List<GroupMember> getXx() {
        if (xx==null) {
            return new ArrayList<GroupMember>();
        }else {
            if (xx.size()>0){
                xx.add(0,new GroupMember("#",1));
            }
            for (int i=0;i<xx.size();i++){
                xx.get(i).setPinyin("#");
            }
        }

        return xx;
    }

    public void setXx(List<GroupMember> xx) {

        this.xx = xx;
    }
}
