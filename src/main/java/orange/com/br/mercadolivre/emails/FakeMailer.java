package orange.com.br.mercadolivre.emails;

import org.springframework.stereotype.Component;

@Component
public class FakeMailer implements Mailer {

    @Override
    public void send(String body, String subject, String from, String to) {
        System.out.println(body);
        System.out.println(subject);
        System.out.println(from);
        System.out.println(to);
    }

}
