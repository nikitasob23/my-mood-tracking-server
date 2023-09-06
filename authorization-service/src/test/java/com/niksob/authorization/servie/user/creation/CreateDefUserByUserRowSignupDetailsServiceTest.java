package com.niksob.authorization.servie.user.creation;

import com.niksob.authorization.config.model.user.TestUserConfig;
import com.niksob.authorization_model.model.signup.SignupDetails;
import com.niksob.domain_model.model.user.UserInfo;
import com.niksob.authorization.service.user.creation.CreateDefUserByLoginDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {TestUserConfig.class})
public class CreateDefUserByUserRowSignupDetailsServiceTest {

    @Autowired
    private CreateDefUserByLoginDetailsService createDefUserByLoginDetailsService;
    @Autowired
    private SignupDetails signupDetails;
    @Autowired
    @Qualifier("test_user_info_without_refresh_token")
    private UserInfo userInfo;

    @Test
    public void testCreation() {

        final UserInfo resultUserInfo = createDefUserByLoginDetailsService.execute(signupDetails);

        compareUserInfoExcludingDateTime(resultUserInfo);
        compareCreationDateTime(resultUserInfo);
        compareLastVisitDateTime(resultUserInfo);
    }

    private void compareCreationDateTime(UserInfo resultUserInfo) {
        final LocalDateTime resultDateTime = resultUserInfo.getActiveDetails().getCreationDateTime();
        final LocalDateTime dateTime = userInfo.getActiveDetails().getCreationDateTime();
        assertThat(resultDateTime).isEqualToIgnoringSeconds(dateTime);
    }

    private void compareLastVisitDateTime(UserInfo resultUserInfo) {
        final LocalDateTime resultDateTime = resultUserInfo.getActiveDetails().getLastVisitDateTime();
        final LocalDateTime dateTime = userInfo.getActiveDetails().getLastVisitDateTime();
        assertThat(resultDateTime).isEqualToIgnoringSeconds(dateTime);
    }

    private void compareUserInfoExcludingDateTime(UserInfo resultUserInfo) {
        assertThat(resultUserInfo)
                .usingRecursiveComparison()
                .ignoringFields("activeDetails.creationDateTime")
                .ignoringFields("activeDetails.lastVisitDateTime")
                .isEqualTo(userInfo);
    }
}
