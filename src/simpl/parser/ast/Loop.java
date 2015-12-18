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

public class Loop extends Expr {

    public Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        System.out.println("----------type check in Loop");
        TypeResult tr1 = e1.typecheck(E);
        System.out.println(tr1.t);
        TypeResult tr2 = e2.typecheck(E);
        System.out.println(tr2.t);

        /*Substitution substitution = tr1.s.compose(tr2.s)
                .compose(tr1.t.unify(Type.INT)).compose(tr2.t.unify(Type.INT));*/

        Type t1 = tr1.t;
        //Type t2 = tr2.t;

        Substitution substitution = tr2.s.compose(tr1.s);

        t1 = substitution.apply(t1);
        //t2 = substitution.apply(t2);

        substitution = t1.unify(Type.BOOL)
                .compose(substitution);

        TypeResult result = TypeResult.of(substitution,Type.UNIT);

        System.out.println("----------end check in Loop");
        return result;
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue v1 = (BoolValue)e1.eval(s);
        if(v1.b){
            return new Seq(e2,this).eval(s);
        }else{
            return Value.UNIT;
        }
    }
}
