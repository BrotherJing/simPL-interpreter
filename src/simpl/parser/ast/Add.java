package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Add extends ArithExpr {

    public Add(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " + " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        // according to E-ADD
        // assume that l and r both evaluate to IntValue, then return the sum of the two IntValues
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        return new IntValue(((IntValue)v1).n+((IntValue)v2).n);
        //return new IntValue(((IntValue)l.eval(s)).n+((IntValue)r.eval(s)).n);
        //return null;
    }
}
