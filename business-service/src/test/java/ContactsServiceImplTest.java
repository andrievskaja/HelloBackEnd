
import com.andrievskaja.business.model.Contact;
import com.andrievskaja.business.service.impl.ContactsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Людмила
 */
public class ContactsServiceImplTest {

    private final ContactsServiceImpl contactsService = new ContactsServiceImpl();
    ContactsServiceImpl c = mock(ContactsServiceImpl.class);

    @Test
    public void contactsSort() {
        when(c.testListContacts()).thenReturn(initContacts());
        Assert.assertEquals(7, contactsService.listContactsSort("^A.*$").size());
    }

    private List<Contact> initContacts() {
        List<Contact> contact = new ArrayList<>();
        contact.add(new Contact(1, "Иван"));
        contact.add(new Contact(2, "Ольга"));
        contact.add(new Contact(3, "Саша"));
        contact.add(new Contact(4, "Анна"));
        contact.add(new Contact(5, "Алина"));
        contact.add(new Contact(6, "Ваня"));
        contact.add(new Contact(7, "Сеня"));
        contact.add(new Contact(8, "Айва"));
        contact.add(new Contact(9, "Оли"));
        contact.add(new Contact(10, "Вася"));
        return contact;
    }
}
