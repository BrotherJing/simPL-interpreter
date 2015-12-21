package simpl.parser.ast;

import simpl.Logger;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class ArithExpr extends BinaryExpr {

    public ArithExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        Logger.i("----------type check in ArithExpr");
        TypeResult tr1 = l.typecheck(E);
        Logger.i(tr1.t);
        TypeResult tr2 = r.typecheck(E);
        Logger.i(tr2.t);

        Type t1 = tr1.t;
        Type t2 = tr2.t;

        Substitution substitution = tr2.s.compose(tr1.s);

        t1 = substitution.apply(t1);
        t2 = substitution.apply(t2);

        substitution = t2.unify(Type.INT).compose(t1.unify(Type.INT)).compose(substitution);

        TypeResult result = TypeResult.of(substitution,Type.INT);

        Logger.i("----------end check in ArithExpr");
        return result;
    }
}
