package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class RelExpr extends BinaryExpr {

    public RelExpr(Expr l, Expr r) {
        super(l, r);
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

        substitution = t2.unify(Type.INT)
                .compose(t1.unify(Type.INT))
                .compose(substitution);

        TypeResult result = TypeResult.of(substitution,Type.BOOL);

        System.out.println("----------end check in RelExpr");
        return result;
        //return null;
    }
}
