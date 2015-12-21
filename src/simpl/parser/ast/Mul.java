package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Mul extends ArithExpr {

    public Mul(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " * " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // according to E-MUL
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        if(!(v1 instanceof IntValue&&v2 instanceof IntValue)){
            throw new RuntimeError("must be 2 int values");
        }
        return new IntValue(((IntValue)v1).n*((IntValue)v2).n);
    }
}
