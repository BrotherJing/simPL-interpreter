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
        // TODO
        // only IntValue is allowed
        IntValue v1 = (IntValue)l.eval(s);
        IntValue v2 = (IntValue)r.eval(s);
        return new BoolValue(v1.n>v2.n);
    }
}
