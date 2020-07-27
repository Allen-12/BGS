package biometricgatesecurity;

public class Javaserial
{
    public static void main (String[] args)throws Exception 
    {
        Communicator close = new Communicator();
        close.close();
        Communicator main = new Communicator();
//        main.initialize();
        Thread t=new Thread()
        {
            @Override
            public void run() 
            {
                try 
                {
                    Thread.sleep(1000000);
                } 
                catch (InterruptedException ie)
                {}
            }
        };
        t.start();
        System.out.println("Started");
       
    }
    
}
