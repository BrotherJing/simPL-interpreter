package simpl.parser.ast;

import simpl.typing.ListType;
import simpl.typing.PairType;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        System.out.println("----------type check in EqExpr");
        TypeResult tr1 = l.typecheck(E);
        System.out.println(tr1.t);
        TypeResult tr2 = r.typecheck(E);
        System.out.println(tr2.t);

        Substitution substitution = tr2.s.compose(tr1.s);

        Type t1 = substitution.apply(tr1.t);
        Type t2 = substitution.apply(tr2.t);

        TypeResult result;

        if(t1 instanceof ListType||t2 instanceof ListType){
            TypeVar tv = new TypeVar(false);
            substitution = t2.unify(tv).compose(t1.unify(tv)).compose(substitution);

            /*t1 = substitution.apply(t1);
            t2 = substitution.apply(t2);*/

        }else if(t1 instanceof PairType||t2 instanceof PairType){
            TypeVar tv = new TypeVar(false);
            substitution = t2.unify(tv).compose(t1.unify(tv)).compose(substitution);
        }else if(t1 instanceof TypeVar&&t2 instanceof TypeVar){
            TypeVar tv = new TypeVar(false);
            substitution = t2.unify(tv).compose(t1.unify(tv)).compose(substitution);
        }
        else if(t1.equals(Type.INT)||t2.equals(Type.INT)){
            //result = TypeResult.of(tr1.s.compose(tr2.s).compose(t1.unify(Type.INT)).compose(t2.unify(Type.INT)), Type.BOOL);
            substitution = t2.unify(Type.INT).compose(t1.unify(Type.INT)).compose(substitution);
        }else if(t1.equals(Type.BOOL)||t2.equals(Type.BOOL)){
            substitution = t2.unify(Type.BOOL).compose(t1.unify(Type.BOOL)).compose(substitution);
        }
        result = TypeResult.of(substitution,Type.BOOL);

        System.out.println("----------end check in EqExpr");
        return result;
        //return null;
    }
}
