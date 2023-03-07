
import org.graalvm.polyglot.*;
import java.lang.reflect.Array;

class Polyglot {


    private static double RProb(int nr, int x)
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();

        polyglot.getBindings("R").putMember("nr",nr);
        polyglot.getBindings("R").putMember("x",x);
        Value result=polyglot.eval("R", "sum(dbinom(0:x, nr, 0.5))");
        double prob=result.asDouble();
        polyglot.close();

        return prob;
    }

    //functia MAIN
    public static void main(String[] args)
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        int nr= polyglot.eval("python", "int(input(\"Dati numarul de aruncari: \"))").asInt();
        int x=0;
        while(x<1 || x>nr)
        {
            x = polyglot.eval("python", "int(input(\"Dati numarul x: \"))").asInt();
        }

        double prob=RProb(nr,x);

        System.out.println("Probabilitatea ceruta este: "+ prob);

        polyglot.close();
    }
}
