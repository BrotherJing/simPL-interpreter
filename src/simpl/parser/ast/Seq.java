package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Seq extends BinaryExpr {

    public Seq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " ; " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO

        System.out.println("----------type check in Seq");
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        Type t2 = tr2.t;

        Substitution substitution = tr2.s.compose(tr1.s);

        t2 = substitution.apply(t2);

        System.out.println("----------end check in Seq");
        return TypeResult.of(substitution, t2);
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        return v2;
        //return null;
    }
}
