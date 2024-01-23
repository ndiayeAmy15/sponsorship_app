package sn.dev.sponsorship_app.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sponsorship {
    private int id;
    private DateFormat  date_sponsorship ;
    private Utilisateur elector;
    private Utilisateur candidate;

}
