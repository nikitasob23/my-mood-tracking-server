package com.niksob.mailsender.model.mail.active_code;

import com.niksob.mailsender.model.auth.ActiveCode;
import com.niksob.mailsender.model.user.Email;
import com.niksob.domain_model.model.user.login.username.Username;
import lombok.Data;
import lombok.NonNull;

@Data
public class ActiveCodeSendingInfo {
    @NonNull
    private Username senderUsername;
    @NonNull
    private Email recipientEmail;
    @NonNull
    private ActiveCode activeCode;
}
