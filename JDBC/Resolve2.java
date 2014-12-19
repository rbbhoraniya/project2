import javax.naming.Binding;
import javax.naming.NamingEnumeration;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
public class Resolve2 {
   public static void main(String argv[]) {
      // The user should provide a file to lookup
      if (argv.length != 1) {
         System.err.println("Usage: java Resolve2 ");
         System.exit(-1);}
      // Here we use the file system service provider
      Hashtable env = new Hashtable();
      env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.FSContextFactory");
      env.put(Context.PROVIDER_URL, argv[0]);
      try {
         // Create the initial context
         Context ctx = new InitialContext(env);
            NamingEnumeration ne = ctx.listBindings("");
         while(ne.hasMore()) {
           Binding b = (Binding) ne.next();
           System.out.println(b.getName() + " " + b.getObject());
         }
         // close the context
         ctx.close();
      } catch (NamingException e) {
         System.err.println("Problem looking up " + argv[0] + ": " + e);
      }
   }
}
