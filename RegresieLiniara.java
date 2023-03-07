
import org.graalvm.polyglot.*;
import java.util.*;
import java.io.*;

class Polyglot {


    private static void R_RegresieLiniara(double[][] x, String name, String path, String color)
    {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();

        polyglot.getBindings("R").putMember("x_1",x[0]);
        polyglot.getBindings("R").putMember("x_2",x[1]);
        polyglot.getBindings("R").putMember("nume",name);
        polyglot.getBindings("R").putMember("cale",path);
        polyglot.getBindings("R").putMember("culoare",color);
        Value result=polyglot.eval("R", "library(lattice)\n" +
                "df <- structure(list(x1 = x_1, x2 = x_2), .Names=c(\"x1\",\"x2\"), class = \"data.frame\")\n" +
                "lmTemp=lm(x1~x2, data=df)\n"+ //reprezinta regresia liniara folosind functia lm din R
                "my.plot <- xyplot(x1~x2, data=df, col=culoare, panel=function(x,y){\npanel.xyplot(x,y)\npanel.abline(lmTemp)})\n" +
                "setwd(cale)\n" + "trellis.device(device=\"png\", filename=nume)\n" + "print(my.plot)\n" + "dev.off()");

        polyglot.close();

    }

    //functia MAIN
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(new File("dataset.txt"));
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        double[][] x =new double[n][m];
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(scanner.hasNextDouble())
                {
                    x[i][j]= scanner.nextDouble();
                }


        System.out.print("The name of the image: ");
        String name_image =reader.readLine();
        System.out.print("The path: ");
        String path = reader.readLine();
        System.out.print("The color: ");
        String color = reader.readLine();

        R_RegresieLiniara(x, name_image, path, color);

        reader.close();

    }
}
