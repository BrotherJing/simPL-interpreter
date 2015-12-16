package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        System.out.println("type check in App");
        TypeResult tr1 = l.typecheck(E);
        System.out.println(tr1.t);
        TypeResult tr2 = r.typecheck(E);
        System.out.println(tr2.t);

        Type t1 = tr1.t;
        Type t2 = tr2.t;
        TypeVar tv = new TypeVar(false);//new type a
        Substitution substitution = tr1.s.compose(tr2.s).compose(t1.unify(new ArrowType(t2,tv)));//t1=t2->a
        System.out.println(substitution);
        ArrowType newArrowType = (ArrowType)substitution.apply(t1);

       /* ArrowType at = (ArrowType)(tr1.t);
        Type t2 = tr2.t;

        Substitution substitution = tr1.s.compose(tr2.s).compose(at.t1.unify(t2));
        ArrowType newArrowType = (ArrowType)substitution.apply(at);
        substitution.apply(t2);*/

        System.out.println("end check in App");
        return TypeResult.of(substitution,newArrowType.t2);//return a
        //return TypeResult.of(tr1.s.compose(tr2.s).compose(at.t1.unify(t2)),at.t2);
        //return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        // according to the rule E-App,
        // first evaluate the function,
        // then evaluate the parameter, using the original state.
        // then evaluate the expression in fun, using a new Env.

        Value v = l.eval(s);
        FunValue fun = (FunValue)v;

        Value param = r.eval(s);
        State newState = State.of(new Env(fun.E,fun.x,param),s.M,s.p);
        Value result = fun.e.eval(newState);
        return result;
        //return null;
    }
}
