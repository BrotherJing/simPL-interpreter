package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " :: " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        System.out.println("----------type check in Cons");
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        System.out.println(tr2.t);

        /*if(!(tr2.t instanceof ListType)){
            throw new TypeError("not list type");
        }*/
        Type t1 = tr1.t;
        Type t2 = tr2.t;

        Substitution substitution = tr2.s.compose(tr1.s);

        t1 = substitution.apply(t1);
        t2 = substitution.apply(t2);

        substitution = t2.unify(new ListType(t1)).compose(substitution);

        System.out.println("----------end check in Cons");

        return TypeResult.of(substitution,substitution.apply(t2));

        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        return new ConsValue(v1,v2);
        //return null;
    }
}
