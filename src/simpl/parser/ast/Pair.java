package simpl.parser.ast;

import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Pair extends BinaryExpr {

    public Pair(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(pair " + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        Substitution substitution = tr2.s.compose(tr1.s);

        Type t1 = substitution.apply(tr1.t);
        Type t2 = substitution.apply(tr2.t);

        return TypeResult.of(substitution,new PairType(t1,t2));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        return new PairValue(v1,v2);
    }
}
