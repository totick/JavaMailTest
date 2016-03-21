package JavaMailTest.JavaMailTest;


/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		String user = "j.lundgren@nexeven.com";
		String password = "$/P4yec2JmyZLj";
		
		MailReader reader = new MailReader();
		String content = reader.getForgotPasswordMailContent(user, password);
		System.out.println(content);
	}
}
