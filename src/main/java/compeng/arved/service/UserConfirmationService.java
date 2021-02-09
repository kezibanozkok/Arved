package compeng.arved.service;

import compeng.arved.domain.UserConfirmation;

import java.util.List;

public interface UserConfirmationService {
    List<UserConfirmation> getPendingApproval();
    void confirm(String id);
    void reject(String id);
}
