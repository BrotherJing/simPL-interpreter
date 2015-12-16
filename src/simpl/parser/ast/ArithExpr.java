package simpl.parser.ast;

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
        // TODO
        System.out.println("type check in ArithExpr");
        TypeResult tr1 = l.typecheck(E);
        System.out.println(tr1.t);
        TypeResult tr2 = r.typecheck(E);
        System.out.println(tr2.t);

        TypeResult result = TypeResult.of(tr1.s.compose(tr2.s).compose(tr1.t.unify(Type.INT)).compose(tr2.t.unify(Type.INT)),Type.INT);

        System.out.println("end check in ArithExpr");
        return result;
        //return null;
    }
}
