package com.contacts.app.services;

import com.contacts.app.dto.ContactDTO;
import com.contacts.app.exceptions.contact.ContactNotFoundException;
import com.contacts.app.exceptions.contact.ContactParameterNull;
import com.contacts.app.exceptions.user.UserNotFound;
import com.contacts.app.model.Contact;
import com.contacts.app.repository.ContactRepository;
import com.contacts.app.services.impl.ContactServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;


import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ContactServiceTest {

    private ContactRepository contactRepository = mock(ContactRepository.class);
    private ContactService contactService = new ContactServiceImpl(contactRepository);


    // ---------------------------------------------- CREATE TEST ----------------------------------------------

    @Test()
    public void create_contact_when_is_successful() throws ContactParameterNull {
        ContactDTO contactDTOTest = new ContactDTO();
        contactDTOTest.setPhoneNumber("537782324234");
        contactDTOTest.setAge(23);
        contactDTOTest.setName("MyName");
        contactDTOTest.setNickName("NickName");

        Contact contactToSaved = new Contact();
        contactToSaved.setPhoneNumber("537782324234");
        contactToSaved.setAge(23);
        contactToSaved.setName("MyName");
        contactToSaved.setNickName("NickName");

        Contact contactSaved = new Contact();
        contactSaved.setPhoneNumber("537782324234");
        contactSaved.setAge(23);
        contactSaved.setName("MyName");
        contactSaved.setNickName("NickName");
        contactSaved.setIdContact(1);

        when(contactRepository.save(contactToSaved)).thenReturn(contactSaved);

        ContactDTO contactResult = contactService.create(contactDTOTest);

        Assert.assertSame(contactResult.getIdContact(),contactSaved.getIdContact());

    }

    @Test(expected = ContactParameterNull.class)
    public void create_contact_when_name_is_null() throws ContactParameterNull {
        ContactDTO contactDtoTest = new ContactDTO();
        contactDtoTest.setPhoneNumber("537782324234");
        contactDtoTest.setAge(36);
        contactDtoTest.setNickName("NickName");

        contactService.create(contactDtoTest);
    }

    @Test(expected = ContactParameterNull.class)
    public void create_contact_when_age_is_null() throws ContactParameterNull {
        ContactDTO contactDtoTest = new ContactDTO();
        contactDtoTest.setPhoneNumber("537782324234");
        contactDtoTest.setName("MyName");
        contactDtoTest.setNickName("NickName");

        contactService.create(contactDtoTest);
    }

    @Test(expected = ContactParameterNull.class)
    public void create_contact_when_phone_is_null() throws ContactParameterNull {
        ContactDTO contactDtoTest = new ContactDTO();
        contactDtoTest.setAge(23);
        contactDtoTest.setName("MyName");
        contactDtoTest.setNickName("NickName");

        contactService.create(contactDtoTest);
    }

    // ---------------------------------------------- UPDATE TEST ----------------------------------------------

    @Test(expected = ContactNotFoundException.class)
    public void update_contact_when_is_not_found() throws ContactNotFoundException, ContactParameterNull {
        ContactDTO contactDTOTest = new ContactDTO();
        contactDTOTest.setPhoneNumber("537782324234");
        contactDTOTest.setAge(23);
        contactDTOTest.setName("MyName");
        contactDTOTest.setNickName("NickName");

        Contact contactSaved = new Contact();
        contactSaved.setPhoneNumber("537782324234");
        contactSaved.setAge(23);
        contactSaved.setName("MyName");
        contactSaved.setNickName("NickName");
        contactSaved.setIdContact(1);

        ContactDTO contactResult = contactService.update(1,contactDTOTest);
    }

    @Test()
    public void update_contact_when_is_success() throws ContactNotFoundException, ContactParameterNull {
        ContactDTO contactDTOTest = new ContactDTO();
        contactDTOTest.setPhoneNumber("537782324234");
        contactDTOTest.setAge(23);
        contactDTOTest.setName("MyName");
        contactDTOTest.setNickName("NickName");

        Contact contactSaved = new Contact();
        contactSaved.setPhoneNumber("537782324234");
        contactSaved.setAge(23);
        contactSaved.setName("MyName");
        contactSaved.setNickName("NickName");
        contactSaved.setIdContact(1);

        when(contactRepository.findByIdContact(1)).thenReturn(contactSaved);

        ContactDTO contactResult = contactService.update(1,contactDTOTest);

        Assert.assertSame(contactResult.getIdContact(),contactSaved.getIdContact());

    }

    @Test(expected = ContactParameterNull.class)
    public void update_contact_when_name_is_null() throws ContactNotFoundException, ContactParameterNull{
        ContactDTO contactDtoTest = new ContactDTO();
        contactDtoTest.setPhoneNumber("537782324234");
        contactDtoTest.setAge(36);
        contactDtoTest.setNickName("NickName");

        contactService.update(1, contactDtoTest);
    }

    @Test(expected = ContactParameterNull.class)
    public void update_contact_when_age_is_null() throws ContactNotFoundException, ContactParameterNull{
        ContactDTO contactDtoTest = new ContactDTO();
        contactDtoTest.setPhoneNumber("537782324234");
        contactDtoTest.setName("MyName");
        contactDtoTest.setNickName("NickName");

        contactService.create(contactDtoTest);
    }

    @Test(expected = ContactParameterNull.class)
    public void update_contact_when_phone_is_null() throws ContactNotFoundException, ContactParameterNull{
        ContactDTO contactDtoTest = new ContactDTO();
        contactDtoTest.setAge(23);
        contactDtoTest.setName("MyName");
        contactDtoTest.setNickName("NickName");

        contactService.create(contactDtoTest);
    }

}
