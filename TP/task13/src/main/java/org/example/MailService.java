package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class MailService {
    @Autowired
    private TravelersCard travelersCard;
    public void sendMailOfRequest(LocalDate date) {
        System.out.println("Отправлено сообщение на почту: " +
                "\n\"Оформленные туры: " +
                "\n" + travelersCard.getTrips() +
                "\nДата оформления: " + date + "\"\n");
    }
}
