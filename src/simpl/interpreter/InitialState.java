package simpl.interpreter;

import static simpl.parser.Symbol.symbol;
import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.snd;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;
import simpl.parser.Symbol;

public class InitialState extends State {

    public InitialState() {
        super(initialEnv(Env.empty), new Mem(), new Int(0));
    }

    private static Env initialEnv(Env E) {
        return 
            new Env(
            new Env(
            new Env(
            new Env(
            new Env(
            new Env(
            new Env(E,Symbol.symbol("fst"),new fst()),
            Symbol.symbol("snd"),new snd()),
            Symbol.symbol("hd"),new hd()),
            Symbol.symbol("tl"),new tl()),
            Symbol.symbol("iszero"),new iszero()),
            Symbol.symbol("pred"),new pred()),
            Symbol.symbol("succ"),new succ())
            ;
    }
}
