package JavaMailTest.JavaMailTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

public class MailReader {

	private Properties props = new Properties();
	
	
	public MailReader(){
		props.setProperty("mail.store.protocol", "imaps");
	}
	
	public String getForgotPasswordMailContent(String user, String password){
		String content = null;
		
		try {
			
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect("imap.one.com", user, password);
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			Message[] msgs = inbox.getMessages();
			Collections.reverse(Arrays.asList(msgs));
			
			//Maybe iterate only the last 10 messages?
			for(Message msg : msgs){
				if(msg.getSubject().equals("[Nexeven] - Reset Password")){
					Multipart mp = (Multipart) msg.getContent();
					BodyPart bp = mp.getBodyPart(0);
					MimeMultipart mimePart = (MimeMultipart) bp.getContent();
					content = mimePart.getBodyPart(1).getContent().toString();
					break;
				}
			}
		} catch (Exception mex) {
			mex.printStackTrace();
		}
		
		return content;
		
	}
	
}
