package models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "ACCIDENT_LETTERS")
public class AccidentLetters extends BaseModel{
    public String message;
    public String detail;

    public static Finder<UUID, AccidentLetters> finder = new Finder<UUID,AccidentLetters>(UUID.class, AccidentLetters.class);

    public static AccidentLetters find(UUID id){
		return finder.ref(id);
    }
}
