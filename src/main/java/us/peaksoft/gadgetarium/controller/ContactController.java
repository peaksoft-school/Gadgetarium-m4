package us.peaksoft.gadgetarium.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.peaksoft.gadgetarium.dto.ContactRequest;
import us.peaksoft.gadgetarium.service.ContactService;

@RestController
@RequiredArgsConstructor
@Tag(name = "ContactController", description = "Connection between users and programmist")
@RequestMapping("api/contacts")
public class ContactController {
    private final ContactService contactService;

    @PostMapping("sendEmail")
    public void contact(@RequestBody ContactRequest contact){
        contactService.sendEmail(contact);
    }
}
