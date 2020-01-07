package dto;
import org.springframework.util.StringUtils;
import play.data.validation.Constraints;
import play.data.validation.Constraints.Validate;
import play.data.validation.Constraints.Validatable;

import static org.springframework.util.ObjectUtils.isEmpty;


@Validate
public class ApplicationForm implements Validatable<String>
{
   @Constraints.Required protected String email;
    @Override
    public String validate() {
        if (isEmpty(email))
        {
           return "Invalid email";
       }
        return null;
    }
public void setEmail(String email) {
    this.email = email;
}
    public String getEmail() {
        return email;
    }

}
