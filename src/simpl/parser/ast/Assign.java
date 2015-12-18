package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO

        System.out.println("----------type check in Assign");
        TypeResult tr1 = l.typecheck(E);
        System.out.println("e1:"+tr1.t);
        TypeResult tr2 = r.typecheck(E);
        System.out.println("e2:"+tr2.t);

        Type t1 = tr1.t;
        Type t2 = tr2.t;

        Substitution substitution = tr2.s.compose(tr1.s);

        t1 = substitution.apply(t1);
        t2 = substitution.apply(t2);

        Type tv = new TypeVar(false);

        substitution = t1.unify(new RefType(tv)).compose(substitution);

        tv = substitution.apply(tv);

        substitution = t2.unify(tv).compose(substitution);

        TypeResult result = TypeResult.of(substitution,Type.UNIT);

        System.out.println("----------end check in Assign");

        return result;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        RefValue v1 = (RefValue)(l.eval(s));
        Value v2 = r.eval(s);
        s.M.put(v1.p, v2);
        return Value.UNIT;
        //return null;
    }
}
