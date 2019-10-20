package com.contacts.app.controller;

import com.contacts.app.dto.ContactDTO;
import com.contacts.app.exceptions.contact.ContactNotFoundException;
import com.contacts.app.exceptions.contact.ContactParameterNull;
import com.contacts.app.exceptions.contact.response.HTTPContactParameterNull;
import com.contacts.app.model.Contact;
import com.contacts.app.services.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class to manage the web operations to contact
 */
@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    @ApiOperation(value = "Create contact", notes = "Service to create new contact")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Contact created successfully"),
            @ApiResponse(code = 400, message = "Invalid Request")})
    public ResponseEntity<ContactDTO> createContact(@RequestBody ContactDTO contactDTO) throws HTTPContactParameterNull {
        try {
            return new ResponseEntity<>(this.contactService.create(contactDTO), HttpStatus.CREATED);
        } catch (ContactParameterNull contactParameterNull) {
            throw new HTTPContactParameterNull(contactParameterNull.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update contact", notes = "Service to update a contact")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Contact updated successfully"),
            @ApiResponse(code = 404, message = "Contact not found")})
    public ResponseEntity<ContactDTO> updateContact(@PathVariable("id") Integer id, @RequestBody ContactDTO contactDTO) throws HTTPContactParameterNull {
        try {
            ContactDTO responseContact = this.contactService.update(id, contactDTO);
            return new ResponseEntity<>(responseContact, HttpStatus.OK);
        } catch (ContactParameterNull | ContactNotFoundException contactParameterNull) {
            throw new HTTPContactParameterNull(contactParameterNull.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete contact", notes = "Service to delete a contact")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Contact deleted successfully"),
            @ApiResponse(code = 404, message = "Contact not found")})
    public void deleteCustomer(@PathVariable("id") Integer id) {
        this.contactService.delete(id);
    }

    /*@GetMapping("/{id}")
    @ApiOperation(value = "Get contacts by User", notes = "Service to get a list of contacts by user")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "List generated successfully"),
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<List<ContactDTO>> getAll(@PathVariable("id") Integer id) throws HTTPContactParameterNull {

        try {
            return ResponseEntity.ok(this.contactService.getByUser(id));
        } catch (ContactNotFoundException e) {
            throw new HTTPContactParameterNull(e.getMessage());
        }
    }*/

}
