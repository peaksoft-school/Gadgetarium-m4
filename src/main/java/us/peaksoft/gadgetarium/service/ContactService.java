package us.peaksoft.gadgetarium.service;
import us.peaksoft.gadgetarium.dto.ContactRequest;

public interface ContactService {
    void sendEmail(ContactRequest contact);

}
