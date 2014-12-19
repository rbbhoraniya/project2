
public class ThreadSafeSingleton 
{
	// The instance of the Singleton
	private static ThreadSafeSingleton myInstance;
	
	// Need the following object to synchronize a block 
	private static Object syncObject;
	
	// Prevent direct access to the constructor
	private ThreadSafeSingleton() 
	{
		super();
	}
	
	public static ThreadSafeSingleton getInstance()
	{	
		/* In a non-thread-safe version of a Singleton   
		   the following line could be executed, and the 
		   thread could be immediately swapped out */
		if (myInstance == null) 
		{		
			synchronized(syncObject) 
			{			
				if (myInstance == null) 
				{
					myInstance = new ThreadSafeSingleton();
				}			
			}
		}
		return myInstance;
	}		
}
