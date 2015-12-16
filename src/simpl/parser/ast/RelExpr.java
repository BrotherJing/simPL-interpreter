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
        System.out.println("type check in RelExpr");
        TypeResult tr1 = l.typecheck(E);
        System.out.println(tr1.t);
        TypeResult tr2 = r.typecheck(E);
        System.out.println(tr2.t);

        TypeResult result = TypeResult.of(tr1.s.compose(tr2.s),Type.BOOL);

        System.out.println("end check in RelExpr");
        return result;
        //return null;
    }
}
