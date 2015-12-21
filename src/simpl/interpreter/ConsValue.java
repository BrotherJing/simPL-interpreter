package simpl.interpreter;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        return v1+"::"+v2;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof NilValue){
            return false;
        }else if(other instanceof ConsValue){
            return v1.equals(((ConsValue) other).v1)&&v2.equals(((ConsValue) other).v2);
        }
        return false;
    }
}
