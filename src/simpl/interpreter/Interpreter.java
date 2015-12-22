package simpl.interpreter;

import java.io.FileInputStream;
import java.io.InputStream;

import simpl.Logger;
import simpl.parser.Parser;
import simpl.parser.SyntaxError;
import simpl.parser.ast.Expr;
import simpl.typing.DefaultTypeEnv;
import simpl.typing.TypeError;

public class Interpreter {

    public void run(String filename) {
        try (InputStream inp = new FileInputStream(filename)) {
            Parser parser = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            Expr program = (Expr) parseTree.value;
            Logger.off();
            System.out.println(program.typecheck(new DefaultTypeEnv()).t);
            System.out.println(program.eval(new InitialState()));
        }
        catch (SyntaxError e) {
            //System.out.println("syntax error");
            System.out.println(e.getMessage());
        }
        catch (TypeError e) {
            //System.out.println("type error");
            System.out.println(e.getMessage());
        }
        catch (RuntimeError e) {
            System.out.println(e.getMessage());
            //System.out.println("runtime error");
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void interpret(String filename) {
        Interpreter i = new Interpreter();
        System.out.println(filename);
        i.run(filename);
    }

    public static void main(String[] args) {
        if(args.length<1){
            System.out.println("usage: java -jar SimPL.jar program.spl");
            return;
        }
        interpret(args[0]);
        /*interpret("doc/examples/plus3.spl");
        interpret("doc/examples/max.spl");
        interpret("doc/examples/factorial.spl");
        interpret("doc/examples/gcd1.spl");
        interpret("doc/examples/sum.spl");
        interpret("doc/examples/map.spl");
        interpret("doc/examples/gcd2.spl");
        interpret("doc/examples/pcf.even2.spl");
        interpret("doc/examples/pcf.sum2.spl");
        interpret("doc/examples/pcf.minus.spl");
        interpret("doc/examples/pcf.fibonacci.spl");
        interpret("doc/examples/pcf.factorial.spl");
        interpret("doc/examples/pcf.twice.spl");
        interpret("doc/examples/pcf.lists.spl");*/
    }
}
