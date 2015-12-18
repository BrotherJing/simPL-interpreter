package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        System.out.println("----------type check in RelExpr");
        TypeResult tr1 = l.typecheck(E);
        System.out.println(tr1.t);
        TypeResult tr2 = r.typecheck(E);
        System.out.println(tr2.t);

        /*Substitution substitution = tr1.s.compose(tr2.s)
                .compose(tr1.t.unify(Type.INT)).compose(tr2.t.unify(Type.INT));*/

        Type t1 = tr1.t;
        Type t2 = tr2.t;

        Substitution substitution = tr2.s.compose(tr1.s);

        t1 = substitution.apply(t1);
        t2 = substitution.apply(t2);

        substitution = t2.unify(Type.BOOL)
                .compose(t1.unify(Type.BOOL))
                .compose(substitution);

        TypeResult result = TypeResult.of(substitution,Type.BOOL);

        System.out.println("----------end check in RelExpr");
        return result;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue lb = (BoolValue)(l.eval(s));
        if(!lb.b){
            return new BoolValue(false);
        }
        BoolValue rb = (BoolValue)(r.eval(s));
        return new BoolValue(rb.b);
    }
}
