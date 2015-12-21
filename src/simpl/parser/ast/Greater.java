package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Greater extends RelExpr {

    public Greater(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " > " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // only IntValue is allowed
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        if(!(v1 instanceof IntValue&&v2 instanceof IntValue)){
            throw new RuntimeError("must be 2 int values");
        }
        return new BoolValue(((IntValue)v1).n>((IntValue)v2).n);
    }
}
