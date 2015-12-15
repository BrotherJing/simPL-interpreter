package simpl.interpreter;

import simpl.parser.Symbol;

public class Env {

    private final Env E;
    private final Symbol x;
    private final Value v;

    private Env() {
        E = null;
        x = null;
        v = null;
    }

    public static Env empty = new Env() {
        public Value get(Symbol y) {
            return null;
        }

        public Env clone() {
            return this;
        }
        
        public String toString(){
            return "";
        }
    };

    public Env(Env E, Symbol x, Value v) {
        this.E = E;
        this.x = x;
        this.v = v;
    }

    public Value get(Symbol y) {
        // TODO
        // if y==x, return v, else find the value in E
        if(y.toString().equals(x.toString())){
            return v;
        }
        return E.get(y);
        //return null;
    }

    public Env clone() {
        // TODO
        // clone a new Env same as this
        return new Env(E,x,v);
        //return null;
    }
    
    public String toString(){
        String str = x.toString()+":"+v.toString()+"\n";
        str += E.toString();
        return str;
    }
}
