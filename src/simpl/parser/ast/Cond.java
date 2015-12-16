package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        System.out.println("type check in Cond");
        TypeResult tr1 = e1.typecheck(E);
        System.out.println(tr1.s);
        TypeResult tr2 = e2.typecheck(E);
        TypeResult tr3 = e3.typecheck(E);
        System.out.println("end check in Cond");
        TypeResult result = TypeResult.of(tr1.s.compose(tr2.s).compose(tr3.s)
                .compose(tr1.t.unify(Type.BOOL))
                .compose(tr2.t.unify(tr3.t)),tr3.t);
        return result;
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        // refer to E-COND1 and E-COND2
        BoolValue v1 = (BoolValue)e1.eval(s);
        if(v1.b){
            return e2.eval(s);
        }else{
            return e3.eval(s);
        }
        //return null;
    }
}
