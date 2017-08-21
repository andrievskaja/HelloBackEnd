
import com.andrievskaja.business.model.Contact;
import com.andrievskaja.business.service.ContactsService;
import com.andrievskaja.business.service.impl.ContactsServiceImpl;
import com.andrievskaja.dao.ContactRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Людмила
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:service-test-context.xml",
    "classpath:context.xml"
})
public class ContactsServiceImplTest {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactsService contactsService;
    ContactsServiceImpl c = mock(ContactsServiceImpl.class);

    @Test
    public void contactsSort() {
        when(contactRepository.findAll()).thenReturn(initContacts());
        Assert.assertEquals(7, contactsService.listContactsSort("^(?!A).*$").size());
    }
    @Test
    public void contactsSortZero() {
        when(contactRepository.findAll()).thenReturn(initContacts());
        Assert.assertEquals(0, contactsService.listContactsSort(".*\\\\b.{0}").size());
    }

    private List initContacts() {
        List<Contact> contact = new ArrayList<>();
        contact.add(new Contact(1, "Ivan"));
        contact.add(new Contact(2, "Olga"));
        contact.add(new Contact(3, "Sacha"));
        contact.add(new Contact(4, "Anna"));
        contact.add(new Contact(5, "Alina"));
        contact.add(new Contact(6, "Vanja"));
        contact.add(new Contact(7, "Senja"));
        contact.add(new Contact(8, "Ava"));
        contact.add(new Contact(9, "Olli"));
        contact.add(new Contact(10, "Vasja"));
        return contact;
    }
}
