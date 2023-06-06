package section7.lesson17;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
//@Repeatable(ChickenContainer.class)
public @interface Chicken {


}
